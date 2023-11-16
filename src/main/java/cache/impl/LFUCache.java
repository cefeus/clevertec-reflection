package cache.impl;

import cache.Cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.UUID;

public class LFUCache<T> implements Cache<T> {

    HashMap<UUID, T> vals;
    HashMap<UUID, Integer> counts;
    HashMap<Integer, LinkedHashSet<UUID>> lists;
    int capacity;
    int min = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    public Optional<T> get(UUID key) {
        if (!vals.containsKey(key))
            return  Optional.empty();

        int count = counts.get(key);
        counts.put(key, count + 1);
        lists.get(count).remove(key);
        if (count == min && lists.get(count).isEmpty())
            min++;
        if (!lists.containsKey(count + 1))
            lists.put(count + 1, new LinkedHashSet<>());
        lists.get(count + 1).add(key);
        return Optional.of(vals.get(key));
    }

    public void put(UUID key, T value) {
        if (capacity <= 0) {
            return;
        }
        if (vals.containsKey(key)) {
            vals.put(key, value);
            get(key);
            return;
        }
        if (vals.size() >= capacity) {
            UUID evit = lists.get(min).iterator().next();
            lists.get(min).remove(evit);
            vals.remove(evit);
            counts.remove(evit);
        }
        vals.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}
