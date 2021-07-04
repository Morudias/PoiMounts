package me.critikull.mounts.mount.price;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PriceItems implements Price {
    private final Map<Material, Integer> items;

    public PriceItems(Map<Material, Integer> items) {
        this.items = items;
    }

    public Map<Material, Integer> getItems() {
        return this.items;
    }

    public boolean canAfford(Player player) {
        for (Material material : this.items.keySet()) {
            if (!player.getInventory().contains(material, ((Integer)this.items.get(material)).intValue()))
                return false;
        }
        return true;
    }

    public void withdraw(Player player) {
        for (Material material : this.items.keySet()) {
            player.getInventory().removeItem(new ItemStack[] { new ItemStack(material, ((Integer)this.items.get(material)).intValue()) });
        }
    }

    public void deposit(Player player) {
        for (Material material : this.items.keySet()) {
            player.getInventory().addItem(new ItemStack[] { new ItemStack(material, ((Integer)this.items.get(material)).intValue()) });
        }
    }

    public boolean isSupported() {
        return true;
    }

    private static String capitalizeWords(String str) {
        List<String> words = new ArrayList<>();
        for (String word : str.split("_"))
            words.add(word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase());
        return String.join(" ", (Iterable)words);
    }

    public String toString() {
        List<String> tokens = new ArrayList<>();
        for (Material material : this.items.keySet()) {
            tokens.add(String.format("%dx %s", new Object[] { this.items.get(material), capitalizeWords(material.toString()) }));
        }
        return String.join(", ", (Iterable)tokens);
    }
}