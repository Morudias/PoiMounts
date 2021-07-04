package me.critikull.mounts.config;

import java.util.Arrays;

import com.gmail.nossr50.datatypes.skills.SkillType;
import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.mount.price.Price;
import me.critikull.mounts.mount.price.PriceEconomy;
import me.critikull.mounts.mount.price.PriceItems;
import me.critikull.mounts.mount.requirement.Requirement;
import me.critikull.mounts.mount.requirement.RequirementAdvancement;
import me.critikull.mounts.mount.requirement.RequirementLevel;
import me.critikull.mounts.mount.requirement.mcmmo.RequirementPowerLevel;
import me.critikull.mounts.mount.requirement.mcmmo.RequirementSkillLevel;
import me.critikull.mounts.mount.types.MountType;
import me.critikull.mounts.mount.types.MountTypeAbstractHorse;
import me.critikull.mounts.mount.types.MountTypeHorse;
import me.critikull.mounts.utils.MapUtil;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.ItemStack;

public final class ConfigDemo {
    public static void load() {
        MountsPlugin.getInstance().getDataStore()
                .save((MountType)new MountTypeHorse("forest_steed", Material.IRON_HORSE_ARMOR, "Forest Steed", (Price)new PriceItems(
                        MapUtil.mapOf(Material.GOLD_INGOT, Integer.valueOf(5), new Object[0])), (Price)new PriceItems(
                        MapUtil.mapOf(Material.GOLD_INGOT, Integer.valueOf(2), new Object[0])), 0.15D, 0.3D, Horse.Color.BROWN, Horse.Style.NONE, null,
                        Arrays.asList(new Requirement[0]), Arrays.asList(new String[0]), Arrays.asList(new String[0]),
                        new ItemStack(Material.STONE)));
        MountsPlugin.getInstance().getDataStore().save((MountType)new MountTypeHorse("nightmare", Material.IRON_HORSE_ARMOR, "Nightmare", (Price)new PriceItems(
                MapUtil.mapOf(Material.GOLD_INGOT, Integer.valueOf(50), new Object[0])), (Price)new PriceItems(
                MapUtil.mapOf(Material.GOLD_INGOT, Integer.valueOf(25), new Object[0])), 0.18D, 0.35D, Horse.Color.BLACK, Horse.Style.NONE, null,

                Arrays.asList(new Requirement[] { (Requirement)new RequirementLevel(10), (Requirement)new RequirementAdvancement("recipes/transportation/carrot_on_a_stick") }), Arrays.asList(new String[] { "VIP", "Rare" }), Arrays.asList(new String[] {
                "&9<breed> - <categories>", "", "&7&oLives in the shadows..", "", "&5&oAttributes:", "  &5&oSpeed <speedMeter>", "  &5&oJump <jumpMeter>", "", "&5&oRequirements:", "<requirements>",
                "", "&9Price: &a<priceBuy>", "&9Sells for: &a<priceSell>" }),
                new ItemStack(Material.STONE)));
        if (MountsPlugin.hasEconomyManager())
            MountsPlugin.getInstance().getDataStore()
                    .save((MountType)new MountTypeHorse("armored_nightmare", Material.IRON_HORSE_ARMOR, "Armored Nightmare", (Price)new PriceEconomy(200.0D), (Price)new PriceEconomy(100.0D), 0.19D, 0.44D, Horse.Color.BLACK, Horse.Style.NONE, Material.IRON_HORSE_ARMOR,

                            Arrays.asList(new Requirement[0]), Arrays.asList(new String[0]),
                            Arrays.asList(new String[0]),
                            new ItemStack(Material.STONE)));
        if (MountsPlugin.hasMcMMO()) {
            MountsPlugin.getInstance().getDataStore()
                    .save((MountType)new MountTypeAbstractHorse("deathcharger", EntityType.SKELETON_HORSE, Material.GOLDEN_HORSE_ARMOR, "Deathcharger", (Price)new PriceItems(

                            MapUtil.mapOf(Material.DIAMOND, Integer.valueOf(40), new Object[0])), (Price)new PriceItems(
                            MapUtil.mapOf(Material.DIAMOND, Integer.valueOf(10), new Object[0])), 0.2D, 0.45D,
                            Arrays.asList(new Requirement[] { (Requirement)new RequirementSkillLevel(10, SkillType.TAMING) }), Arrays.asList(new String[0]),
                            Arrays.asList(new String[0]),
                            new ItemStack(Material.STONE)));
            MountsPlugin.getInstance().getDataStore()
                    .save((MountType)new MountTypeHorse("warhorse", Material.GOLDEN_HORSE_ARMOR, "Warhorse", (Price)new PriceItems(
                            MapUtil.mapOf(Material.DIAMOND, Integer.valueOf(45), new Object[0])), (Price)new PriceItems(
                            MapUtil.mapOf(Material.DIAMOND, Integer.valueOf(25), new Object[0])), 0.21D, 0.46D, Horse.Color.CHESTNUT, Horse.Style.BLACK_DOTS, Material.GOLDEN_HORSE_ARMOR,

                            Arrays.asList(new Requirement[] { (Requirement)new RequirementPowerLevel(15) }), Arrays.asList(new String[0]), Arrays.asList(new String[0]),
                            new ItemStack(Material.STONE)));
        }
    }
}
