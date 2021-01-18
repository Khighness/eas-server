package top.parak.examarrangementsystem.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.config </p>
 * <p> FileName: RedisConfigure <p>
 * <p> Description: RedisTemplae配置 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Configuration
public class RedisConfigure {

    /**
     * <p>自定义redisTemplate</p>
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    @SuppressWarnings("all")
    @Qualifier("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        /* 创建redisTemplate */
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        /* 关联redisConnectionFactory */
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        /* Jackson2JsonRedisSerializer：Json序列化器 */
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        /* StringRedisSerializer：String序列化器 */
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        /* 设置key的序列化方式：String */
        redisTemplate.setKeySerializer(stringRedisSerializer);
        /* 设置value的序列化方式：Json */
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        /* 设置hash的key的序列化方式：Json */
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        /* 设置hash的value的序列化方式：Json */
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
