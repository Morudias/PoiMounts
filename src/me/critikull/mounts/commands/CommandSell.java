package me.critikull.mounts.commands;

import me.critikull.mounts.config.Config;
import me.critikull.mounts.menu.MenuSell;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSell implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mounts.command.sell")) {
            sender.sendMessage(Config.messagePermissionDenied());
            return true;
        }
        if (args.length != 1)
            return false;
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(Config.messagePlayerNotFound());
            return true;
        }
        MenuSell.show(player);
        return true;
    }
}
