package com.tabwu.mvc.servlet;

import com.alibaba.fastjson.JSON;
import com.tabwu.mvc.annotation.Controller;
import com.tabwu.mvc.annotation.RequestMapping;
import com.tabwu.mvc.handler.HandlerAdaptor;
import com.tabwu.mvc.utils.XmlParseUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * @PROJECT_NAME: wu-spring_mvc
 * @USER: tabwu
 * @DATE: 2022/1/7 15:42
 * @DESCRIPTION:
 */
public class DispatcherServlet extends HttpServlet {
    private Logger logger;

    private List<String> classnameList = new ArrayList<>();

    private ConcurrentHashMap<String, Object> webApplicationContext = new ConcurrentHashMap<>();

    private HashMap<String, HandlerAdaptor> handlerMapping = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        String ContextConfigLocation = config.getInitParameter("ContextConfigLocation");
        String needScanPath = XmlParseUtil.parseXml(ContextConfigLocation.split(":")[1]);

        String[] paths = needScanPath.split(",");

        assert paths.length > 0;

        this.refresh(paths);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.doDispatcher(req,resp);
        } catch (IOException e) {
            logger.info("500 server exception!!!!");
            resp.getWriter().write("500 server exception!!!");
        }
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (handlerMapping.isEmpty()) {
            logger.info("handlerMapping is null!!!");
            return;
        }

        String contextPath = req.getContextPath();

        String requestURI = req.getRequestURI();
        requestURI = requestURI.replace(contextPath, "").trim();

        if (!handlerMapping.containsKey(requestURI)) {
            logger.info("404 URL NOT FOUND!");
            resp.getWriter().write("404 URL NOT FOUND!");
        }

        HandlerAdaptor handlerAdaptor = handlerMapping.get(requestURI);
        Method method = handlerAdaptor.getMethod();

        //从method中获取参数类型与个数,将请求参数与方法参数绑定匹配
        //此处参数解析绑定有缺陷
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] params = new Object[parameterTypes.length];
        Map<String, String[]> parameterMap = req.getParameterMap();

        for (int i = 0; i < parameterTypes.length; i++) {
            if (ServletRequest.class.isAssignableFrom(parameterTypes[i])) {
                params[i] = req;
            }

            if (ServletResponse.class.isAssignableFrom(parameterTypes[i])) {
                params[i] = resp;
            }

            String methodParam = parameterTypes[i].getSimpleName();
            if (methodParam.equals("String")) {
                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                    params[i] = entry.getValue()[0];
                }
            }
        }

        try {
            Object result = method.invoke(handlerAdaptor.getClassObject(), params);

            //将结果交给视图解析器处理，此处简化处理，直接打印出结果
            resp.getWriter().write(JSON.toJSONString(result));

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void refresh(String[] paths) {
        for (String path : paths) {
            String basePackage = path.replace(".", "/");
            URL resource = this.getClass().getClassLoader().getResource(basePackage);
            File scanPackage = new File(resource.getFile());
            if (scanPackage.isDirectory()) {
                this.componentScan(scanPackage);
            } else {
                logger.warning("base-package config path is not directory!");
            }
        }

        this.createInstance();
    }

    private void createInstance() {
        for (String classname : classnameList) {
            try {
                Class<?> clazz = Class.forName(classname);
                if (clazz.isAnnotationPresent(Controller.class)) {
                    //此处不考虑依赖注入以及循环依赖问题
                    Object bean = clazz.newInstance();
                    Controller controllerAnnotation = clazz.getDeclaredAnnotation(Controller.class);
                    if (controllerAnnotation.value() != "") {
                        webApplicationContext.put(controllerAnnotation.value(),bean);
                    } else {
                        String beanName = clazz.getName().substring(0,1).toLowerCase() + clazz.getName().substring(1);
                        webApplicationContext.put(beanName,bean);
                    }
                    String baseUrl = "";
                    if (clazz.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping requestMappingAnnotation = clazz.getDeclaredAnnotation(RequestMapping.class);
                        baseUrl = requestMappingAnnotation.value();
                    }

                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping requestMappingAnnotation = clazz.getDeclaredAnnotation(RequestMapping.class);
                            String url = (baseUrl + requestMappingAnnotation.value()).trim();
                            HandlerAdaptor adaptor = new HandlerAdaptor(url, bean, method);
                            handlerMapping.put(url,adaptor);
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }


    private void componentScan(File scanPackage) {
        File[] files = scanPackage.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                this.componentScan(file);
            } else {
                if (file.getAbsolutePath().endsWith(".class")) {
                    String className = file.getAbsolutePath().replace(".class", "");
                    classnameList.add(className);
                }
            }
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
