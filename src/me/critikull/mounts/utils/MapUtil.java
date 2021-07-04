package me.critikull.mounts.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public final class MapUtil {
    public static <K, V> Map<K, V> mapOf(K key, V value, Object... alteratingsKeysAndValues) {
        Map<K, V> map = new LinkedHashMap<>();
        map.put(key, value);
        for (int i = 0; i < alteratingsKeysAndValues.length; i += 2)
            map.put((K)alteratingsKeysAndValues[i], (V)alteratingsKeysAndValues[i + 1]);
        return map;
    }
}
