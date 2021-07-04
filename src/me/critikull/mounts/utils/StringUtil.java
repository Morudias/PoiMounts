package me.critikull.mounts.utils;

import java.util.ArrayList;
import java.util.List;

public final class StringUtil {
    public static String rstrip(String str, char delim) {
        String[] components = str.split("/");
        return components[components.length - 1];
    }

    public static String capitalize(String str) {
        List<String> words = new ArrayList<>();
        for (String word : str.split("_"))
            words.add(word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase());
        return String.join(" ", (Iterable)words);
    }
}
