package me.critikull.mounts.economy;

import me.critikull.mounts.MountsPlugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public final class EconomyManager {
    private final Economy economy;

    public EconomyManager(Economy economy) {
        this.economy = economy;
    }

    public boolean has(Player player, double amount) {
        return (this.economy.getBalance((OfflinePlayer)player) >= amount);
    }

    public void withdraw(Player player, double amount) {
        this.economy.withdrawPlayer((OfflinePlayer)player, amount);
    }

    public void deposit(Player player, double amount) {
        this.economy.depositPlayer((OfflinePlayer)player, amount);
    }

    public static EconomyManager getEconomyManager() {
        if (MountsPlugin.getInstance().getServer().getPluginManager().getPlugin("Vault") == null)
            return null;
        RegisteredServiceProvider<Economy> rsp = MountsPlugin.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null)
            return null;
        return new EconomyManager((Economy)rsp.getProvider());
    }
}
