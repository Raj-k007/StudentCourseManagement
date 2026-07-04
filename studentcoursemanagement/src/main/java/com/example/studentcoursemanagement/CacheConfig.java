package com.example.studentcoursemanagement;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory){

        // 1. Get the current active ClassLoader (handles DevTools restarts)
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

//        // 2. Pass the ClassLoader to your JSON serializer
//        GenericJackson2JsonRedisSerializer jsonSerializer =
//                new GenericJackson2JsonRedisSerializer(null, classLoader);
        // 2. Use the modern, non-deprecated Jackson serializer factory pattern
        RedisSerializer<Object> jsonSerializer = RedisSerializer.json();
        // 2. Key Serializer (Plain Text String - Crucial for matching Evict keys)
        RedisSerializer<String> stringSerializer = RedisSerializer.string();
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(15)).disableCachingNullValues().serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringSerializer)
        );
        return RedisCacheManager.builder(connectionFactory).cacheDefaults(config).build();
    }
}
