package cache;

import cache.impl.LFUCache;
import cache.impl.LRUCache;
import util.ReadProperties;


/**
 * Wrapper-like class for instantiating cache accordingly to values in properties file
 */
public class Cache {

    private ICache cache;

    /**
     * Constructs a Cache based on properties specifying the cache type and capacity.
     * Initializes either an LFUCache or an LRUCache based on the provided cache type.
     */
    public Cache() {
        String cacheType = ReadProperties.getPropertyByKey("CACHE");
        Integer cacheCapacity = Integer.valueOf(ReadProperties.getPropertyByKey("CAPACITY"));
        if (LFUCache.class.getName().contains(cacheType)) cache = new LFUCache(cacheCapacity);
        if (LRUCache.class.getName().contains(cacheType)) cache = new LRUCache(cacheCapacity);
    }

    /**
     * Retrieves the initialized cache.
     *
     * @return The initialized cache, either LFUCache or LRUCache based on the properties.
     */
    public ICache getCache() {
        return cache;
    }

}
