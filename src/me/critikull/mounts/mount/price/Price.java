package me.critikull.mounts.mount.price;

import org.bukkit.entity.Player;

public interface Price {
    boolean canAfford(Player paramPlayer);

    void withdraw(Player paramPlayer);

    void deposit(Player paramPlayer);

    boolean isSupported();
}
