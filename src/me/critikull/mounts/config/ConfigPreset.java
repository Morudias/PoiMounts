package me.critikull.mounts.config;

import me.critikull.mounts.Log;
import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.mount.Mount;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

public final class ConfigPreset {
    private static File getPresetFile() {
        return Paths.get(MountsPlugin.getInstance().getDataFolder().getPath(), new String[] { "preset.yml" }).toFile();
    }

    public static void load() {
        File file = getPresetFile();
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        for (String playerStr : yamlConfiguration.getKeys(false)) {
            UUID playerId = UUID.fromString(playerStr);
            UUID mountId = UUID.fromString(yamlConfiguration.getString(playerStr));
            Mount mount = MountsPlugin.getInstance().getMountManager().getMount(playerId, mountId);
            if (mount != null) {
                Log.info(String.format("Loaded player %s preset %s %s", new Object[] { playerId.toString(), mount.getType().getId(), mountId.toString() }), new Object[0]);
                MountsPlugin.getInstance().getMountManager().setPreset(mount);
            }
        }
    }

    public static void save(Mount mount) {
        File file = getPresetFile();
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.set(mount.getOwnerId().toString(), mount.getId().toString());
        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void remove(Player player) {
        File file = getPresetFile();
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.set(player.getUniqueId().toString(), null);
        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}