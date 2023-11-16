package cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapWithCapacity<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LinkedHashMapWithCapacity(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > this.capacity;
    }
}
