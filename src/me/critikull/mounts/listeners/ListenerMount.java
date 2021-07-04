package me.critikull.mounts.listeners;

import me.critikull.mounts.config.Config;
import me.critikull.mounts.mount.Mount;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

public final class ListenerMount implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onUnmount(VehicleExitEvent e) {
        Mount mount = Mount.get((Entity)e.getVehicle());
        if (mount != null)
            mount.store();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent e) {
        Mount mount = Mount.get(e.getPlayer());
        if (mount != null)
            mount.store();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDeath(PlayerQuitEvent e) {
        Mount mount = Mount.get(e.getPlayer());
        if (mount != null)
            mount.store();
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onMountDamage(EntityDamageEvent e) {
        Mount mount = Mount.get(e.getEntity());
        if (mount != null)
            e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageEvent e) {
        if (Config.unmountOnDamage() &&
                e.getEntity() instanceof Player) {
            Player player = (Player)e.getEntity();
            Mount mount = Mount.get(player);
            if (mount != null)
                mount.store();
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (e.getInventory().getHolder() instanceof Entity) {
            Entity entity = (Entity)e.getInventory().getHolder();
            Mount mount = Mount.get(entity);
            if (mount != null)
                e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        Mount mount = Mount.get(e.getPlayer());
        if (mount != null)
            mount.store();
    }
}