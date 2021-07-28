package com.zhonghz.util;

import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.api.annotation.RIndex;

@REntity
public class Test {
    @RId
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @RIndex
    private String name;

    public Test(){}

    public Test(String id,String name){
        this.id = id;
        this.name = name;
    }

    public String toString(){
        return this.id + "," + this.name;
    }
}
