package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXMLApplicationContext;
import org.litespring.context.support.FileSystemXMLApplicationContext;
import org.litespring.service.v1.PetStoreService;

public class ApplicationContextTest {
    @Test
    public void testGetBean() {
        ApplicationContext context = new ClassPathXMLApplicationContext("petstore-v1.xml");
        PetStoreService service = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(service);
    }

    @Test
    public void testGetBeanFromFileSystemContext(){
        ApplicationContext context = new FileSystemXMLApplicationContext("D:\\MyWorkspace\\LiteSpring\\src\\main\\resources\\petstore-v1.xml");
        PetStoreService service = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(service);
    }
}
