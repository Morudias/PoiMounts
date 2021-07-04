package me.critikull.mounts.listeners;

import me.critikull.mounts.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public final class ListenerMenu implements Listener {
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onImmutableInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof Menu) {
            Menu gui = (Menu)e.getInventory().getHolder();
            if (e.getRawSlot() < e.getInventory().getSize() &&
                    e.getWhoClicked() instanceof Player) {
                Player player = (Player)e.getWhoClicked();
                gui.onClick(player, e.getSlot());
            }
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getHolder() instanceof Menu && e.getPlayer() instanceof Player) {
            Menu gui = (Menu)e.getInventory().getHolder();
            Player player = (Player)e.getPlayer();
            gui.onClose(player);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInventoryDrag(InventoryDragEvent e) {
        if (e.getInventory().getHolder() instanceof Menu)
            e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInventoryInteract(InventoryInteractEvent e) {
        if (e.getInventory().getHolder() instanceof Menu)
            e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInventoryMoveItem(InventoryMoveItemEvent e) {
        if (e.getInitiator().getHolder() instanceof Menu || e.getDestination().getHolder() instanceof Menu)
            e.setCancelled(true);
    }
}
