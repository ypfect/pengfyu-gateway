package com.pengfyu.zuul.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * redisson配置
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redisson")
public class RedissonConfig {

    /** redis-host */
    private String ip;
    /** redis端口 */
    private int port;
    /** redis密码 */
    private String password;
    /** 缓存过期时间，默认18小时，单位毫秒 **/
    private int ttl = 18 * 60 * 60 * 1000;
    /** 最长空闲时间，默认1小时，单位毫秒  */
    private int maxIdleTime = 60 * 60 * 1000;

    @Bean(destroyMethod = "shutdown",name = "redissonClient")
    public RedissonClient createRedissonInstance(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + ip + ":" + port).setPassword(password);
        return Redisson.create(config);
    }

    // ~ redisson & spring整合缓存 cacheManager
    // ==============================================================
    @Bean
    public CacheManager createCacheManager(RedissonClient redissonClient){
        Map<String, CacheConfig> config = new HashMap<>();

        try {
            //反射获取缓存实例
            Field[] fields = RedisCacheMapNames.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String mapName = field.getName();
                // 创建一个名称为"testMap"的缓存，过期时间ttl为24分钟，同时最长空闲时maxIdleTime为12分钟。
                config.put(mapName, new CacheConfig(ttl, maxIdleTime));

            }
        }catch (Exception  e){
            throw new RuntimeException(e);
        }
        return new RedissonSpringCacheManager(redissonClient, config);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public int getMaxIdleTime() {
        return maxIdleTime;
    }

    public void setMaxIdleTime(int maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }
}
