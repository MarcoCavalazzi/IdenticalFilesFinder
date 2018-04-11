/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identicalfiles.finder;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class MultiMap<K, V> {

    @SuppressWarnings("CollectionWithoutInitialCapacity")
    private final Map<K, List<V>> map = new HashMap<>();

    public List<V> get(K key) {
        List<V> values = map.get(key);
        if (values == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(values);
        }
    }

    public void add(K key, V value) {
        List<V> values = map.get(key);
        if (values == null) {
            values = new LinkedList<>();
            map.put(key, values);
        }
        values.add(value);
    }
    
    public Set<K> keySet(){
        return map.keySet();
    }
}
