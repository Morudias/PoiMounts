package me.critikull.mounts.listeners;

import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.datastore.ConfigDataStore;
import me.critikull.mounts.mount.types.MountType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ListenerToken implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onTokenRedeem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.LEFT_CLICK_AIR)) {
            return;
        }
        if(player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            return;
        }
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        ItemMeta meta = heldItem.getItemMeta();
        if(!meta.getPersistentDataContainer().has(MountsPlugin.TOKENIDKEY, PersistentDataType.STRING)) {
            return;
        }
        for(MountType type : new ConfigDataStore().loadMountTypes()) {
            if(meta.getPersistentDataContainer().get(MountsPlugin.TOKENIDKEY, PersistentDataType.STRING).equals(type.getId())) {
                MountType foundMount = type;
                //Check if they already own that mount
                for(MountType ownedMount : MountsPlugin.getInstance().getMountManager().getMountTypes(player)) {
                    if(ownedMount.getId().equals(foundMount.getId())) {
                        player.sendMessage(ChatColor.RED + "You already have that mount");
                        return;
                    }
                }
                MountsPlugin.getInstance().giveMount(player, foundMount);
                player.sendMessage("You got the mount");
            }
            else {
                continue;
            }
        }
    }

}
