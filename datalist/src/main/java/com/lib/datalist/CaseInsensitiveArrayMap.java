package com.lib.datalist;


import androidx.collection.ArrayMap;

import java.util.Locale;
import java.util.Map;

class CaseInsensitiveArrayMap extends ArrayMap<String, Object> {

    @Override
    public boolean containsKey(Object key) {
        Object realKey = key.toString().toUpperCase(Locale.getDefault());
        return super.containsKey(realKey);
    }

    @Override
    public Object get(Object key) {
        Object realKey = key.toString().toUpperCase(Locale.getDefault());
        return super.get(realKey);
    }

    @Override
    public Object put(String key, Object value) {
        String oldKey = key.toUpperCase(Locale.getDefault());
        return super.put(oldKey, value);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        for (Entry<? extends String, ?> entry : m.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            this.put(key, value);
        }
    }

    @Override
    public Object remove(Object key) {
        Object realKey = key.toString().toUpperCase(Locale.getDefault());
        return super.remove(realKey);
    }
}