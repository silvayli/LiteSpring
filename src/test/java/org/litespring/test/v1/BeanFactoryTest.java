package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.service.v1.PetStoreService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void testGetBean() {
        reader.loadBeanDefinitions("petstore-v1.xml");
        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");
        assertEquals("org.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());
        PetStoreService petStore = (PetStoreService) factory.getBean("petStore");
        assertNotNull(petStore);
    }


    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinitions("petstore-v1.xml");
        try {
            factory.getBean("invalidBean");
        } catch (BeanCreationException exception) {
            return;
        }
        Assert.fail("Expect BeanCreation Exception");
    }

    @Test
    public void testInvalidXML() {
        try {
            reader.loadBeanDefinitions("xxxx.xml");
        } catch (BeanDefinitionStoreException exception) {
            return;
        }

        Assert.fail("Expect BeanDefinitionStoreException Exception");
    }

}
