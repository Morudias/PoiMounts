package me.critikull.mounts.providers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RegionProvider {
    public boolean canRide(Player player) {
        return true;
    }

    public static RegionProvider get() {
        if (Bukkit.getServer().getPluginManager().getPlugin("WorldGuard") != null)
            return new WorldGuardProvider();
        return new RegionProvider();
    }
}
