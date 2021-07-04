package me.critikull.mounts.mount.types;

import me.critikull.mounts.config.Config;
import me.critikull.mounts.mount.price.Price;
import me.critikull.mounts.mount.requirement.Requirement;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MountTypeAbstractHorse extends MountType {
    public MountTypeAbstractHorse(String id, EntityType type, Material icon, String name, Price priceBuy, Price priceSell, double speed, double jump, List<Requirement> requirements, List<String> categories, List<String> lore, ItemStack tokenItem) {
        super(id, type, icon, name, priceBuy, priceSell, speed, jump, requirements, categories, lore, tokenItem);
    }

    public MountTypeAbstractHorse(String id, EntityType type, Material icon, String name, Price priceBuy, Price priceSell, double speed, double jump, List<Requirement> requirements, List<String> categories, List<String> lore) {
        super(id, type, icon, name, priceBuy, priceSell, speed, jump, requirements, categories, lore, null);
    }

    public Vehicle spawn(Player player) {
        AbstractHorse horse = (AbstractHorse)player.getWorld().spawnEntity(player.getLocation(), getType());
        horse.setTamed(true);
        horse.setOwner((AnimalTamer)player);
        horse.setJumpStrength(getJump());
        horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(getSpeed());
        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        horse.setCanPickupItems(false);
        horse.setAdult();
        horse.setBreed(false);
        horse.setSilent(Config.silenceMount());
        return (Vehicle)horse;
    }

    public static boolean isValidEntityType(EntityType type) {
        return Arrays.<EntityType>asList(new EntityType[] { EntityType.SKELETON_HORSE, EntityType.ZOMBIE_HORSE, EntityType.MULE, EntityType.DONKEY }).contains(type);
    }
}
