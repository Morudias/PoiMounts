package me.critikull.mounts.mount.requirement.mcmmo;

import com.gmail.nossr50.api.ExperienceAPI;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.mount.requirement.Requirement;
import org.bukkit.entity.Player;

public class RequirementPowerLevel extends Requirement {
    private final int powerLevel;

    public RequirementPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public int getPowerLevel() {
        return this.powerLevel;
    }

    public boolean isSatisfied(Player player) {
        return (ExperienceAPI.getPowerLevel(player) >= this.powerLevel);
    }

    public String getErrorMessage() {
        return Config.mcmmoPowerLevelErrorMessage();
    }

    public String getHelpMessage(Player player) {
        return Config.requirementHelpMessage(this, player, "messages.requirements.mcmmo.powerLevel.help", "  <state> &5&oPower Level >= <powerLevel>", new String[] { "powerLevel",

                String.format("%d", new Object[] { Integer.valueOf(this.powerLevel) }) });
    }
}
