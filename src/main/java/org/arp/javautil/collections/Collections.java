/*
 * #%L
 * JavaUtil
 * %%
 * Copyright (C) 2012 - 2013 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.arp.javautil.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Extra utilities for collections.
 * 
 * @author Andrew Post
 */
public class Collections {
    
    private static final HashMapFactory HASH_MAP_FACTORY = new HashMapFactory();

    private Collections() {
    }

    /**
     * Puts a value into a map of key -> list of values. If the specified key
     * is new, it creates an {@link ArrayList} as its value.
     * @param map a {@link Map}.
     * @param key a key.
     * @param valueElt a value.
     */
    public static <K, V> void putList(Map<K, List<V>> map, K key, V valueElt) {
        if (map.containsKey(key)) {
            List<V> l = map.get(key);
            l.add(valueElt);
        } else {
            List<V> l = new ArrayList<>();
            l.add(valueElt);
            map.put(key, l);
        }
    }

    /**
     * Puts a collection of values into a map of key -> list of values.
     * If the specified key is new, it creates an {@link ArrayList} as its
     * value.
     * @param map a {@link Map}.
     * @param key a key.
     * @param values a {@link Collection} of values.
     */
    public static <K, V> void putListMult(Map<K, List<V>> map, K key,
            Collection<? extends V> values) {
        for (V value : values) {
            putList(map, key, value);
        }
    }
    
    /**
     * Puts a value into a map of key -> set of values. If the specified key
     * is new, it creates a {@link HashSet} as its value.
     * @param map a {@link Map}.
     * @param key a key.
     * @param valueElt a value.
     */
    public static <K, V> void putSet(Map<K, Set<V>> map, K key, V valueElt) {
        if (map.containsKey(key)) {
            Set<V> l = map.get(key);
            l.add(valueElt);
        } else {
            Set<V> l = new HashSet<>();
            l.add(valueElt);
            map.put(key, l);
        }
    }
    
    /**
     * Puts a collection of values into a map of key -> set of values.
     * If the specified key is new, it creates an {@link ArrayList} as its
     * value.
     * @param map a {@link Map}.
     * @param key a key.
     * @param values a {@link Collection} of values.
     */
    public static <K, V> void putSetMult(Map<K, Set<V>> map, K key,
            Collection<? extends V> values) {
        for (V value : values) {
            putSet(map, key, value);
        }
    }
    
    /**
     * Copies all of the mappings from the second map to the first. The value
     * for each key is the union of the first and second maps' values for that
     * key.
     * @param <K>
     * @param <V>
     * @param map the first {@link Map}.
     * @param other the second {@link Map}.
     */
    public static <K, V> void putSetAll(Map<K, Set<V>> map, Map<K, Set<V>> other) {
        for (Map.Entry<K, Set<V>> me : other.entrySet()) {
            putSetMult(map, me.getKey(), me.getValue());
        }
    }

    /**
     * Checks whether any of an array's elements are also in the provided set.
     *
     * @param aSet a {@link Set}.
     * @param arr an array.
     * @return <code>true</code> or <code>false</code>.
     */
    public static <K> boolean containsAny(Set<K> aSet, K[] arr) {
        for (K obj : arr) {
            if (aSet.contains(obj)) {
                return true;
            }
        }
        return false;
    }
    
    public static <K> Set<K> intersection(Collection<Set<K>> sets) {
        boolean firstPass = true;
        Set<K> result = new HashSet<>();
        for (Set<K> set : sets) {
            if (firstPass) {
                result.addAll(set);
                firstPass = false;
            } else {
                result.retainAll(set);
            }
        }
        return result;
    }

    @SafeVarargs
    public static <K> Set<K> intersection(Set<K>... sets) {
        boolean firstPass = true;
        Set<K> result = new HashSet<>();
        for (Set<K> set : sets) {
            if (firstPass) {
                result.addAll(set);
                firstPass = false;
            } else {
                result.retainAll(set);
            }
        }
        return result;
    }
    
    public static <K, V> HashMap<K, V> newHashMap(int expectedCapacity) {
        return HASH_MAP_FACTORY.getInstance(expectedCapacity);
    }
    
}
