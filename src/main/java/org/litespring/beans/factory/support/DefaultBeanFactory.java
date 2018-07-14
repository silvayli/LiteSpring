package org.litespring.beans.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.util.ClassUtils;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory {

    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";

    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();


    public DefaultBeanFactory(String configFile) {
        this.loadBeanDefinition(configFile);
    }

    private void loadBeanDefinition(String configFile){
        InputStream inputStream = null;
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        inputStream = classLoader.getResourceAsStream(configFile);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()){
                Element element = iterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id,beanClassName);
                this.beanDefinitionMap.put(id,beanDefinition);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public BeanDefinition getBeanDefinition(String beanID) {
        return beanDefinitionMap.get(beanID);
    }

    public Object getBean(String beanID) {
        BeanDefinition beanDefinition = this.getBeanDefinition(beanID);
        String beanClassName = beanDefinition.getBeanClassName();
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

        Class<?> clz = null;
        try {
            clz = classLoader.loadClass(beanClassName);
            return clz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
