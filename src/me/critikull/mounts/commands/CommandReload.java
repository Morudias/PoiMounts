package me.critikull.mounts.commands;

import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class CommandReload implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mounts.command.reload")) {
            sender.sendMessage(Config.messagePermissionDenied());
            return true;
        }
        MountsPlugin.getInstance().reload();
        sender.sendMessage(Config.messageConfigReload());
        return true;
    }
}
