package org.litespring.context.support;

import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

public class ClassPathXMLApplicationContext extends AbstractApplicationContext {



    public ClassPathXMLApplicationContext(String path) {
        super(path);
    }

    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path,this.getBeanClassLoader());
    }

}
