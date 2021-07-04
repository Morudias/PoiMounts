package me.critikull.mounts.commands;

import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.config.ConfigPreset;
import me.critikull.mounts.mount.Mount;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class CommandMountSet implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mounts.command.mountset")) {
            sender.sendMessage(Config.messagePermissionDenied());
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.messagePermissionDenied());
            return true;
        }
        Player player = (Player)sender;
        Mount mount = Mount.get(player);
        if (mount == null) {
            sender.sendMessage(Config.messageMountPresetInvalid());
        } else {
            MountsPlugin.getInstance().getMountManager().setPreset(mount);
            ConfigPreset.save(mount);
            player.sendMessage(Config.messageMountPresetSave());
        }
        return true;
    }
}
