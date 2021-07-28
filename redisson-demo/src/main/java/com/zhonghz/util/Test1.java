package com.zhonghz.util;

import java.io.Serializable;

public class Test1 implements Serializable {

    private static final long serialVersionUID = -8686007683984030633L;

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

    private String name;

    public Test1(){}

    public Test1(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String toString(){
        return this.id + "," + this.name;
    }
}
