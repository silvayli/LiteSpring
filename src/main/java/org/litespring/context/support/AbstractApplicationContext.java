package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext{
    DefaultBeanFactory factory = null;

    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String path) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = this.getResourceByPath(path);
        reader.loadBeanDefinitions(resource);
    }

    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }

    public ClassLoader getBeanClassLoader() {
        if(this.beanClassLoader == null){
            return ClassUtils.getDefaultClassLoader();
        }
        return beanClassLoader;
    }

    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    protected abstract Resource getResourceByPath(String path);
}
