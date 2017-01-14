package com.codertom.params.engine.common;

/**
 * 注册信息
 */
public class URL {
    //路径可能有很多种,redis的key，zk的path，local的path
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean equals(URL url){
        if (path.equals(url.getPath()))
            return true;
        return false;
    }
}
