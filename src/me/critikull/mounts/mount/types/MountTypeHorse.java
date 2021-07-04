package me.critikull.mounts.mount.types;

import me.critikull.mounts.mount.price.Price;
import me.critikull.mounts.mount.requirement.Requirement;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MountTypeHorse extends MountTypeAbstractHorse {
    private final Material armor;

    private final Horse.Color color;

    private final Horse.Style style;

    public MountTypeHorse(String id, Material icon, String name, Price priceBuy, Price priceSell, double speed, double jump, Horse.Color color, Horse.Style style, Material armor, List<Requirement> requirements, List<String> categories, List<String> lore, ItemStack tokenItem) {
        super(id, EntityType.HORSE, icon, name, priceBuy, priceSell, speed, jump, requirements, categories, lore, tokenItem);
        this.armor = armor;
        this.color = color;
        this.style = style;
    }

    public MountTypeHorse(String id, Material icon, String name, Price priceBuy, Price priceSell, double speed, double jump, Horse.Color color, Horse.Style style, Material armor, List<Requirement> requirements, List<String> categories, List<String> lore) {
        super(id, EntityType.HORSE, icon, name, priceBuy, priceSell, speed, jump, requirements, categories, lore, null);
        this.armor = armor;
        this.color = color;
        this.style = style;
    }

    public Material getArmor() {
        return this.armor;
    }

    public boolean hasArmor() {
        return (getArmor() != null);
    }

    public Horse.Color getColor() {
        return this.color;
    }

    public Horse.Style getStyle() {
        return this.style;
    }

    public Vehicle spawn(Player player) {
        Horse horse = (Horse)super.spawn(player);
        horse.addPassenger((Entity)player);
        horse.setTamed(true);
        horse.setOwner((AnimalTamer)player);
        horse.setJumpStrength(getJump());
        horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(getSpeed());
        horse.setColor(this.color);
        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        if (hasArmor())
            horse.getInventory().setArmor(new ItemStack(getArmor()));
        horse.setStyle(this.style);
        horse.setCanPickupItems(false);
        horse.setInvulnerable(true);
        horse.setAdult();
        return (Vehicle)horse;
    }
}