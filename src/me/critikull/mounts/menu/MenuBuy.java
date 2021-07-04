package me.critikull.mounts.menu;

import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.mount.types.MountType;
import me.critikull.mounts.mount.types.MountTypes;
import org.bukkit.entity.Player;

public class MenuBuy extends Menu {
    private static final int SLOTS = 54;

    private final MountTypes mountTypes;

    public MenuBuy(MountTypes mountTypes) {
        super(54, Config.buyShopTitle());
        this.mountTypes = mountTypes;
        init();
    }

    private void init() {
        for (int slot = 0; slot < Math.min(54, this.mountTypes.size()); slot++)
            getInventory().setItem(slot, this.mountTypes.get(slot).getPurchaseItem());
    }

    public void onClick(Player player, int slot) {
        MountType mountType = this.mountTypes.get(slot);
        if (mountType != null) {
            MountsPlugin.getInstance().buyMount(player, mountType);
            close(player);
        }
    }

    public static void show(Player player, MountTypes mountTypes) {
        (new MenuBuy(mountTypes)).open(player);
    }

    public static void show(Player player) {
        (new MenuBuy(MountsPlugin.getInstance().getMountManager().getMountTypes(player))).open(player);
    }
}
