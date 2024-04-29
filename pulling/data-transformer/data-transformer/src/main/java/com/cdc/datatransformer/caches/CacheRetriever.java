package com.cdc.datatransformer.caches;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public final class CacheRetriever {
    private final Map<String, Cache> cacheMap = new ConcurrentHashMap<>();

    public void put(String key, Cache value) {
        this.cacheMap.put(key, value);
    }

    public Cache get(String key) {
        return this.cacheMap.get(key);
    }

    public Cache remove(String key) {
        return this.cacheMap.remove(key);
    }

}
