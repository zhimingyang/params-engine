package com.codertom.params.engine.common;

import com.google.common.base.Strings;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 抽象路径类
 */
public class Path {

    //xml配置文件的数据流
    private String input;

    //在Zookeeper或redis等存储的路径描述地址
    private URL url;

    public String getInput() {
        return input;
    }

    public URL getUrl() {
        return url;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public InputStream getInputStream(){
        if (Strings.nullToEmpty(input).trim().isEmpty()){
            throw new IllegalArgumentException("path input is null or empty");
        }
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        return inputStream;
    }
}
