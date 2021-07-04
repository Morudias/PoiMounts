package me.critikull.mounts.mount.requirement.mcmmo;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.SkillType;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.mount.requirement.Requirement;
import org.bukkit.entity.Player;

public class RequirementSkillLevel extends Requirement {
    private final int minLevel;

    private final SkillType skillType;

    public RequirementSkillLevel(int minLevel, SkillType skillType) {
        this.minLevel = minLevel;
        this.skillType = skillType;
    }

    public int getLevel() {
        return this.minLevel;
    }

    public SkillType getSkillType() {
        return this.skillType;
    }

    public boolean isSatisfied(Player player) {
        return (ExperienceAPI.getLevel(player, String.valueOf(this.skillType)) >= this.minLevel);
    }

    public String getErrorMessage() {
        return Config.mcmmoSkillLevelErrorMessage(this.skillType.getName());
    }

    public String getHelpMessage(Player player) {
        return Config.requirementHelpMessage(this, player, "messages.requirements.mcmmo.skillLevel.help", "  <state> &5&o<skill> >= <level>", new String[] { "skill", this.skillType
                .getName(), "level",
                String.format("%d", new Object[] { Integer.valueOf(this.minLevel) }) });
    }
}
