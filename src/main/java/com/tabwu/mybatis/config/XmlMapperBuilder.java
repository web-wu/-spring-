package com.tabwu.mybatis.config;

import com.tabwu.mybatis.pojo.Configuration;
import com.tabwu.mybatis.pojo.Mapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/25 15:55
 * @DESCRIPTION:
 */
public class XmlMapperBuilder {
    private Configuration configuration;

    public XmlMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration loadXmlMapper(InputStream in) {

        try {
            Document document = new SAXReader().read(in);
            Element mapper = document.getRootElement();
            String namespace = mapper.attributeValue("namespace");

            Iterator iterator = mapper.elementIterator();
            while (iterator.hasNext()) {
                Element ele = (Element) iterator.next();
                Mapper statement = new Mapper();

                String id = ele.attributeValue("id");
                statement.setId(id);

                String parameterType = ele.attributeValue("parameterType");
                if (parameterType != null && !parameterType.isEmpty()) {
                    statement.setParamterType(Class.forName(parameterType));
                }

                String resultType = ele.attributeValue("resultType");
                if (resultType != null && !resultType.isEmpty()) {
                    statement.setResultType(Class.forName(resultType));
                }

                String sql = ele.getTextTrim();
                statement.setSql(sql);

                String key = namespace + "." + id;
                configuration.getMapperMap().put(key,statement);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return configuration;
    }
}
