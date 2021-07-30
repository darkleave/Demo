package com.zhonghz.util;

import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;

import java.io.Serializable;

/**
 * kryo 默认的 FieldSerializer 序列化器不支持向前以及向后兼容，
 * 一旦字段新增或者删除，都会导致旧数据序列化异常;
 * 而CompatibleFieldSerializer 序列化器支持一定程度上的向前向后兼容，
 * 字段增加删除都不会造成异常,这种方式与hessian序列化方式将字段存储到map
 * 中有异曲同工之妙(都需要注意子类父类字段名不能相同，否则会被覆盖)
 */
@DefaultSerializer(CompatibleFieldSerializer.class)
public class Test1 implements Serializable {

    private static final long serialVersionUID = -8686007683984030633L;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
