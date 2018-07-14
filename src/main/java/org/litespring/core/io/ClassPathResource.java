package org.litespring.core.io;

import org.litespring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource {
    public ClassPathResource(String path) {
        this(path,null);
    }

    public ClassPathResource(String path, ClassLoader classLoader){
        this.path = path;
        if(classLoader != null){
            this.classLoader = classLoader;
        }else {
            this.classLoader = ClassUtils.getDefaultClassLoader();
        }
    }

    private String path;
    private ClassLoader classLoader;

    public InputStream getInputStream() throws IOException {
        InputStream inputStream = this.classLoader.getResourceAsStream(this.path);
        if(inputStream == null){
            throw new FileNotFoundException(path + " Can't be found");
        }
        return inputStream;
    }

    public String getDescription() {
        return this.path;
    }
}
