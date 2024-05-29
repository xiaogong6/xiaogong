package com.xiaogong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-05-06
 */
@Configuration
public class LuaResourceConfig {

    @Bean
    public DefaultRedisScript<Long> generateSerialNumber(){
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/generateSerialNumber.lua")));
        redisScript.setResultType(Long.class);
        return redisScript;
    }

}
