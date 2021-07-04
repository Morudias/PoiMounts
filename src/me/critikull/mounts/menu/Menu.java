package me.critikull.mounts.menu;

import me.critikull.mounts.MountsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;

public abstract class Menu implements InventoryHolder {
    protected final Inventory inventory;

    public Menu(int size, String title) {
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void open(Player player) {
        player.openInventory(this.inventory);
    }

    public void close(final Player player) {
        MountsPlugin.getInstance().getServer().getScheduler().runTaskLater((Plugin)MountsPlugin.getInstance(), new Runnable() {
            public void run() {
                player.closeInventory();
            }
        },  1L);
    }

    public void onClick(Player player, int slot) {}

    public void onClose(Player player) {}
}
