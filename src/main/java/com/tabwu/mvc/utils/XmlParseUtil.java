package com.tabwu.mvc.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @PROJECT_NAME: wu-spring_mvc
 * @USER: tabwu
 * @DATE: 2022/1/7 16:51
 * @DESCRIPTION:
 */
public class XmlParseUtil {

    public static String parseXml(String xml) {
        SAXReader saxReader = new SAXReader();
        InputStream inputStream = XmlParseUtil.class.getClassLoader().getResourceAsStream(xml);

        try {
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            Element componentSacn = rootElement.element("component-scan");
            Attribute attribute = componentSacn.attribute("base-package");
            String basePackage = attribute.getText();
            return basePackage;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "";
    }
}
