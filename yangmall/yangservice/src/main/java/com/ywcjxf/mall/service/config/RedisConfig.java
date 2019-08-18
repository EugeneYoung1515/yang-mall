package com.ywcjxf.mall.service.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
public class RedisConfig {

    @Value("${redis.lettuce.host}")
    private String host;

    @Value("${redis.lettuce.password}")
    private String password;

    @Value("${redis.lettuce.port}")
    private int port;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        lettuceConnectionFactory.setHostName(host);
        lettuceConnectionFactory.setPassword(password);
        lettuceConnectionFactory.setPort(port);
        return lettuceConnectionFactory;
    }

    @Bean
    public <T> RedisTemplate<String,T> redisTemplate(RedisConnectionFactory rcf){
        RedisTemplate<String, T> redisTemplate = new RedisTemplate<String, T>();
        redisTemplate.setConnectionFactory(rcf);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //这里要是用string HashOperations<String,Integer,Integer> hashOperations中的hash key 就不能用string以外的

        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        redisTemplate.setHashKeySerializer(serializer);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);//这几个常量的意思是
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS"));

        serializer.setObjectMapper(mapper);

        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean(name = "script")
    public RedisScript<List> script() {

        DefaultRedisScript<List> luaScript = new DefaultRedisScript<List>();
        luaScript.setResultType(List.class);
        luaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/zinter.lua")));
        return luaScript;
    }

    /*
    @Bean(name = "redisLockScript")
    public RedisScript<List> script2() {

        DefaultRedisScript<List> luaScript = new DefaultRedisScript<List>();
        luaScript.setResultType(List.class);
        luaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/redislock.lua")));
        return luaScript;
    }
    */


    @Bean(name = "redisLockScript")
    public RedisScript<Boolean> script2() {

        DefaultRedisScript<Boolean> luaScript = new DefaultRedisScript<Boolean>();
        luaScript.setResultType(Boolean.class);
        luaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/redislock.lua")));
        return luaScript;
    }


    @Bean(name = "releaseLockScript")
    public RedisScript<Long> script3() {

        DefaultRedisScript<Long> luaScript = new DefaultRedisScript<Long>();
        luaScript.setResultType(Long.class);
        luaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/releaselock.lua")));
        return luaScript;
    }

    /*
    @Bean(name = "releaseLockScript")
    public RedisScript<List> script3() {

        DefaultRedisScript<List> luaScript = new DefaultRedisScript<List>();
        luaScript.setResultType(List.class);
        luaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/releaselock.lua")));
        return luaScript;
    }
    */

    @Bean(name = "redisReentrantLockScript")
    public RedisScript<String> script4(){
        DefaultRedisScript<String> luaScript = new DefaultRedisScript<String>();
        luaScript.setResultType(String.class);
        luaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/redisreentrantlock.lua")));
        return luaScript;
    }

    @Bean(name = "releaseReentrantLockScript")
    public RedisScript<Long> script5(){
        DefaultRedisScript<Long> luaScript = new DefaultRedisScript<Long>();
        luaScript.setResultType(Long.class);
        luaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/releasereentrantlock.lua")));
        return luaScript;
    }

    @Bean
    public ObjectMapper mapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);//这几个常量的意思是
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS"));
        return mapper;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
