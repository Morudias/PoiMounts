package me.critikull.mounts.mount.requirement;

import org.bukkit.entity.Player;

public abstract class Requirement {
    public abstract boolean isSatisfied(Player paramPlayer);

    public abstract String getErrorMessage();

    public abstract String getHelpMessage(Player paramPlayer);
}
