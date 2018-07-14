package org.litespring.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.util.ClassUtils;

import java.io.InputStream;
import java.util.Iterator;

public class XmlBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    private BeanDefinitionRegistry registry;

    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";


    public void loadBeanDefinitions(String configFile) {
        InputStream inputStream = null;
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        inputStream = classLoader.getResourceAsStream(configFile);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, beanClassName);
                this.registry.registerBeanDefinition(id, beanDefinition);
            }
        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("IO Exception, Invalid XML", e);
        }
    }

}
