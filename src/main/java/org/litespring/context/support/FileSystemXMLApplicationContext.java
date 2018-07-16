package org.litespring.context.support;

import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

public class FileSystemXMLApplicationContext extends AbstractApplicationContext {

    public FileSystemXMLApplicationContext(String path) {
        super(path);
    }

    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }

}
