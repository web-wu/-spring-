package com.tabwu.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.tabwu.mybatis.pojo.Configuration;
import com.tabwu.mybatis.utils.Resources;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/25 16:19
 * @DESCRIPTION:
 */
public class XmlConfigBuilder {

    private Configuration configuration;

    public XmlConfigBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration loadXmlConfig(InputStream in) {

        try {
            Document document = new SAXReader().read(in);
            Element config = document.getRootElement();
            Element dataSourse = config.element("dataSourse");
            List propertyTags = dataSourse.elements();

            Properties pro = new Properties();
            for (Object property : propertyTags) {
                Element property_ = (Element) property;
                pro.setProperty(property_.attributeValue("name"), property_.attributeValue("value"));
            }

            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(pro.getProperty("driverClass"));
            dataSource.setUrl(pro.getProperty("url"));
            dataSource.setUsername(pro.getProperty("username"));
            dataSource.setPassword(pro.getProperty("password"));

            configuration.setDataSource(dataSource);

            Element mappers = config.element("mappers");
            List MapperList = mappers.elements();
            for (Object tag : MapperList) {
                Element mapper = (Element) tag;
                String resource = mapper.attributeValue("resource");
                configuration = new XmlMapperBuilder(this.configuration).loadXmlMapper(Resources.getResource(resource));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        return configuration;
    }
}
