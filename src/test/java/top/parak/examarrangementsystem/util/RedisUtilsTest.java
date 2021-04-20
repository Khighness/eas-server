package top.parak.examarrangementsystem.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisUtilsTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void add() {
        redisTemplate.opsForValue().set("eas:test:1", "1", 10, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("eas:test:2", "2");
    }

    @Test
    void query() {
        System.out.println(redisTemplate.opsForValue().getOperations().getExpire("eas:test:1"));
        System.out.println(redisTemplate.opsForValue().getOperations().getExpire("eas:test:2"));
    }

}
