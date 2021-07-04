package me.critikull.mounts.utils;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class ItemStackUtil {
    public static ItemStack create(Material material, String displayName) {
        return create(material, displayName, new ArrayList<>());
    }

    public static ItemStack create(Material material, String displayName, List<String> description) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtil.colorize(displayName));
        meta.setLore(description);
        item.setItemMeta(meta);
        return item;
    }

    public static void addLore(ItemStack item, List<String> lines) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        for (String line : lines)
            lore.add(MessageUtil.colorize(line));
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
}
