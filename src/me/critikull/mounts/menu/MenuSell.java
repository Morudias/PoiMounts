package me.critikull.mounts.menu;

import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.mount.Mount;
import me.critikull.mounts.mount.Mounts;
import org.bukkit.entity.Player;

public class MenuSell extends Menu {
    private static final int SLOTS = 54;

    private final Mounts mounts;

    public MenuSell(Mounts mounts) {
        super(54, Config.sellShopTitle());
        this.mounts = mounts;
        init();
    }

    private void init() {
        for (int slot = 0; slot < Math.min(54, this.mounts.size()); slot++)
            getInventory().setItem(slot, this.mounts.get(slot).getType().getSellItem());
    }

    public void onClick(Player player, int slot) {
        Mount mount = this.mounts.get(slot);
        if (mount != null) {
            MountsPlugin.getInstance().sellMount(player, mount);
            close(player);
        }
    }

    public static void show(Player player) {
        (new MenuSell(MountsPlugin.getInstance().getMountManager().getMounts(player))).open(player);
    }
}
