package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.BeansException;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;

import static org.junit.Assert.*;

public class BeanFactoryTest {
    @Test
    public void testGetBean() {
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");
        assertEquals("org.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());
        PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
        assertNotNull(petStore);
    }
    @Test
    public void testInvalidBean(){
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        try{
            factory.getBean("invalidBean");
        }catch (BeanCreationException exception){
            return;
        }
        Assert.fail("Expect BeanCreation Exception");
    }

    @Test
    public void testInvalidXML(){
        try{
            new DefaultBeanFactory("xxx.xml");
        }catch (BeanDefinitionStoreException exception){
            return;
        }

        Assert.fail("Expect BeanDefinitionStoreException Exception");
    }
}
