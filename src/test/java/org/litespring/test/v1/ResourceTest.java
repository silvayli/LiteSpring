package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class ResourceTest {
    @Test
    public void testClassPathResource() {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        try {
            InputStream inputStream = resource.getInputStream();
            Assert.assertNotNull(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileSystemResource() {
        Resource resource = new FileSystemResource("D:\\MyWorkspace\\LiteSpring\\src\\main\\resources\\petstore-v1.xml");
        try {
            InputStream inputStream = resource.getInputStream();
            Assert.assertNotNull(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
