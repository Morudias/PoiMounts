package me.critikull.mounts.commands;

import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.config.ConfigDemo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class CommandDemo implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mounts.command.demo")) {
            sender.sendMessage(Config.messagePermissionDenied());
            return true;
        }
        ConfigDemo.load();
        MountsPlugin.getInstance().reload();
        sender.sendMessage(Config.messageConfigDemo());
        return true;
    }
}
