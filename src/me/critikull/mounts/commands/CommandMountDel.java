package me.critikull.mounts.commands;

import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.config.ConfigPreset;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class CommandMountDel implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mounts.command.mountdel")) {
            sender.sendMessage(Config.messagePermissionDenied());
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.messagePermissionDenied());
            return true;
        }
        Player player = (Player)sender;
        MountsPlugin.getInstance().getMountManager().removePreset(player);
        sender.sendMessage(Config.messageMountPresetRemove());
        ConfigPreset.remove(player);
        return true;
    }
}
