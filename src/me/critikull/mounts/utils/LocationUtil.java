package me.critikull.mounts.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtil {
    public static String toString(Location location) {
        return String.format("%s %d %d %d", new Object[] { location.getWorld().getName(), Integer.valueOf(location.getBlockX()), Integer.valueOf(location.getBlockY()), Integer.valueOf(location.getBlockZ()) });
    }

    public static Location fromString(String str) {
        int x, y, z;
        if (str == null)
            return null;
        String[] tokens = str.split(" ");
        if (tokens.length != 4)
            return null;
        World world = Bukkit.getWorld(tokens[0]);
        if (world == null)
            return null;
        try {
            x = Integer.parseInt(tokens[1]);
            y = Integer.parseInt(tokens[2]);
            z = Integer.parseInt(tokens[3]);
        } catch (NumberFormatException e) {
            return null;
        }
        return new Location(world, x, y, z);
    }
}
