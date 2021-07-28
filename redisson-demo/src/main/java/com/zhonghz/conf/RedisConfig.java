package com.zhonghz.conf;

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

@Configuration
public class RedisConfig {


    @Bean
    public Config getRedisConfig() throws Exception{
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
//        KryoCodec kryo = new KryoCodec();
//        kryo.get().register(Test1.class,new JavaSerializer());
//        config.setCodec(kryo);
        config.setCodec(new JsonJacksonCodec());
        config.useClusterServers()
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://42.192.43.10:7001")
                .addNodeAddress("redis://42.192.43.10:7002")
                .addNodeAddress("redis://42.192.43.10:7003")
//                .addNodeAddress("redis://42.192.43.10:7004")
//                .addNodeAddress("redis://42.192.43.10:7005")
//                .addNodeAddress("redis://42.192.43.10:7006")
                .setCheckSlotsCoverage(false) // 不设置为false会报错??
                .setMasterConnectionMinimumIdleSize(5)
                .setPassword("123456").setReadMode(ReadMode.MASTER)
        ;
        String test = config.toYAML();

        System.out.println("test:222222222222222222222222222" + test);

        return config;
    }

    @Bean
    public RedissonClient getReissonClient(Config config){
        RedissonClient client = Redisson.create(config);
        return client;
    }

}
