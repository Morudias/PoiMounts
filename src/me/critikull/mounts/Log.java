package me.critikull.mounts;

import me.critikull.mounts.config.Config;
import org.bukkit.Bukkit;

public final class Log {
    public static void info(String format, Object... args) {
        if (Config.isDebug())
            Bukkit.getLogger().info(String.format(format, args));
    }
}
