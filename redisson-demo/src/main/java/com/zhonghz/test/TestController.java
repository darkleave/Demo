package com.zhonghz.test;

import com.zhonghz.util.Test;
import com.zhonghz.util.Test1;
import org.redisson.api.RBucket;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.redisson.codec.KryoCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    private RedissonClient client;

    @Autowired
    public void setRedissonClient(RedissonClient client){
        this.client = client;
    }

    /**
     * redisson通用对象桶,支持对整个对象的序列化以及反序列化
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(method = RequestMethod.GET , value="/testBucket")
    public Object testBucket() throws InterruptedException {
        client.getBucket("anyObject");
        RBucket<Test1> bucket = client.getBucket("anyObject");
        Test1 obj = bucket.get();
        System.out.println("out:" + obj);
        Test1 test2 = new Test1("3333","ccccccc");
        bucket.set(test2,600, TimeUnit.SECONDS);
        Test1 result = bucket.get();
        System.out.println("final:" + result);
        return "test";
    }

    /**
     * redisson 分布式实时对象
     * @return
     */
    @RequestMapping(method = RequestMethod.GET , value="/testLiveObject")
    public Object testLiveObject() {

        RLiveObjectService service = client.getLiveObjectService();
        Test result = service.get(Test.class,"1");

        if(result == null){
            Test testPojo = new Test("1","aaaa");
            System.out.println("pojo:" + testPojo);
            result = service.persist(testPojo);
        }else{
            System.out.println("已存在对象:" + result);
        }
        if(result.getName().equals("aaaa")){
            result.setName("bbbb");
        }else{
            result.setName("aaaa");
        }
        return result;
    }

}
