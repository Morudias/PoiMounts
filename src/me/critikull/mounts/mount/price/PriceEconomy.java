package me.critikull.mounts.mount.price;

import me.critikull.mounts.MountsPlugin;
import org.bukkit.entity.Player;

public class PriceEconomy implements Price {
    private final double price;

    public PriceEconomy(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean canAfford(Player player) {
        return MountsPlugin.getEconomyManager().has(player, this.price);
    }

    public void withdraw(Player player) {
        MountsPlugin.getEconomyManager().withdraw(player, this.price);
    }

    public void deposit(Player player) {
        MountsPlugin.getEconomyManager().deposit(player, this.price);
    }

    public boolean isSupported() {
        return MountsPlugin.hasEconomyManager();
    }

    public String toString() {
        return String.format("$%,.2f", new Object[] { Double.valueOf(this.price) });
    }
}
