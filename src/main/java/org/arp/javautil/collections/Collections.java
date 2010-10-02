package org.arp.javautil.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Extra utilities for collections.
 * 
 * @author Andrew Post
 */
public class Collections {

    private Collections() {
    }

    /**
     * Return a string which is the concatenation of the strings in the
     * collection (or the string representations of each object in the
     * collection).
     *
     * @param c
     *            the <code>Collection</code>. If null, an empty
     *            <code>String</code> will be returned.
     * @param separator
     *            the <code>String</code> separator between elements. If
     *            <code>null</code>, <code>""</code> is used.
     * @return a <code>String</code>.
     */
    public static String join(Collection c, String separator) {
        if (c == null) {
            return "";
        } else {
            return Iterators.join(c.iterator(), separator);
        }
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
            List<V> l = new ArrayList<V>();
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
     * @param values a {@link Collection<? extends V>} of values.
     */
    public static <K, V> void putListAll(Map<K, List<V>> map, K key,
            Collection<? extends V> values) {
        for (V value : values)
            putList(map, key, value);
    }
}
