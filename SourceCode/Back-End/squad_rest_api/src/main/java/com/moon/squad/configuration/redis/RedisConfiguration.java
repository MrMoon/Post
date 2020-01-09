package com.moon.squad.configuration.redis;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import static com.moon.squad.configuration.ConfigurationConstants.HOST_NAME;
import static com.moon.squad.configuration.ConfigurationConstants.PORT_NUMBER;

@Configuration
class RedisConfiguration extends CachingConfigurerSupport {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration(HOST_NAME, PORT_NUMBER));
    }

    @Bean
    @Primary
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        GenericJackson2JsonRedisSerializer valSerializer = new GenericJackson2JsonRedisSerializer();
        StringSerializer stringSerializer = new StringSerializer();
        template.setValueSerializer(RedisSerializer.string());
        template.setKeySerializer(new RedisSerializer<Object>() {

            @Override
            public byte[] serialize(Object t) throws SerializationException {
                return (t == null ? null : (":" + t.toString()).getBytes());
            }

            @Override
            public Object deserialize(byte[] bytes) throws SerializationException {
                return (bytes == null ? null : new String(bytes));
            }
        });
        template.setHashValueSerializer(valSerializer);
        return template;
    }
}
