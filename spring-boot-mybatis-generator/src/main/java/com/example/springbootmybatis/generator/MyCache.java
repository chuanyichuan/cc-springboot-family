package com.example.springbootmybatis.generator;

import org.apache.ibatis.cache.Cache;

public class MyCache implements Cache {

    private String id;

    public MyCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("start...");
        System.out.println("end...");
    }

    @Override
    public Object getObject(Object key) {
        System.out.println("val insert into redis");
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {
    }

    @Override
    public int getSize() {
        return 0;
    }
}
