package cache.impl;

import cache.Cache;
import cache.LinkedHashMapWithCapacity;

import java.util.Optional;
import java.util.UUID;

public class LRUCache<T> implements Cache<T> {

    private final LinkedHashMapWithCapacity<UUID, T> map;

    public LRUCache(int capacity) {
        this.map = new LinkedHashMapWithCapacity<>(capacity);
    }

    public Optional<T> get(UUID key) {
        T value = this.map.get(key);
        return value == null
                ? Optional.empty()
                : Optional.of(value);
    }

    public void put(UUID key, T value) {
        this.map.put(key, value);
    }
}
