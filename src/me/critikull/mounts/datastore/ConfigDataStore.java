package me.critikull.mounts.datastore;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import com.gmail.nossr50.datatypes.skills.SkillType;
import me.critikull.mounts.Log;
import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.mount.Mount;
import me.critikull.mounts.mount.Mounts;
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
import me.critikull.mounts.mount.types.MountTypes;
import me.critikull.mounts.utils.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.persistence.PersistentDataType;


public class ConfigDataStore implements IDataStore {
    private static Path getPlayersPath() {
        return Paths.get(MountsPlugin.getInstance().getDataFolder().getPath(), new String[] { "players" });
    }

    private static Path getPlayerMountsPath(UUID ownerId) {
        return Paths.get(getPlayersPath().toString(), new String[] { ownerId.toString() });
    }

    private static File getPlayerMountFile(Mount mount) {
        return Paths.get(getPlayerMountsPath(mount.getOwnerId()).toString(), new String[] { mount.getId().toString() }).toFile();
    }

    private static Path getMountTypesPath() {
        return Paths.get(MountsPlugin.getInstance().getDataFolder().getPath(), new String[] { "mounts" });
    }

    private static File getMountTypeFile(MountType type) {
        return Paths.get(getMountTypesPath().toString(), new String[] { String.format("%s.yml", new Object[] { type.getId() }) }).toFile();
    }

    public void save(Mount mount) {
        Path path = getPlayerMountsPath(mount.getOwnerId());
        path.toFile().mkdirs();
        File file = Paths.get(path.toString(), new String[] { String.format("%s.%s", new Object[] { mount.getId().toString(), "yml" }) }).toFile();
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.set("mount", mount.getType().getId());
        yamlConfiguration.set("distance", Double.valueOf(mount.getDistance()));
        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(Mount mount) {
        getPlayerMountFile(mount).delete();
    }

    private static Mount loadMount(String ownerId, File file) {
        String id = FileUtil.getFileName(file);
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        String name = yamlConfiguration.getString("mount", null);
        if (name == null) {
            Log.info(String.format("player mount '%s' missing mount", new Object[] { file.toString() }), new Object[0]);
            return null;
        }
        MountType type = MountsPlugin.getInstance().getMountManager().getMountType(name);
        if (type == null) {
            Log.info(String.format("player mount '%s' mount not found", new Object[] { file.toString() }), new Object[0]);
            return null;
        }
        double distance = yamlConfiguration.getDouble("distance", 0.0D);
        return new Mount(UUID.fromString(id), UUID.fromString(ownerId), type, distance);
    }

    private static Mounts loadMounts(Path path) {
        Mounts mounts = new Mounts();
        File[] files = path.toFile().listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".yml");
            }
        });
        if (files != null)
            for (File file : files) {
                Mount mount = loadMount(path.toFile().getName(), file);
                if (mount != null) {
                    Log.info(String.format("Loaded player mount %s", new Object[] { mount.getId() }), new Object[0]);
                    mounts.add(mount);
                }
            }
        return mounts;
    }

    public Mounts loadMounts() {
        Mounts mounts = new Mounts();
        File[] files = getPlayersPath().toFile().listFiles();
        if (files != null)
            for (File file : files)
                mounts.addAll(loadMounts(file.toPath()));
        return mounts;
    }

    private static void savePrice(FileConfiguration config, String key, Price price) {
        if (price instanceof PriceEconomy) {
            PriceEconomy priceEconomy = (PriceEconomy)price;
            config.set(key, Double.valueOf(priceEconomy.getPrice()));
        } else if (price instanceof PriceItems) {
            PriceItems priceItems = (PriceItems)price;
            for (Material type : priceItems.getItems().keySet()) {
                config.set(String.format("%s.%s", new Object[] { key, type.toString() }), priceItems.getItems().get(type));
            }
        }
    }

    private static void saveRequirement(FileConfiguration config, Requirement requirement) {
        if (requirement instanceof RequirementLevel) {
            RequirementLevel level = (RequirementLevel)requirement;
            config.set("requirements.level", Integer.valueOf(level.getLevel()));
        } else if (requirement instanceof RequirementAdvancement) {
            RequirementAdvancement advancement = (RequirementAdvancement)requirement;
            List<String> advancements = config.getStringList("requirements.advancements");
            advancements.add(advancement.getKey());
            config.set("requirements.advancements", advancements);
        } else if (requirement instanceof RequirementSkillLevel) {
            RequirementSkillLevel skill = (RequirementSkillLevel)requirement;
            config.set(String.format("requirements.mcmmo.skills.%s", new Object[] { skill.getSkillType().toString() }), Integer.valueOf(skill.getLevel()));
        } else if (requirement instanceof RequirementPowerLevel) {
            RequirementPowerLevel powerLevel = (RequirementPowerLevel)requirement;
            config.set("requirements.mcmmo.powerLevel", Integer.valueOf(powerLevel.getPowerLevel()));
        }
    }

    public void save(MountType type) {
        File file = getMountTypeFile(type);
        file.delete();
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.set("name", type.getName());
        yamlConfiguration.set("type", type.getType().toString());
        yamlConfiguration.set("icon", type.getIcon().toString());
        yamlConfiguration.set("speed", Double.valueOf(type.getSpeed()));
        yamlConfiguration.set("jump", Double.valueOf(type.getJump()));
        yamlConfiguration.set("categories", type.getCategories());
        yamlConfiguration.set("lore", type.getLore());
        if (type instanceof MountTypeHorse) {
            MountTypeHorse typeHorse = (MountTypeHorse)type;
            yamlConfiguration.set("color", typeHorse.getColor().toString());
            yamlConfiguration.set("style", typeHorse.getStyle().toString());
            if (typeHorse.hasArmor())
                yamlConfiguration.set("armor", typeHorse.getArmor().toString());
        }
        savePrice((FileConfiguration)yamlConfiguration, "priceBuy", type.getPriceBuy());
        savePrice((FileConfiguration)yamlConfiguration, "priceSell", type.getPriceSell());
        for (Requirement requirement : type.getRequirements())
            saveRequirement((FileConfiguration)yamlConfiguration, requirement);
        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Price loadPrice(String mountId, FileConfiguration config, String key) {
        if (config.isDouble(key))
            return (Price)new PriceEconomy(config.getDouble(key));
        ConfigurationSection priceBuySection = config.getConfigurationSection(key);
        if (priceBuySection != null) {
            HashMap<Material, Integer> items = new HashMap<>();
            for (String materialName : priceBuySection.getKeys(false)) {
                Material material = Material.getMaterial(materialName);
                if (material == null) {
                    Log.info(String.format("mount '%s' invalid %s item: %s", new Object[] { mountId, key, materialName }), new Object[0]);
                    return null;
                }
                int amount = priceBuySection.getInt(materialName);
                items.put(material, Integer.valueOf(amount));
            }
            return (Price)new PriceItems(items);
        }
        return null;
    }

    private static ItemStack loadTokenItem(String mountID, FileConfiguration config) { //TODO: Try/catches around these returning null incase error reading config
        if(!config.contains("tokenItem.material")) {
            Bukkit.getLogger().log(Level.WARNING, "Invalid material for %s", mountID);
            return null;
        }
        ItemStack ret = new ItemStack(Material.valueOf(config.getString("tokenItem.material")));
        ItemMeta retMeta = ret.getItemMeta();
        if(!config.contains("tokenItem.name")) {
            Bukkit.getLogger().log(Level.WARNING, "Invalid display name for %s", mountID);
            return null;
        } else {
            retMeta.setDisplayName(config.getString("tokenItem.name"));
        }
        if(!config.contains("tokenItem.lore")) {
            Bukkit.getLogger().log(Level.WARNING, "Invalid lore for %s", mountID);
            return null;
        } else {
            retMeta.setLore(config.getStringList("tokenItem.lore"));
        }
        retMeta.getPersistentDataContainer().set(MountsPlugin.TOKENIDKEY, PersistentDataType.STRING, mountID);
        ret.setItemMeta(retMeta);
        return ret;
    }

    private static MountType loadMountType(File file) {
        String id = FileUtil.getFileName(file);
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        if (MountsPlugin.getInstance().getServer().getPluginManager()
                .getPermission(String.format("mounts.mount.%s", new Object[] { id })) == null)
            MountsPlugin.getInstance().getServer().getPluginManager()
                    .addPermission(new Permission(String.format("mounts.mount.%s", new Object[] { id }), PermissionDefault.TRUE));
        Material icon = Material.getMaterial(yamlConfiguration.getString("icon"));
        if (icon == null) {
            Log.info(String.format("mount '%s' missing icon", new Object[] { id }), new Object[0]);
            return null;
        }
        String name = yamlConfiguration.getString("name", null);
        if (name == null) {
            Log.info(String.format("mount '%s' missing name", new Object[] { id }), new Object[0]);
            return null;
        }
        if (!yamlConfiguration.contains("speed")) {
            Log.info(String.format("mount '%s' missing speed", new Object[] { id }), new Object[0]);
            return null;
        }
        if (!yamlConfiguration.isDouble("speed")) {
            Log.info(String.format("mount '%s' invalid speed", new Object[] { id }), new Object[0]);
            return null;
        }
        double speed = yamlConfiguration.getDouble("speed");
        if (!yamlConfiguration.contains("jump")) {
            Log.info(String.format("mount '%s' missing jump", new Object[] { id }), new Object[0]);
            return null;
        }
        if (!yamlConfiguration.contains("jump")) {
            Log.info(String.format("mount '%s' missing jump", new Object[] { id }), new Object[0]);
            return null;
        }
        if (!yamlConfiguration.isDouble("jump")) {
            Log.info(String.format("mount '%s' invalid jump", new Object[] { id }), new Object[0]);
            return null;
        }
        double jump = yamlConfiguration.getDouble("jump");
        EntityType type = EntityType.valueOf(yamlConfiguration.getString("type"));
        if (type == null) {
            Log.info(String.format("mount '%s' missing type", new Object[] { id }), new Object[0]);
            return null;
        }
        List<Requirement> requirements = new ArrayList<>();
        if (MountsPlugin.hasMcMMO()) {
            String skillsKey = "requirements.mcmmo.skills";
            ConfigurationSection skillSection = yamlConfiguration.getConfigurationSection(skillsKey);
            if (skillSection != null)
                for (String skillName : skillSection.getKeys(false)) {
                    SkillType skill = null;
                    try {
                        skill = SkillType.valueOf(skillName.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        Log.info(String.format("mount '%s' invalid mcMMO skill '%s'", new Object[] { id, skillName }), new Object[0]);
                        return null;
                    }
                    String skillLevelKey = String.format("%s.%s", new Object[] { skillsKey, skillName });
                    if (!yamlConfiguration.isInt(skillLevelKey)) {
                        Log.info(String.format("mount '%s' invalid mcMMO skill level '%s'", new Object[] { id, skillName }), new Object[0]);
                        return null;
                    }
                    int level = yamlConfiguration.getInt(skillLevelKey);
                    requirements.add(new RequirementSkillLevel(level, skill));
                }
            String powerLevelKey = "requirements.mcmmo.powerLevel";
            if (yamlConfiguration.contains(powerLevelKey)) {
                int powerLevel = yamlConfiguration.getInt(powerLevelKey);
                requirements.add(new RequirementPowerLevel(powerLevel));
            }
        }
        if (yamlConfiguration.isInt("requirements.level")) {
            int minLevel = yamlConfiguration.getInt("requirements.level");
            requirements.add(new RequirementLevel(minLevel));
        }
        ItemStack tokenItem = null;
        tokenItem = loadTokenItem(id, (FileConfiguration) yamlConfiguration);
        for (String advancementKey : yamlConfiguration.getStringList("requirements.advancements"))
            requirements.add(new RequirementAdvancement(advancementKey));
        Price priceBuy = loadPrice(id, (FileConfiguration)yamlConfiguration, "priceBuy");
        Price priceSell = loadPrice(id, (FileConfiguration)yamlConfiguration, "priceSell");
        List<String> categories = yamlConfiguration.getStringList("categories");
        List<String> lore = yamlConfiguration.getStringList("lore");
        if (type == EntityType.HORSE) {
            Horse.Color color;
            if (!yamlConfiguration.contains("color")) {
                Log.info(String.format("mount '%s' missing color", new Object[] { id }), new Object[0]);
                return null;
            }
            try {
                color = Horse.Color.valueOf(yamlConfiguration.getString("color"));
            } catch (IllegalArgumentException e) {
                Log.info(String.format("mount '%s' invalid color", new Object[] { id }), new Object[0]);
                return null;
            }
            Material armor = Material.getMaterial(yamlConfiguration.getString("armor"));
            Horse.Style style = Horse.Style.NONE;
            if (yamlConfiguration.contains("style"))
                try {
                    style = Horse.Style.valueOf(yamlConfiguration.getString("style"));
                } catch (IllegalArgumentException e) {
                    Log.info(String.format("mount '%s' invalid style", new Object[] { id }), new Object[0]);
                    return null;
                }
            return (MountType)new MountTypeHorse(id, icon, name, priceBuy, priceSell, speed, jump, color, style, armor, requirements, categories, lore, tokenItem);
        }
        if (MountTypeAbstractHorse.isValidEntityType(type))
            return (MountType)new MountTypeAbstractHorse(id, type, icon, name, priceBuy, priceSell, speed, jump, requirements, categories, lore, tokenItem);
        Log.info(String.format("mount '%s' invalid type", new Object[] { id }), new Object[0]);
        return null;
    }

    public MountTypes loadMountTypes() {
        MountTypes mountTypes = new MountTypes();
        File[] files = getMountTypesPath().toFile().listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".yml");
            }
        });
        if (files != null)
            for (File file : files) {
                MountType type = loadMountType(file);
                if (type != null) {
                    Log.info(String.format("Loaded mount %s", new Object[] { type.getId() }), new Object[0]);
                    mountTypes.add(type);
                }
            }
        return mountTypes;
    }
}