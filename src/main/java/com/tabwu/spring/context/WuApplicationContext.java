package com.tabwu.spring.context;

import com.tabwu.spring.annotation.Autowired;
import com.tabwu.spring.annotation.Component;
import com.tabwu.spring.annotation.ComponentScan;
import com.tabwu.spring.annotation.Scope;
import org.aspectj.lang.annotation.Aspect;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2021/12/29 15:52
 * @DESCRIPTION:
 */
public class WuApplicationContext {
    private Class configClass;

    //单例池
    private ConcurrentHashMap<String,Object> singletonObjects = new ConcurrentHashMap<String,Object>();
    //beanDefinition 池子
    private ConcurrentHashMap<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    //beanPostProcessor 池子
    private List<BeanPostProcessor> beanPostProcessorsList = new ArrayList<BeanPostProcessor>();
    //缓存所有切面类的clazz文件
    public static List<Class> aspectClazzList = new ArrayList<Class>();


    public WuApplicationContext(Class configClass) {
        this.configClass = configClass;
        this.componentScan();

        for (Map.Entry<String, BeanDefinition> beanDefinition : beanDefinitionMap.entrySet()) {
            String beanName = beanDefinition.getKey();
            BeanDefinition definition = beanDefinition.getValue();
            if (definition.getScope().equals("singleton")) {
                Object bean = doCreateBean(beanName, definition);
                singletonObjects.put(beanName, bean);
            }
        }
    }

    private Object doCreateBean(String beanName, BeanDefinition definition) {
        Class clazz = definition.getClazz();

        try {
            //实例化
            Object instance = clazz.getDeclaredConstructor().newInstance();
            //依赖注入，属性填充
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    //根据类型和beanName进行注入  byType  byName  此处简化直接根据beanName依赖注入
                    Object bean = getBean(declaredField.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(instance,bean);
                }
            }

            //调用Aware接口
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBeanName(beanName);
            }

            //调用beanPostProcessor的 before方法
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorsList) {
                instance = beanPostProcessor.beanPostProcessorBeforeInitialization(instance, beanName);
            }



            //调用initializationBean方法
            if (instance instanceof InitializationBean) {
                ((InitializationBean) instance).afterPropertiesset();
            }

            //调用beanPostProcessor的 after方法
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorsList) {
                instance = beanPostProcessor.beanPostProcessorAfterInitialization(instance, beanName);
            }


            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void componentScan() {
        ComponentScan componentScan = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String paths = componentScan.value();
        //支持配置 ComponentScan 的多包扫描，以 ， 为分隔
        String[] needScanPath = paths.split(",");
        ArrayList<String> pathList = new ArrayList<>();
        for (String scanPath : needScanPath) {
            pathList.add(scanPath);
        }
        //将 AOP 支持对象加入ioc容器
        pathList.add("com.tabwu.spring.aop.proxy");
        for (String path : pathList) {
            path = path.replace(".", "/");
            ClassLoader classLoader = WuApplicationContext.class.getClassLoader();
            URL url = classLoader.getResource(path);

            File file = new File(url.getFile());

            generateBeanDefinition(file, classLoader);
        }

    }

    private void generateBeanDefinition(File file, ClassLoader classLoader) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File f : files) {
                if (f.isDirectory()) {
                    generateBeanDefinition(f, classLoader);
                }
                String absolutePath = f.getAbsolutePath();
                if (absolutePath.endsWith(".class")) {
                    String ClassFullName = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    ClassFullName = ClassFullName.replace("\\", ".");

                    try {
                        Class<?> Clazz = classLoader.loadClass(ClassFullName);
                        //判断当前类是否具有Component注解
                        if (Clazz.isAnnotationPresent(Component.class)) {
                            //判断当前类是否是切面类aspect
                            if (Clazz.isAnnotationPresent(Aspect.class)) {
                                aspectClazzList.add(Clazz);
                            }

                            Component componentAnnotation = Clazz.getDeclaredAnnotation(Component.class);
                            String beanName = componentAnnotation.value();
                            if ("".equals(beanName)) {
                                beanName = Clazz.getSimpleName().substring(0,1).toLowerCase() + Clazz.getSimpleName().substring(1);
                            }
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(Clazz);

                            //判断当前类是否实现或者继承了 BeanPostProcessor 接口
                            if (BeanPostProcessor.class.isAssignableFrom(Clazz)) {
                                BeanPostProcessor beanPostProcessor =(BeanPostProcessor) Clazz.getDeclaredConstructor().newInstance();
                                beanPostProcessorsList.add(beanPostProcessor);
                            }


                            if (Clazz.isAnnotationPresent(Scope.class)) {
                                Scope ScopeAnnotation = Clazz.getDeclaredAnnotation(Scope.class);
                                beanDefinition.setScope(ScopeAnnotation.value());
                            } else {
                                beanDefinition.setScope("singleton");
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = singletonObjects.get(beanName);
                return bean;
            } else {
                Object bean = doCreateBean(beanName, beanDefinition);
                return bean;
            }
        } else {
            throw new NullPointerException();
        }
    }
}

