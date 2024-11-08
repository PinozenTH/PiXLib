package com.pinont.piXLib.api.utils;

import java.util.HashMap;

public class Count {

    HashMap<Object, Integer> map = new HashMap<>();

    public void put(Object k, Integer v) {
        map.put(k, v);
    }

    public void add(Object k, Integer v) {
        if (map.containsKey(k)) {
            map.put(k, map.get(k) + v);
            return;
        } map.put(k, v);
    }

    public void remove(Object k) {
        map.remove(k);
    }

    public void clear() {
        map.clear();
    }

    public boolean containsKey(Object k) {
        return map.containsKey(k);
    }

    public boolean containsValue(Integer v) {
        return map.containsValue(v);
    }

    public Integer get(Object k) {
        return map.get(k);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void reset(Object k) {
        map.replace(k, 0);
    }

}
