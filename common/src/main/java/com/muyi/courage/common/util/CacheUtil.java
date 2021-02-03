package com.muyi.courage.common.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨志勇
 * @apiNote 本地缓存
 * 本地缓存虽快，但是也有缺点：
 * 更新麻烦，容易产生脏缓存。
 * 受到JVM容量的限制。
 * @date 2021-01-20 16:55
 */
public class CacheUtil {
    private static Cache<String,Object> commonCache=null;

    @PostConstruct
    public void init(){
        commonCache= CacheBuilder.newBuilder()
                //初始容量
                .initialCapacity(10)
                //最大100个KEY，超过后会按照LRU策略移除
                .maximumSize(100)
                //设置写缓存后多少秒过期，还有根据访问过期即expireAfterAccess
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .build();
    }

    public static void setCommonCache(String key, Object value) {
        commonCache.put(key,value);
    }

    public static Object getFromCommonCache(String key) {
        return commonCache.getIfPresent(key);
    }
}
