package cache.impl;

import cache.ICache;
import cache.LinkedHashMapWithCapacity;

import java.util.Optional;

public class LRUCache<K,V> implements ICache<K,V> {

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
