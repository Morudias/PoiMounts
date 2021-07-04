package me.critikull.mounts.mount.types;

import me.critikull.mounts.config.Config;
import me.critikull.mounts.mount.price.Price;
import me.critikull.mounts.mount.requirement.Requirement;
import me.critikull.mounts.utils.ItemStackUtil;
import me.critikull.mounts.utils.MessageUtil;
import me.critikull.mounts.utils.StringUtil;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MountType {
    private final String id;

    private final EntityType type;

    private final String breedName;

    private final Material icon;

    private final String name;

    private final double speed;

    private final double jump;

    private final List<Requirement> requirements;

    private final Price priceBuy;

    private final Price priceSell;

    private final List<String> categories;

    private final List<String> lore;

    private final ItemStack tokenItem;

    public MountType(String id, EntityType type, Material icon, String name, Price priceBuy, Price priceSell, double speed, double jump, List<Requirement> requirements, List<String> categories, List<String> lore, ItemStack tokenItem) {
        this.id = id;
        this.type = type;
        this.breedName = StringUtil.capitalize(type.toString());
        this.icon = icon;
        this.name = name;
        this.speed = speed;
        this.jump = jump;
        this.requirements = requirements;
        this.priceBuy = priceBuy;
        this.priceSell = priceSell;
        this.categories = categories;
        this.lore = lore;
        this.tokenItem = tokenItem;
    }

    public ItemStack getTokenItem() {
        return this.tokenItem;
    }

    public String getId() {
        return this.id;
    }

    public EntityType getType() {
        return this.type;
    }

    public String getBreedName() {
        return this.breedName;
    }

    public Material getIcon() {
        return this.icon;
    }

    public String getName() {
        return this.name;
    }

    public Price getPriceBuy() {
        return this.priceBuy;
    }

    public boolean hasPriceBuy() {
        return (getPriceBuy() != null);
    }

    public Price getPriceSell() {
        return this.priceSell;
    }

    public boolean hasPriceSell() {
        return (getPriceSell() != null);
    }

    public double getSpeed() {
        return this.speed;
    }

    public double getJump() {
        return this.jump;
    }

    public boolean hasLore() {
        return !this.lore.isEmpty();
    }

    public List<String> getLore() {
        return this.lore;
    }

    public List<Requirement> getRequirements() {
        return this.requirements;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public abstract Vehicle spawn(Player paramPlayer);

    public boolean hasRequirements() {
        return !this.requirements.isEmpty();
    }

    private static String getSpeedMeter(double speed) {
        if (speed < 0.1D)
            return "Very Slow";
        if (speed < 0.3D)
            return "Slow";
        if (speed < 0.6D)
            return "Normal";
        if (speed < 0.7D)
            return "Fast";
        return "Very Fast";
    }

    private static String getJumpMeter(double jump) {
        if (jump < 0.1D)
            return "Very Low";
        if (jump < 0.3D)
            return "Low";
        if (jump < 0.5D)
            return "Normal";
        if (jump < 0.8D)
            return "High";
        return "Very High";
    }

    public ItemStack getItem() {
        return getItem(null);
    }

    public ItemStack getItem(Player player) {
        ItemStack item = new ItemStack(getIcon());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtil.colorize(String.format("&e%s", new Object[] { getName() })));
        List<String> lore = new ArrayList<>();
        for (String line : hasLore() ? getLore() : Config.getLore()) {
            if (line.equals("<requirements>")) {
                if (hasRequirements()) {
                    for (Requirement requirement : getRequirements())
                        lore.add(MessageUtil.colorize(requirement.getHelpMessage(player)));
                    continue;
                }
                lore.add(Config.messageRequirementsNone());
                continue;
            }
            lore.add(MessageUtil.create(line).replace("id", getId()).replace("name", getName())
                    .replace("breed", getBreedName()).replace("speed", String.format("%s", new Object[] { Double.valueOf(getSpeed()) })).replace("jump", String.format("%s", new Object[] { Double.valueOf(getJump()) })).replace("speedBoost", String.format("%d", new Object[] { Integer.valueOf((int)(getSpeed() / 0.1D)) })).replace("jumpBoost", String.format("%d", new Object[] { Integer.valueOf((int)(getJump() / 0.432084373616155D)) })).replace("speedMeter", getSpeedMeter(getSpeed())).replace("jumpMeter", getJumpMeter(getJump()))
                    .replace("priceBuy", hasPriceBuy() ? getPriceBuy().toString() : Config.messagePriceBuyFree())
                    .replace("priceSell", hasPriceSell() ? getPriceSell().toString() : Config.messagePriceSellNothing())
                    .replace("categories", String.join(", ", (Iterable)getCategories())).replace("requirements", "").colorize());
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getPurchaseItem() {
        ItemStack item = getItem();
        ItemStackUtil.addLore(item, Arrays.asList(new String[] { "", Config.messagePurchaseLore() }));
        return item;
    }

    public ItemStack getSellItem() {
        ItemStack item = getItem();
        ItemStackUtil.addLore(item, Arrays.asList(new String[] { "", Config.messageSellLore() }));
        return item;
    }

    public boolean hasPermission(Player player) {
        return player.hasPermission(String.format("mounts.mount.%s", new Object[] { this.id }));
    }

    public boolean hasCategory(String mountCategory) {
        return this.categories.contains(mountCategory);
    }
}
