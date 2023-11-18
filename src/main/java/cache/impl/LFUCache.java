package cache.impl;

import cache.Cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Optional;

public class LFUCache<K,V> implements Cache<K,V> {

    HashMap<K, V> values;
    HashMap<K, Integer> callCount;
    HashMap<Integer, LinkedHashSet<K>> callCountsLists;
    int capacity;
    int min = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        values = new HashMap<>();
        callCount = new HashMap<>();
        callCountsLists = new HashMap<>();
        callCountsLists.put(1, new LinkedHashSet<>());
    }

    public Optional<V> get(K key) {
        if (!values.containsKey(key))
            return  Optional.empty();

        int count = callCount.get(key);
        callCount.put(key, count + 1);
        callCountsLists.get(count).remove(key);
        if (count == min && callCountsLists.get(count).isEmpty())
            min++;
        if (!callCountsLists.containsKey(count + 1))
            callCountsLists.put(count + 1, new LinkedHashSet<>());
        callCountsLists.get(count + 1).add(key);
        return Optional.of(values.get(key));
    }

    public void put(K key, V value) {
        if (capacity <= 0) {
            return;
        }
        if (values.containsKey(key)) {
            values.put(key, value);
            get(key);
            return;
        }
        if (values.size() >= capacity) {
            K evit = callCountsLists.get(min).iterator().next();
            callCountsLists.get(min).remove(evit);
            values.remove(evit);
            callCount.remove(evit);
        }
        values.put(key, value);
        callCount.put(key, 1);
        min = 1;
        callCountsLists.get(1).add(key);
    }

    @Override
    public void pop(K key) {
        values.remove(key);
        int count = callCount.get(key);
        callCountsLists.get(count).remove(key);
        callCount.remove(key);
    }
}
