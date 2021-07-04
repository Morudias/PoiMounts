package me.critikull.mounts.utils;

public final class TimeUtil {
    public static long milliseconds() {
        return System.currentTimeMillis();
    }

    public static long seconds() {
        return milliseconds() / 1000L;
    }
}
