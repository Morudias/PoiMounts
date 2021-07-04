package me.critikull.mounts.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class ArrayUtil {
    public static List<String> toStringList(List<?> objects) {
        List<String> strings = new ArrayList<>();
        for (Object object : objects)
            strings.add(object.toString());
        return strings;
    }

    public static List<String> toStringList(Set<?> objects) {
        List<String> strings = new ArrayList<>();
        for (Object object : objects)
            strings.add(object.toString());
        return strings;
    }
}
