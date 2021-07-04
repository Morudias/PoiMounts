package me.critikull.mounts.menu;

import java.util.Arrays;
import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.mount.Mount;
import me.critikull.mounts.mount.Mounts;
import me.critikull.mounts.utils.ItemStackUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MenuMounts extends Menu {
    private static final int SLOTS = 27; //TODO: Make this paginated

    private final Mounts mounts;

    public MenuMounts(Player player, Mounts mounts) {
        super(27, Config.mountsTitle());
        this.mounts = mounts;
        init(player);
        open(player);
    }

    private void init(Player player) {
        for (int slot = 0; slot < Math.min(27, this.mounts.size()); slot++) {
            ItemStack item = this.mounts.get(slot).getItem(player);
            ItemStackUtil.addLore(item, Arrays.asList(new String[] { "", Config.messageRideLore() }));
            getInventory().setItem(slot, item);
        }
    }

    public void onClick(Player player, int slot) {
        Mount mount = this.mounts.get(slot);
        if (mount != null) {
            mount.ride(player);
            close(player);
        }
    }

    public static void show(Player player) {
        if (!Mount.isRiding(player))
            new MenuMounts(player, MountsPlugin.getInstance().getMountManager().getMounts(player));
    }
}
