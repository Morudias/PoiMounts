package me.critikull.mounts.commands;

import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.datastore.ConfigDataStore;
import me.critikull.mounts.mount.Mount;
import me.critikull.mounts.mount.types.MountType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandGiveToken implements CommandExecutor, TabCompleter {

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
        for(MountType loadedType : MountsPlugin.ALL_MOUNTS) {
            if(loadedType.getId().equals(args[1])) {
                if(loadedType.getTokenItem() == null) {
                    player.sendMessage(ChatColor.RED + "That mount doesn't have a token item.");
                    return true;
                }
                ItemStack itemToken = loadedType.getTokenItem();
                //if they have an empty slot
                if(player.getInventory().firstEmpty() != -1) {
                    player.getInventory().addItem(itemToken);
                    player.sendMessage(String.format(ChatColor.YELLOW + "You got a %s%s mount %stoken.", ChatColor.GOLD,loadedType.getId().replaceAll("_"," "), ChatColor.YELLOW));
                    return true;
                }
                //loop through all item metas in inv and check if not null and equal to item to give
                for(ItemMeta invMeta : Arrays.stream(player.getInventory().getContents()).filter(s -> s != null).map(s -> s.getItemMeta()).collect(Collectors.toList())) {
                    if(invMeta == null || !(invMeta.equals(itemToken.getItemMeta()))) {
                        continue;
                    }
                    player.getInventory().addItem(itemToken);
                    player.sendMessage(String.format(ChatColor.YELLOW + "You got a %s%s mount %stoken.", ChatColor.GOLD,loadedType.getId().replaceAll("_"," "), ChatColor.YELLOW));
                    return true;
                }
                player.getLocation().getWorld().dropItemNaturally(player.getLocation(), itemToken);
                player.sendMessage(String.format(ChatColor.YELLOW + "Your inventory was full, dropping %s%s mount %stoken.", ChatColor.GOLD, loadedType.getId().replaceAll("_"," "), ChatColor.YELLOW)); //TODO: This procs if one of the empty stacks is the item
                return true;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("mounts.command.givetoken")) {
            return null;
        }
        if(args.length == 2) {
            return MountsPlugin.ALL_MOUNTS.stream().map(s -> s.getId()).filter(s -> s.startsWith(args[1])).collect(Collectors.toList());
        }
        return null;
    }
}
