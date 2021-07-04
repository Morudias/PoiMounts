package me.critikull.mounts.mount.requirement;

import me.critikull.mounts.config.Config;
import me.critikull.mounts.utils.AdvancementUtil;
import org.bukkit.entity.Player;

public class RequirementAdvancement extends Requirement {
    private final String key;

    public RequirementAdvancement(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public boolean isSatisfied(Player player) {
        return AdvancementUtil.playerComplete(player, this.key);
    }

    public String getErrorMessage() {
        return Config.advancementErrorMessage();
    }

    public String getHelpMessage(Player player) {
        return Config.requirementHelpMessage(this, player, "messages.requirements.advancement.help", "  <state> &5&oAdvancement '<advancement>'", new String[] { "advancement",

                AdvancementUtil.displayName(this.key) });
    }
}
