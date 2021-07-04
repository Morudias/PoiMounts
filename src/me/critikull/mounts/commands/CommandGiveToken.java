package me.critikull.mounts.commands;

import me.critikull.mounts.config.Config;
import me.critikull.mounts.datastore.ConfigDataStore;
import me.critikull.mounts.mount.types.MountType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandGiveToken implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mounts.command.givetoken")) {
            sender.sendMessage(Config.messagePermissionDenied());
            return true;
        }
        if (args.length != 2)
            return false;
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(Config.messagePlayerNotFound());
            return true;
        }
        for(MountType loadedType : new ConfigDataStore().loadMountTypes()) {
            if(loadedType.getId().equals(args[1])) {
                if(loadedType.getTokenItem() == null) {
                    player.sendMessage("That mount doesn't have a token item");
                    return true;
                }
                ItemStack itemToken = loadedType.getTokenItem();
                if(player.getInventory().firstEmpty() == -1) {
                    player.getLocation().getWorld().dropItemNaturally(player.getLocation(), itemToken);
                    player.sendMessage(ChatColor.YELLOW + "Inventory full, dropping item.");
                } else {
                    player.getInventory().addItem(itemToken);

                }
            }
        }
        return true;
    }

}
