package me.critikull.mounts.config;

import com.gmail.nossr50.datatypes.skills.SkillType;
import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.mount.Mount;
import me.critikull.mounts.mount.requirement.Requirement;
import me.critikull.mounts.mount.types.MountType;
import me.critikull.mounts.utils.MessageUtil;
import me.critikull.mounts.utils.SoundUtil;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Config {
    private static final String DEBUG_KEY = "debug";

    private static final String DATASTORE_TYPE_KEY = "dataStore.type";

    private static final String DATASTORE_MYSQL_HOST_KEY = "dataStore.mysql.host";

    private static final String DATASTORE_MYSQL_PORT_KEY = "dataStore.mysql.port";

    private static final String DATASTORE_MYSQL_DATABASE_KEY = "dataStore.mysql.database";

    private static final String DATASTORE_MYSQL_USER_KEY = "dataStore.mysql.user";

    private static final String DATASTORE_MYSQL_PASS_KEY = "dataStore.mysql.pass";

    private static final String MESSAGES_PURCHASE_LORE_KEY = "messages.purchaseLore";

    private static final String MESSAGES_SELL_LORE_KEY = "messages.sellLore";

    private static final String MESSAGES_RIDE_LORE_KEY = "messages.rideLore";

    private static final String MESSAGES_PRICE_BUY_FREE_KEY = "messages.priceBuyFree";

    private static final String MESSAGES_PRICE_SELL_NOTHING_KEY = "messages.priceSellNothing";

    private static final String MESSAGES_REQUIREMENTS_SATISFIED_KEY = "messages.requirementSatisfied";

    private static final String MESSAGES_REQUIREMENTS_NOT_SATISFIED_KEY = "messages.requirementNotSatisfied";

    private static final String MESSAGES_CONFIG_RELOAD_KEY = "messages.configReload";

    private static final String MESSAGES_CONFIG_DEMO_KEY = "messages.configDemo";

    private static final String MESSAGES_GIVE_KEY = "messages.give";

    private static final String MESSAGES_PERMISSION_DENIED_KEY = "messages.permissionDenied";

    private static final String MESSAGES_PLAYER_NOT_FOUND_KEY = "messages.playerNotFound";

    private static final String MESSAGES_MOUNT_NOT_FOUND_KEY = "messages.mountNotFound";

    private static final String MESSAGES_MOUNT_COOLDOWN_KEY = "messages.mountCooldown";

    private static final String MESSAGES_MOUNT_REQUIREMENT_KEY = "messages.mountRequirement";

    private static final String MESSAGES_MOUNT_PRESET_NOT_FOUND_KEY = "messages.mountPresetNotFound";

    private static final String MESSAGES_MOUNT_PRESET_INVALID_KEY = "messages.mountPresetInvalid";

    private static final String MESSAGES_MOUNT_PRESET_SAVE_KEY = "messages.mountPresetSave";

    private static final String MESSAGES_MOUNT_PRESET_REMOVE_KEY = "messages.mountPresetRemove";

    private static final String MESSAGES_PAYMENT_NOT_SUPPORTED_KEY = "messages.paymentNotSupported";

    private static final String MESSAGES_MOUNT_BUY_KEY = "messages.mountBuy";

    private static final String MESSAGES_MOUNT_SELL_KEY = "messages.mountSell";

    private static final String MESSAGES_INSUFFICIENT_FUNDS_KEY = "messages.insufficientFunds";

    private static final String MESSAGES_SELL_SHOP_TITLE_KEY = "messages.sellShopTitle";

    private static final String MESSAGES_BUY_SHOP_TITLE_KEY = "messages.buyShopTitle";

    private static final String MESSAGES_MOUNTS_TITLE_KEY = "messages.mountsTitle";

    private static final String MESSAGES_MOUNT_CANNOT_RIDE_KEY = "messages.mountCannotRide";

    private static final String SOUNDS_RIDE_MOUNT_KEY = "sounds.rideMount";

    private static final String SOUNDS_STORE_MOUNT_KEY = "sounds.storeMount";

    private static final String SOUNDS_BUY_MOUNT_KEY = "sounds.buyMount";

    private static final String SOUNDS_SELL_MOUNT_KEY = "sounds.sellMount";

    private static final String MOUNT_COOLDOWN_SECONDS_KEY = "mountCooldownSeconds";

    private static final String UNMOUNT_ON_DAMAGE_KEY = "unmountOnDamage";

    private static final String SILENCE_MOUNT_KEY = "silenceMount";

    private static final String LORE_KEY = "lore";

    private static final String MESSAGES_REQUIREMENTS_NONE_KEY = "messages.requirements.none";

    public static final String MESSAGES_REQUIREMENTS_LEVEL_ERROR_KEY = "messages.requirements.level.error";

    public static final String MESSAGES_REQUIREMENTS_LEVEL_HELP_KEY = "messages.requirements.level.help";

    public static final String MESSAGES_REQUIREMENTS_MCMMO_POWERLEVEL_ERROR_KEY = "messages.requirements.mcmmo.powerLevel.error";

    public static final String MESSAGES_REQUIREMENTS_MCMMO_POWERLEVEL_HELP_KEY = "messages.requirements.mcmmo.powerLevel.help";

    public static final String MESSAGES_REQUIREMENTS_MCMMO_SKILLLEVEL_ERROR_KEY = "messages.requirements.mcmmo.skillLevel.error";

    public static final String MESSAGES_REQUIREMENTS_MCMMO_SKILLLEVEL_HELP_KEY = "messages.requirements.mcmmo.skillLevel.help";

    public static final String MESSAGES_REQUIREMENTS_MMOCORE_ATTRIBUTE_ERROR_KEY = "messages.requirements.mmocore.attribute.error";

    public static final String MESSAGES_REQUIREMENTS_MMOCORE_ATTRIBUTE_HELP_KEY = "messages.requirements.mmocore.attribute.help";

    public static final String MESSAGES_REQUIREMENTS_MMOCORE_CLASS_ERROR_KEY = "messages.requirements.mmocore.class.error";

    public static final String MESSAGES_REQUIREMENTS_MMOCORE_CLASS_HELP_KEY = "messages.requirements.mmocore.class.help";

    public static final String MESSAGES_REQUIREMENTS_MMOCORE_LEVEL_ERROR_KEY = "messages.requirements.mmocore.level.error";

    public static final String MESSAGES_REQUIREMENTS_MMOCORE_LEVEL_HELP_KEY = "messages.requirements.mmocore.level.help";

    public static final String MESSAGES_REQUIREMENTS_MMOCORE_PROFESSION_ERROR_KEY = "messages.requirements.mmocore.profession.error";

    public static final String MESSAGES_REQUIREMENTS_MMOCORE_PROFESSION_HELP_KEY = "messages.requirements.mmocore.profession.help";

    public static final String MESSAGES_REQUIREMENTS_QUANTUMRPG_CLASS_ERROR_KEY = "messages.requirements.quantumrpg.class.error";

    public static final String MESSAGES_REQUIREMENTS_QUANTUMRPG_CLASS_HELP_KEY = "messages.requirements.quantumrpg.class.help";

    public static final String MESSAGES_REQUIREMENTS_QUANTUMRPG_LEVEL_ERROR_KEY = "messages.requirements.quantumrpg.level.error";

    public static final String MESSAGES_REQUIREMENTS_QUANTUMRPG_LEVEL_HELP_KEY = "messages.requirements.quantumrpg.level.help";

    public static final String MESSAGES_REQUIREMENTS_SKILLAPI_CLASS_ERROR_KEY = "messages.requirements.skillapi.class.error";

    public static final String MESSAGES_REQUIREMENTS_SKILLAPI_CLASS_HELP_KEY = "messages.requirements.skillapi.class.help";

    public static final String MESSAGES_REQUIREMENTS_SKILLAPI_LEVEL_ERROR_KEY = "messages.requirements.skillapi.level.error";

    public static final String MESSAGES_REQUIREMENTS_SKILLAPI_LEVEL_HELP_KEY = "messages.requirements.skillapi.level.help";

    public static final String MESSAGES_REQUIREMENTS_SKILLAPI_SKILLLEVEL_ERROR_KEY = "messages.requirements.skillapi.skillLevel.error";

    public static final String MESSAGES_REQUIREMENTS_SKILLAPI_SKILLLEVEL_HELP_KEY = "messages.requirements.skillapi.skillLevel.help";

    public static final String MESSAGES_REQUIREMENTS_ADVANCEMENT_ERROR_KEY = "messages.requirements.advancement.error";

    public static final String MESSAGES_REQUIREMENTS_ADVANCEMENT_HELP_KEY = "messages.requirements.advancement.help";

    private static final boolean DEFAULT_DEBUG = false;

    private static final String DEFAULT_DATASTORE_TYPE = "config";

    private static final String DEFAULT_DATASTORE_MYSQL_HOST = "localhost";

    private static final int DEFAULT_DATASTORE_MYSQL_PORT = 3306;

    private static final String DEFAULT_DATASTORE_MYSQL_DATABASE = "minecraft";

    private static final String DEFAULT_DATASTORE_MYSQL_USER = "root";

    private static final String DEFAULT_DATASTORE_MYSQL_PASS = "";

    private static final String DEFAULT_CONFIG_RELOAD_MESSAGE = "&e[Mounts] Configuration reloaded!";

    private static final String DEFAULT_CONFIG_DEMO_MESSAGE = "&e[Mounts] Demo configuration loaded!";

    private static final String DEFAULT_GIVE_MESSAGE = "&eGave <player> a mount: <mount>!";

    private static final String DEFAULT_PERMISSION_DENIED_MESSAGE = "&4Error: &cPermission denied!";

    private static final String DEFAULT_PLAYER_NOT_FOUND_MESSAGE = "&4Error: &cPlayer not found!";

    private static final String DEFAULT_MOUNT_NOT_FOUND_MESSAGE = "&4Error: &cMount not found!";

    private static final String DEFAULT_MOUNT_COOLDOWN_KEY = "&cYou must wait before mounting again!";

    private static final String DEFAULT_MOUNT_REQUIREMENT_MESSAGE = "&cCannot use mount. ";

    private static final String DEFAULT_MOUNT_PRESET_NOT_FOUND_MESSAGE = "&cMount preset not found. Use /mset.";

    private static final String DEFAULT_MOUNT_PRESET_INVALID_MESSAGE = "&cYou must be riding a mount.";

    private static final String DEFAULT_MOUNT_PRESET_SAVE_MESSAGE = "&eMount preset saved!";

    private static final String DEFAULT_MOUNT_PRESET_REMOVE_MESSAGE = "&eMount preset removed!";

    private static final String DEFAULT_MOUNT_CANNOT_RIDE_MESSAGE = "&cYou cannot do that right now!";

    private static final String DEFAULT_PAYMENT_NOT_SUPPORTED_MESSAGE = "&cError: Payment not supported!";

    private static final String DEFAULT_MOUNT_BUY_MESSAGE = "&aYou purchased a mount: <mount>!";

    private static final String DEFAULT_MOUNT_SELL_MESSAGE = "&aYou sold a mount: <mount>!";

    private static final String DEFAULT_INSUFFICIENT_FUNDS_MESSAGE = "&cYou cannot afford this mount: <mount>!";

    private static final String DEFAULT_BUY_SHOP_TITLE_MESSAGE = "Purchase Mount";

    private static final String DEFAULT_SELL_SHOP_TITLE_MESSAGE = "Sell Mount";

    private static final String DEFAULT_MESSAGES_MOUNTS_TITLE = "Mounts";

    private static final List<String> DEFAULT_LORE = Arrays.asList(new String[] { "&9<breed>", "", "&7&oSpeed Boost +<speedBoost>", "&7&oJump Boost +<jumpBoost>", "", "&5&oRequirements:", "<requirements>", "", "&9Price: &a<priceBuy>", "&9Sells for: &a<priceSell>" });

    private static final String DEFAULT_MESSAGES_REQUIREMENTS_NONE = "  &5&oNone";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_LEVEL_ERROR = "&cYour level is too low.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_LEVEL_HELP = "  <state> &5&oLevel >= <level>";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_MCMMO_POWERLEVEL_ERROR = "&cYour power level is too low.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_MCMMO_POWERLEVEL_HELP = "  <state> &5&oPower Level >= <powerLevel>";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_MCMMO_SKILLLEVEL_ERROR = "&cYour <skill> skill level is too low.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_MCMMO_SKILLLEVEL_HELP = "  <state> &5&o<skill> >= <level>";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_MMOCORE_ATTRIBUTE_ERROR = "&cYour <attribute> attribute is too low.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_MMOCORE_ATTRIBUTE_HELP = "  <state> &5&o<attribute> >= <level>";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_MMOCORE_CLASS_ERROR = "&cWrong class.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_MMOCORE_CLASS_HELP = "  <state> &5&oClass <classes>";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_MMOCORE_LEVEL_ERROR = "&cYour level is too low.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_MMOCORE_LEVEL_HELP = "  <state> &5&oLevel >= <level>";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_MMOCORE_PROFESSION_ERROR = "&cYour <profession> profession level is too low.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_MMOCORE_PROFESSION_HELP = "  <state> &5&o<profession> >= <level>";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_QUANTUMRPG_CLASS_ERROR = "&cWrong class.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_QUANTUMRPG_CLASS_HELP = "  <state> &5&oClass <classes>";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_QUANTUMRPG_LEVEL_ERROR = "&cYour level is too low.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_QUANTUMRPG_LEVEL_HELP = "  <state> &5&oLevel >= <level>";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_SKILLAPI_CLASS_ERROR = "&cWrong class.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_SKILLAPI_CLASS_HELP = "  <state> &5&oClass <classes>";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_SKILLAPI_LEVEL_ERROR = "&cYour level is too low.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_SKILLAPI_LEVEL_HELP = "  <state> &5&oLevel >= <level>";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_SKILLAPI_SKILLLEVEL_ERROR = "&cYour <skill> level is too low.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_SKILLAPI_SKILLLEVEL_HELP = "  <state> &5&o<skill> >= <level>";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_ADVANCEMENT_ERROR = "&cAdvancement not completed.";

    public static final String DEFAULT_MESSAGES_REQUIREMENTS_ADVANCEMENT_HELP = "  <state> &5&oAdvancement '<advancement>'";

    private static final String DEFAULT_MESSAGES_PRICE_BUY_FREE = "Free";

    private static final String DEFAULT_MESSAGES_PRICE_SELL_NOTHING = "Nothing";

    private static final String DEFAULT_MESSAGES_PURCHASE_LORE = "&5&oClick to purchase";

    private static final String DEFAULT_MESSAGES_SELL_LORE = "&5&oClick to sell";

    private static final String DEFAULT_MESSAGES_RIDE_LORE = "&5&oClick to ride";

    private static final String DEFAULT_MESSAGES_REQUIREMENTS_SATISFIED = "&e&l";

    private static final String DEFAULT_MESSAGES_REQUIREMENTS_NOT_SATISFIED = "&4&l";

    private static final Sound DEFAULT_RIDE_MOUNT_SOUND = Sound.ENTITY_HORSE_LAND;

    private static final Sound DEFAULT_STORE_MOUNT_SOUND = Sound.ENTITY_HORSE_SADDLE;

    private static final Sound DEFAULT_BUY_MOUNT_SOUND = Sound.BLOCK_NOTE_BLOCK_CHIME;

    private static final Sound DEFAULT_SELL_MOUNT_SOUND = Sound.BLOCK_NOTE_BLOCK_CHIME;

    private static final long DEFAULT_MOUNT_COOLDOWN_SECONDS = 1L;

    private static final boolean DEFAULT_UNMOUNT_ON_DAMAGE = true;

    private static final boolean DEFAULT_SILENCE_MOUNT = false;

    public static void storeDefaults() {
        MountsPlugin.getInstance().getConfig().addDefault("debug", Boolean.valueOf(false));
        MountsPlugin.getInstance().getConfig().addDefault("dataStore.type", "config");
        MountsPlugin.getInstance().getConfig().addDefault("dataStore.mysql.host", "localhost");
        MountsPlugin.getInstance().getConfig().addDefault("dataStore.mysql.port", Integer.valueOf(3306));
        MountsPlugin.getInstance().getConfig().addDefault("dataStore.mysql.database", "minecraft");
        MountsPlugin.getInstance().getConfig().addDefault("dataStore.mysql.user", "root");
        MountsPlugin.getInstance().getConfig().addDefault("dataStore.mysql.pass", "");
        MountsPlugin.getInstance().getConfig().addDefault("messages.configReload", "&e[Mounts] Configuration reloaded!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.configDemo", "&e[Mounts] Demo configuration loaded!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.give", "&eGave <player> a mount: <mount>!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.permissionDenied", "&4Error: &cPermission denied!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.playerNotFound", "&4Error: &cPlayer not found!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.mountNotFound", "&4Error: &cMount not found!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.mountCooldown", "&cYou must wait before mounting again!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.mountRequirement", "&cCannot use mount. ");
        MountsPlugin.getInstance().getConfig().addDefault("messages.mountPresetNotFound", "&cMount preset not found. Use /mset.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.mountPresetInvalid", "&cYou must be riding a mount.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.mountPresetSave", "&eMount preset saved!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.mountPresetRemove", "&eMount preset removed!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.mountCannotRide", "&cYou cannot do that right now!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.paymentNotSupported", "&cError: Payment not supported!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.mountBuy", "&aYou purchased a mount: <mount>!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.mountSell", "&aYou sold a mount: <mount>!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.insufficientFunds", "&cYou cannot afford this mount: <mount>!");
        MountsPlugin.getInstance().getConfig().addDefault("messages.sellShopTitle", "Sell Mount");
        MountsPlugin.getInstance().getConfig().addDefault("messages.mountsTitle", "Mounts");
        MountsPlugin.getInstance().getConfig().addDefault("messages.buyShopTitle", "Purchase Mount");
        MountsPlugin.getInstance().getConfig().addDefault("sounds.rideMount", DEFAULT_RIDE_MOUNT_SOUND.toString());
        MountsPlugin.getInstance().getConfig().addDefault("sounds.storeMount", DEFAULT_STORE_MOUNT_SOUND.toString());
        MountsPlugin.getInstance().getConfig().addDefault("sounds.buyMount", DEFAULT_BUY_MOUNT_SOUND.toString());
        MountsPlugin.getInstance().getConfig().addDefault("sounds.sellMount", DEFAULT_SELL_MOUNT_SOUND.toString());
        MountsPlugin.getInstance().getConfig().addDefault("mountCooldownSeconds", Long.valueOf(1L));
        MountsPlugin.getInstance().getConfig().addDefault("unmountOnDamage", Boolean.valueOf(true));
        MountsPlugin.getInstance().getConfig().addDefault("lore", DEFAULT_LORE);
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.none", "  &5&oNone");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.level.error", "&cYour level is too low.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.level.help", "  <state> &5&oLevel >= <level>");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.mcmmo.powerLevel.error", "&cYour power level is too low.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.mcmmo.powerLevel.help", "  <state> &5&oPower Level >= <powerLevel>");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.mcmmo.skillLevel.error", "&cYour <skill> skill level is too low.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.mcmmo.skillLevel.help", "  <state> &5&o<skill> >= <level>");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.mmocore.attribute.error", "&cYour <attribute> attribute is too low.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.mmocore.attribute.help", "  <state> &5&o<attribute> >= <level>");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.mmocore.class.error", "&cWrong class.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.mmocore.class.help", "  <state> &5&oClass <classes>");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.mmocore.level.error", "&cYour level is too low.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.mmocore.level.help", "  <state> &5&oLevel >= <level>");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.mmocore.profession.error", "&cYour <profession> profession level is too low.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.mmocore.profession.help", "  <state> &5&o<profession> >= <level>");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.quantumrpg.class.error", "&cWrong class.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.quantumrpg.class.help", "  <state> &5&oClass <classes>");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.quantumrpg.level.error", "&cYour level is too low.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.quantumrpg.level.help", "  <state> &5&oLevel >= <level>");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.skillapi.class.error", "&cWrong class.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.skillapi.class.help", "  <state> &5&oClass <classes>");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.skillapi.level.error", "&cYour level is too low.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.skillapi.level.help", "  <state> &5&oLevel >= <level>");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.skillapi.skillLevel.error", "&cYour <skill> level is too low.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.skillapi.skillLevel.help", "  <state> &5&o<skill> >= <level>");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.advancement.help", "  <state> &5&oAdvancement '<advancement>'");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirements.advancement.error", "&cAdvancement not completed.");
        MountsPlugin.getInstance().getConfig().addDefault("messages.priceBuyFree", "Free");
        MountsPlugin.getInstance().getConfig().addDefault("messages.priceSellNothing", "Nothing");
        MountsPlugin.getInstance().getConfig().addDefault("messages.purchaseLore", "&5&oClick to purchase");
        MountsPlugin.getInstance().getConfig().addDefault("messages.sellLore", "&5&oClick to sell");
        MountsPlugin.getInstance().getConfig().addDefault("messages.rideLore", "&5&oClick to ride");
        MountsPlugin.getInstance().getConfig().addDefault("messages.requirementSatisfied", "&e&l");
                MountsPlugin.getInstance().getConfig().addDefault("messages.requirementNotSatisfied", "&4&l");
                        MountsPlugin.getInstance().getConfig().addDefault("silenceMount", Boolean.valueOf(false));
        MountsPlugin.getInstance().getConfig().options().copyDefaults(true);
        MountsPlugin.getInstance().saveConfig();
    }

    public static boolean isDebug() {
        return MountsPlugin.getInstance().getConfig().getBoolean("debug", false);
    }

    public static List<String> getLore() {
        List<String> lore = new ArrayList<>();
        for (String line : MountsPlugin.getInstance().getConfig().getStringList("lore"))
            lore.add(MessageUtil.colorize(line));
        return lore;
    }

    public static String messageConfigReload() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.configReload"));
    }

    public static String buyShopTitle() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.buyShopTitle"));
    }

    public static String sellShopTitle() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.sellShopTitle"));
    }

    public static String mountsTitle() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.mountsTitle"));
    }

    public static String messageConfigDemo() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.configDemo"));
    }

    public static String messageGive(Player player, Mount mount) {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.give")
                .replaceAll("\\<player\\>", player.getName()).replaceAll("\\<mount\\>", mount.getType().getName()));
    }

    public static String messagePermissionDenied() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.permissionDenied"));
    }

    public static String messagePlayerNotFound() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.playerNotFound"));
    }

    public static String messageMountNotFound() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.mountNotFound"));
    }

    public static String messageMountCooldown() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.mountCooldown"));
    }

    public static String messageMountRequirement() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.mountRequirement"));
    }

    public static String messageMountPresetNotFound() {
        return
                MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.mountPresetNotFound"));
    }

    public static String messageMountPresetInvalid() {
        return
                MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.mountPresetInvalid"));
    }

    public static String messageMountPresetSave() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.mountPresetSave"));
    }

    public static String messageMountPresetRemove() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.mountPresetRemove"));
    }

    public static String messagePaymentNotSupported() {
        return
                MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.paymentNotSupported"));
    }

    public static String messageMountCannotRide() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.mountCannotRide"));
    }

    public static String messageBuyMount(MountType mountType) {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.mountBuy")
                .replaceAll("\\<mount\\>", mountType.getName()));
    }

    public static String messageSellMount(Mount mount) {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.mountSell")
                .replaceAll("\\<mount\\>", mount.getType().getName()));
    }

    public static String messageInsufficientFunds(MountType mountType) {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.insufficientFunds")
                .replaceAll("\\<mount\\>", mountType.getName()));
    }

    public static Sound soundRideMount() {
        return SoundUtil.fromString(MountsPlugin.getInstance().getConfig().getString("sounds.rideMount"), DEFAULT_RIDE_MOUNT_SOUND);
    }

    public static Sound soundStoreMount() {
        return SoundUtil.fromString(MountsPlugin.getInstance().getConfig().getString("sounds.storeMount"), DEFAULT_STORE_MOUNT_SOUND);
    }

    public static Sound soundBuyMount() {
        return SoundUtil.fromString(MountsPlugin.getInstance().getConfig().getString("sounds.buyMount"), DEFAULT_BUY_MOUNT_SOUND);
    }

    public static Sound soundSellMount() {
        return SoundUtil.fromString(MountsPlugin.getInstance().getConfig().getString("sounds.sellMount"), DEFAULT_SELL_MOUNT_SOUND);
    }

    public static long mountCooldownSeconds() {
        return MountsPlugin.getInstance().getConfig().getLong("mountCooldownSeconds");
    }

    public static boolean silenceMount() {
        return MountsPlugin.getInstance().getConfig().getBoolean("silenceMount", false);
    }

    public static boolean unmountOnDamage() {
        return MountsPlugin.getInstance().getConfig().getBoolean("unmountOnDamage");
    }

    public static String messageRequirementsNone() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig().getString("messages.requirements.none", "  &5&oNone"));
    }

    public static String messageRequirementSatisfied() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig()
                .getString("messages.requirementSatisfied", "&e&l"));
    }

    public static String messageRequirementNotSatisfied() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig()
                .getString("messages.requirementNotSatisfied", "&4&l"));
    }

    public static String requirementHelpMessage(Requirement requirement, Player player, String key, String def, String... attributes) {
        MessageUtil message = MessageUtil.create(MountsPlugin.getInstance().getConfig().getString(key, def));
        for (int i = 0; i + 1 < attributes.length; i += 2)
            message.replace(attributes[i], attributes[i + 1]);
        if (player == null) {
            message.replace("state", "");
        } else if (requirement.isSatisfied(player)) {
            message.replace("state", messageRequirementSatisfied());
        } else {
            message.replace("state", messageRequirementNotSatisfied());
        }
        return message.colorize();
    }

    public static String levelErrorMessage() {
        return MessageUtil.colorize(MountsPlugin.getInstance().getConfig()
                .getString("messages.requirements.level.error", "&cYour level is too low."));
    }

    public static String mcmmoPowerLevelErrorMessage() {
        return MessageUtil.colorize(
                MountsPlugin.getInstance().getConfig().getString("messages.requirements.mcmmo.powerLevel.error", "&cYour power level is too low."));
    }

    public static String mcmmoSkillLevelErrorMessage(String name) {
        return MessageUtil.create(MountsPlugin.getInstance().getConfig().getString("messages.requirements.mcmmo.skillLevel.error", "&cYour <skill> skill level is too low."))

                .replace("skill", name).colorize();
    }

    public static String mmocoreAttributeErrorMessage(String name) {
        return MessageUtil.create(
                MountsPlugin.getInstance().getConfig().getString("messages.requirements.mmocore.attribute.error", "&cYour <attribute> attribute is too low."))

                .replace("attribute", name).colorize();
    }

    public static String mmocoreClassErrorMessage() {
        return
                MessageUtil.create(MountsPlugin.getInstance().getConfig().getString("messages.requirements.mmocore.class.error", "&cWrong class."))

                        .colorize();
    }

    public static String mmocoreLevelErrorMessage() {
        return
                MessageUtil.create(MountsPlugin.getInstance().getConfig().getString("messages.requirements.mmocore.level.error", "&cYour level is too low."))

                        .colorize();
    }

    public static String mmocoreProfessionErrorMessage(String profession) {
        return
                MessageUtil.create(MountsPlugin.getInstance().getConfig().getString("messages.requirements.mmocore.profession.error", "&cYour <profession> profession level is too low."))

                        .replace("profession", profession).colorize();
    }

    public static String quantumrpgClassErrorMessage() {
        return MessageUtil.create(MountsPlugin.getInstance().getConfig().getString("messages.requirements.quantumrpg.class.error", "&cWrong class."))

                .colorize();
    }

    public static String quantumrpgLevelErrorMessage() {
        return MessageUtil.create(MountsPlugin.getInstance().getConfig().getString("messages.requirements.quantumrpg.level.error", "&cYour level is too low."))

                .colorize();
    }

    public static String skillapiClassErrorMessage() {
        return
                MessageUtil.create(MountsPlugin.getInstance().getConfig().getString("messages.requirements.skillapi.class.error", "&cWrong class."))

                        .colorize();
    }

    public static String skillapiLevelErrorMessage() {
        return
                MessageUtil.create(MountsPlugin.getInstance().getConfig().getString("messages.requirements.skillapi.level.error", "&cYour level is too low."))

                        .colorize();
    }

    public static String skillapiSkillLevelErrorMessage(String name) {
        return MessageUtil.create(
                MountsPlugin.getInstance().getConfig().getString("messages.requirements.skillapi.skillLevel.error", "&cYour <skill> level is too low."))

                .replace("skill", name).colorize();
    }

    public static String advancementErrorMessage() {
        return
                MessageUtil.create(MountsPlugin.getInstance().getConfig().getString("messages.requirements.advancement.error", "&cAdvancement not completed."))

                        .colorize();
    }

    public static String messagePriceBuyFree() {
        return MessageUtil.create(MountsPlugin.getInstance().getConfig().getString("messages.priceBuyFree", "Free"))
                .colorize();
    }

    public static String messagePriceSellNothing() {
        return MessageUtil.create(MountsPlugin.getInstance().getConfig().getString("messages.priceSellNothing", "Nothing"))
                .colorize();
    }

    public static String messagePurchaseLore() {
        return MessageUtil.create(MountsPlugin.getInstance().getConfig().getString("messages.purchaseLore", "&5&oClick to purchase"))
                .colorize();
    }

    public static String messageSellLore() {
        return MessageUtil.create(
                MountsPlugin.getInstance().getConfig().getString("messages.sellLore", "&5&oClick to sell"))
                .colorize();
    }

    public static String messageRideLore() {
        return MessageUtil.create(
                MountsPlugin.getInstance().getConfig().getString("messages.rideLore", "&5&oClick to ride"))
                .colorize();
    }

    public static String dataStoreType() {
        return MountsPlugin.getInstance().getConfig().getString("dataStore.type", "config");
    }

    public static String dataStoreMysqlHost() {
        return MountsPlugin.getInstance().getConfig().getString("dataStore.mysql.host", "localhost");
    }

    public static int dataStoreMysqlPort() {
        return MountsPlugin.getInstance().getConfig().getInt("dataStore.mysql.port", 3306);
    }

    public static String dataStoreMysqlDatabase() {
        return MountsPlugin.getInstance().getConfig().getString("dataStore.mysql.database", "minecraft");
    }

    public static String dataStoreMysqlUser() {
        return MountsPlugin.getInstance().getConfig().getString("dataStore.mysql.user", "root");
    }

    public static String dataStoreMysqlPass() {
        return MountsPlugin.getInstance().getConfig().getString("dataStore.mysql.pass", "");
    }
}