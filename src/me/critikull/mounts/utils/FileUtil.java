package me.critikull.mounts.utils;

import java.io.File;

public final class FileUtil {
    public static String getFileName(File file) {
        if (file == null)
            return null;
        int pos = file.getName().lastIndexOf(".");
        if (pos == -1)
            return file.getName();
        return file.getName().substring(0, pos);
    }
}
