package com.codertom.params.engine.common;

import com.google.common.base.Strings;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 抽象路径类
 */
public class Path {

    //xml配置文件的数据流
    private String contant;

    //在Zookeeper或redis等存储的路径描述地址
    private URL url;

    public Path(String contant,URL url){
        this.contant = contant;
        this.url = url;
    }
    public String getInput() {
        return contant;
    }

    public URL getUrl() {
        return url;
    }

    public void setInput(String contant) {
        this.contant = contant;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public InputStream getInputStream(){
        if (Strings.nullToEmpty(contant).trim().isEmpty()){
            throw new IllegalArgumentException("path input is null or empty");
        }
        InputStream inputStream = new ByteArrayInputStream(contant.getBytes());
        return inputStream;
    }
}
