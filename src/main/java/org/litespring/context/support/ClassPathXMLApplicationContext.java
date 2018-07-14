package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;

public class ClassPathXMLApplicationContext implements ApplicationContext {
    DefaultBeanFactory factory = null;

    public ClassPathXMLApplicationContext(String path){
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(path);
    }

    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }
}
