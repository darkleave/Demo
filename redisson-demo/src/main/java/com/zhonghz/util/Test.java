package com.zhonghz.util;

import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.api.annotation.RIndex;

/**
 * 分布式实时对象，增加
 * @Rentity注解,表示改实体为分布式实时对象
 * @RId注解,表示该id为实体唯一标识
 * @RIndex注解 允许该字段作为索引去查询redis
 */
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

    /**
     * 分布式实时对象,获取属性值实则是通过
     * 将get，set方法映射成对应的hget,hset指令去查询或者请求redis,
     * 因此如果直接获取分布式实时对象的字段值是获取不到的，必须通过get,set方法
     * @return
     */
    public String toString(){
        return this.getId() + "," + this.getName();
    }
}
