package com.zhonghz.conf;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.zhonghz.util.Test1;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.*;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.TransportMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RedisConfig {


    @Bean
    public RedissonClient getRedisConfig() throws Exception{
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        List<Class<?>> classList = new ArrayList<>();
        classList.add(Test1.class);
        // redisson 支持的序列化方式 https://github.com/redisson/redisson/wiki/4.-%E6%95%B0%E6%8D%AE%E5%BA%8F%E5%88%97%E5%8C%96
        // https://github.com/eishay/jvm-serializers/wiki 序列化方式性能比较
        KryoCodec kryo = new KryoCodec(classList);
        //kryoCodec 维护了一个kryo 先进先出的队列，每次存储的时候弹出
        // 一个kryo对象，没有就新增一个，序列化完成之后，再将该对象插入队尾中
        // 默认生成的kryo对象的registrationRequired 属性的值为true,
        // 因此redisson在使用kryo序列化方式时需要注意需要提前将类注册到kryo中
//        KryoCodec kryo = new KryoCodec();
//        Kryo kryoSerial = kryo.get();
//        kryoSerial.setRegistrationRequired(false);
//        kryo.yield(kryoSerial);
        config.setCodec(kryo);
//        config.setCodec(new JsonJacksonCodec());
        config.useClusterServers()
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://42.192.43.10:7001")
                .addNodeAddress("redis://42.192.43.10:7002")
                .addNodeAddress("redis://42.192.43.10:7003")
//                .addNodeAddress("redis://42.192.43.10:7004")
//                .addNodeAddress("redis://42.192.43.10:7005")
//                .addNodeAddress("redis://42.192.43.10:7006")
                .setCheckSlotsCoverage(false) // 不设置为false会报错??
                .setMasterConnectionMinimumIdleSize(1)
                .setPassword("123456").setReadMode(ReadMode.MASTER)
        ;
        return Redisson.create(config);
    }



}
