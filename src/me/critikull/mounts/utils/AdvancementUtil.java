package me.critikull.mounts.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;

public final class AdvancementUtil {
    private static Map<String, String> ACHIEVEMENTS = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
    };

    public static Advancement fromKey(String key) {
        Iterator<Advancement> advancementIterator = Bukkit.getServer().advancementIterator();
        while (advancementIterator.hasNext()) {
            Advancement advancement = advancementIterator.next();
            if (advancement.getKey().getKey().equals(key))
                return advancement;
        }
        return null;
    }

    public static boolean playerComplete(Player player, String key) {
        Advancement advancement = fromKey(key);
        if (advancement != null) {
            AdvancementProgress advancementProgress = player.getAdvancementProgress(advancement);
            return advancementProgress.isDone();
        }
        return false;
    }

    public static String displayName(String key, String def) {
        String displayName = ACHIEVEMENTS.get(key);
        if (displayName != null) {
            if (displayName.equals("Recipe"))
                return String.format("%s: %s", new Object[] { displayName, StringUtil.capitalize(StringUtil.rstrip(key, '/')) });
            return displayName;
        }
        return def;
    }

    public static String displayName(String key) {
        return displayName(key, "");
    }
}
