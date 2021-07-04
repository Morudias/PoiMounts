package me.critikull.mounts.commands;

import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.mount.Mount;
import me.critikull.mounts.mount.types.MountType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class CommandGive implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mounts.command.give")) {
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
        MountType type = MountsPlugin.getInstance().getMountManager().getMountType(args[1]);
        for(Mount ownedMount : MountsPlugin.getInstance().getMountManager().getMounts(player)) {
            if(ownedMount.getType().equals(type)) {
                player.sendMessage(ChatColor.RED + "You already have that mount");
                return true;
            }
        }
        if (type == null) {
            sender.sendMessage(Config.messageMountNotFound());
            return true;
        }
        Mount mount = MountsPlugin.getInstance().giveMount(player, type);
        sender.sendMessage(Config.messageGive(player, mount));
        return true;
    }
}
