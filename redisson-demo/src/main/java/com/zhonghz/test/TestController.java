package com.zhonghz.test;

import com.zhonghz.util.Test;
import com.zhonghz.util.Test1;
import org.redisson.api.RBucket;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    private RedissonClient client;

    @Autowired
    public void setRedissonClient(RedissonClient client){
        this.client = client;
    }

    @RequestMapping(method = RequestMethod.GET , value="/test")
    public Object test(){
//        Map<String,String> test = new HashMap<>();
//        test.put("test","test");
//        RLiveObjectService service = client.getLiveObjectService();
//        Test testPojo = new Test("1","aaaa");
//        System.out.println("pojo:" + testPojo);
////        if(!service.isExists(testPojo)){
////            service.persist(testPojo);
////        }
////        service.merge(testPojo);
//        Test result = service.get(Test.class,"1");
//        if(result.getName().equals("aaaa")){
//            result.setName("bbbb");
//            System.out.println("in-----------------");
//        }
//        System.out.println(result.getId() + "," + result.getName());
//        System.out.println(result);

        Test1 test2 = new Test1("3333","ccccccc");

        RBucket<Test1> bucket = client.getBucket("anyObject");

        Test1 obj = bucket.get();
        System.out.println("out:" + obj);

        bucket.set(test2);

        bucket.trySet(new Test1("44444","dddd"));
//        bucket.compareAndSet(new Test(4), new AnyObject(5));
        Test1 test3 = bucket.getAndSet(new Test1("55555","eeeeee"));
        System.out.println("final:" + test3);
        // zhzhere 序列化方式的比较
        return "test";
    }
}
