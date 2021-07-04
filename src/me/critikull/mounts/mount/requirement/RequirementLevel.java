package me.critikull.mounts.mount.requirement;

import me.critikull.mounts.config.Config;
import org.bukkit.entity.Player;

public class RequirementLevel extends Requirement {
    private final int minLevel;

    public RequirementLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getLevel() {
        return this.minLevel;
    }

    public boolean isSatisfied(Player player) {
        return (player.getLevel() >= this.minLevel);
    }

    public String getErrorMessage() {
        return Config.levelErrorMessage();
    }

    public String getHelpMessage(Player player) {
        return Config.requirementHelpMessage(this, player, "messages.requirements.level.help", "  <state> &5&oLevel >= <level>", new String[] { "level",
                String.format("%d", new Object[] { Integer.valueOf(this.minLevel) }) });
    }
}
