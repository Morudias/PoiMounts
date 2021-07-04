package me.critikull.mounts.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public final class SoundUtil {
    public static void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 1.0F, 0.0F);
    }

    public static Sound fromString(String str, Sound def) {
        try {
            Sound sound = Sound.valueOf(str);
            if (sound != null)
                return sound;
        } catch (Exception exception) {}
        return def;
    }
}
