package cache;

import cache.impl.LFUCache;
import cache.impl.LRUCache;
import util.ReadProperties;

public class Cache {

    private ICache cache;

    public Cache() {
        String cacheType = ReadProperties.getPropertyByKey("CACHE");
        Integer cacheCapacity = Integer.valueOf(ReadProperties.getPropertyByKey("CAPACITY"));
        if (LFUCache.class.getName().contains(cacheType)) cache = new LFUCache(cacheCapacity);
        if (LRUCache.class.getName().contains(cacheType)) cache = new LRUCache(cacheCapacity);
    }

    public ICache getCache() {
        return cache;
    }
}
