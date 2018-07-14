package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXMLApplicationContext;
import org.litespring.service.v1.PetStoreService;

public class ApplicationContextTest {
    @Test
    public void testGetBean() {
        ApplicationContext context = new ClassPathXMLApplicationContext("petstore-v1.xml");
        PetStoreService service = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(service);
    }
}
