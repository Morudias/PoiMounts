package me.critikull.mounts;

import me.critikull.mounts.commands.*;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.config.ConfigDemo;
import me.critikull.mounts.config.ConfigPreset;
import me.critikull.mounts.datastore.ConfigDataStore;
import me.critikull.mounts.datastore.IDataStore;
import me.critikull.mounts.economy.EconomyManager;
import me.critikull.mounts.listeners.ListenerMenu;
import me.critikull.mounts.listeners.ListenerMount;
import me.critikull.mounts.listeners.ListenerToken;
import me.critikull.mounts.mount.Mount;
import me.critikull.mounts.mount.types.MountType;
import me.critikull.mounts.providers.RegionProvider;
import me.critikull.mounts.utils.SoundUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.naming.Name;

public class MountsPlugin extends JavaPlugin {

    public static NamespacedKey TOKENIDKEY;

    private static MountsPlugin instance;

    private final MountManager mountManager = new MountManager();

    private static EconomyManager economyManager;

    private RegionProvider regionControl;

    private IDataStore dataStore;

    private boolean freshInstall;

    public void onEnable() {
        instance = this;
        TOKENIDKEY = new NamespacedKey(getInstance(), "TokenIDKey");
        economyManager = EconomyManager.getEconomyManager();
        this.freshInstall = !getInstance().getDataFolder().exists();
        Config.storeDefaults();
        reload();
        if (this.freshInstall) {
            ConfigDemo.load();
            reload();
        }
        getServer().getPluginManager().registerEvents((Listener)new ListenerMenu(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new ListenerMount(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new ListenerToken(), (Plugin)this);
        getServer().getPluginCommand("mounts").setExecutor((CommandExecutor)new CommandMounts());
        getServer().getPluginCommand("mountsreload").setExecutor((CommandExecutor)new CommandReload());
        getServer().getPluginCommand("mountgive").setExecutor((CommandExecutor)new CommandGive());
        getServer().getPluginCommand("mount").setExecutor((CommandExecutor)new CommandMount());
        getServer().getPluginCommand("mountset").setExecutor((CommandExecutor)new CommandMountSet());
        getServer().getPluginCommand("mountdel").setExecutor((CommandExecutor)new CommandMountDel());
        getServer().getPluginCommand("mountbuy").setExecutor((CommandExecutor)new CommandBuy());
        getServer().getPluginCommand("mountbuycategory").setExecutor((CommandExecutor)new CommandBuyCategory());
        getServer().getPluginCommand("mountsell").setExecutor((CommandExecutor)new CommandSell());
        getServer().getPluginCommand("mountdemo").setExecutor((CommandExecutor)new CommandDemo());
        getServer().getPluginCommand("mounttokengive").setExecutor((CommandExecutor)new CommandGiveToken());
    }

    public void onDisable() {
        getMountManager().storeAll();
    }

    public void onLoad() {
        this.regionControl = RegionProvider.get();
    }

    public static MountsPlugin getInstance() {
        return instance;
    }

    public MountManager getMountManager() {
        return this.mountManager;
    }

    public static EconomyManager getEconomyManager() {
        return economyManager;
    }

    public IDataStore getDataStore() {
        return this.dataStore;
    }

    public static boolean hasEconomyManager() {
        return (economyManager != null);
    }

    public static boolean hasMcMMO() {
        return (instance.getServer().getPluginManager().getPlugin("mcMMO") != null);
    }

    public static boolean hasQuantumRPG() {
        return (instance.getServer().getPluginManager().getPlugin("QuantumRPG") != null);
    }

    public static boolean hasSkillAPI() {
        return (instance.getServer().getPluginManager().getPlugin("SkillAPI") != null);
    }

    public static boolean hasMMOCore() {
        return (instance.getServer().getPluginManager().getPlugin("MMOCore") != null);
    }

    public static boolean hasLibDisguises() {
        return (instance.getServer().getPluginManager().getPlugin("LibsDisguises") != null);
    }

    public void reload() {
        reloadConfig();
        getMountManager().removeAll();
        this.dataStore = (IDataStore)new ConfigDataStore();
        for (MountType mountType : getDataStore().loadMountTypes())
            getMountManager().addMountType(mountType);
        for (Mount mount : getDataStore().loadMounts())
            getMountManager().addMount(mount);
        ConfigPreset.load();
    }

    public Mount giveMount(Player player, MountType mountType) {
        Mount mount = new Mount(player.getUniqueId(), mountType);
        getDataStore().save(mount);
        getMountManager().addMount(mount);
        return mount;
    }

    public void removeMount(Player player, Mount mount) {
        getDataStore().remove(mount);
        getMountManager().removeMount(mount);
    }

    public void buyMount(Player player, MountType mountType) {
        if (mountType.hasPriceBuy()) {
            if (!mountType.getPriceBuy().isSupported()) {
                player.sendMessage(Config.messagePaymentNotSupported());
                return;
            }
            if (!mountType.getPriceBuy().canAfford(player)) {
                player.sendMessage(Config.messageInsufficientFunds(mountType));
                return;
            }
            mountType.getPriceBuy().withdraw(player);
        }
        giveMount(player, mountType);
        player.sendMessage(Config.messageBuyMount(mountType));
        SoundUtil.playSound(player, Config.soundBuyMount());
    }

    public void sellMount(Player player, Mount mount) {
        if (mount.getType().hasPriceSell()) {
            if (!mount.getType().getPriceSell().isSupported()) {
                player.sendMessage(Config.messagePaymentNotSupported());
                return;
            }
            mount.getType().getPriceSell().deposit(player);
        }
        removeMount(player, mount);
        player.sendMessage(Config.messageSellMount(mount));
        SoundUtil.playSound(player, Config.soundSellMount());
    }

    public RegionProvider getRegionControl() {
        return this.regionControl;
    }
}
