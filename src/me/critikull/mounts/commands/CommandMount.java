package me.critikull.mounts.commands;

import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.mount.Mount;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class CommandMount implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mounts.command.mount")) {
            sender.sendMessage(Config.messagePermissionDenied());
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.messagePermissionDenied());
            return true;
        }
        Player player = (Player)sender;
        if (args.length == 0) {
            Mount mount = MountsPlugin.getInstance().getMountManager().getPreset(player);
            if (mount == null) {
                sender.sendMessage(Config.messageMountPresetNotFound());
                return true;
            }
            mount.ride(player);
        } else {
            Mount mount = MountsPlugin.getInstance().getMountManager().getMountById(player, args[0]);
            if (mount == null) {
                sender.sendMessage(Config.messageMountNotFound());
                return true;
            }
            mount.ride(player);
        }
        return true;
    }
}