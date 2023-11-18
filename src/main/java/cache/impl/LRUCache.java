package cache.impl;

import cache.Cache;
import cache.LinkedHashMapWithCapacity;

import java.util.Optional;
import java.util.UUID;

public class LRUCache<K,V> implements Cache<K,V> {

    private final LinkedHashMapWithCapacity<K, V> map;

    public LRUCache(int capacity) {
        this.map = new LinkedHashMapWithCapacity<>(capacity);
    }

    public Optional<V> get(K key) {
        V value = this.map.get(key);
        return value == null
                ? Optional.empty()
                : Optional.of(value);
    }

    public void put(K key, V value) {
        this.map.put(key, value);
    }

    @Override
    public void pop(K key) {
        map.remove(key);
    }
}
