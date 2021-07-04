package me.critikull.mounts.commands;

import java.util.Arrays;

import me.critikull.mounts.MountManager;
import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.menu.MenuBuy;
import me.critikull.mounts.mount.Mount;
import me.critikull.mounts.mount.types.MountType;
import me.critikull.mounts.mount.types.MountTypes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBuy implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mounts.command.buy")) {
            sender.sendMessage(Config.messagePermissionDenied());
            return true;
        }
        if (args.length < 1)
            return false;
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(Config.messagePlayerNotFound());
            return true;
        }
        if (args.length > 1) {
            MountTypes mountTypes = new MountTypes();
            for (MountType mountType : MountsPlugin.getInstance().getMountManager().getMountTypes(player)) {
                for (String mountName : (String[])Arrays.<String>copyOfRange(args, 1, args.length)) {
                    if (mountType.getId().equalsIgnoreCase(mountName)) {
                        for(Mount ownedMount : MountsPlugin.getInstance().getMountManager().getMounts(player)) {
                            if(ownedMount.getType().equals(mountType)) {
                                player.sendMessage(ChatColor.RED + "You already have that mount");
                                return true;
                            }
                        }
                        mountTypes.add(mountType);
                    }
                }
            }
            MenuBuy.show(player, mountTypes);
        } else {
            MenuBuy.show(player);
        }
        return true;
    }
}
