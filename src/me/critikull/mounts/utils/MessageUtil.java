package me.critikull.mounts.utils;

import java.util.regex.Matcher;
import org.bukkit.ChatColor;

public final class MessageUtil {
    private String message;

    public MessageUtil(String message) {
        this.message = message;
    }

    public String colorize() {
        return colorize(this.message);
    }

    public MessageUtil replace(String key, String val) {
        this.message = this.message.replaceAll("\\<" + key + "\\>", Matcher.quoteReplacement(val));
        return this;
    }

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static MessageUtil create(String message) {
        return new MessageUtil(message);
    }
}
