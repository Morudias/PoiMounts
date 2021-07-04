package me.critikull.mounts.mount;

import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.config.Config;
import me.critikull.mounts.mount.requirement.Requirement;
import me.critikull.mounts.mount.types.MountType;
import me.critikull.mounts.utils.MessageUtil;
import me.critikull.mounts.utils.SoundUtil;
import me.critikull.mounts.utils.TimeUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Mount {
    private final UUID id;

    private final UUID ownerId;

    private final MountType type;

    private MountEntity entity;

    private double distance;

    private long lastRideTime;

    public Mount(UUID ownerId, MountType type) {
        this(UUID.randomUUID(), ownerId, type, 0.0D);
    }

    public Mount(UUID id, UUID ownerId, MountType type, double distance) {
        this.id = id;
        this.ownerId = ownerId;
        this.type = type;
        this.entity = null;
        this.distance = distance;
        this.lastRideTime = 0L;
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getOwnerId() {
        return this.ownerId;
    }

    public MountEntity getMountEntity() {
        return this.entity;
    }

    public boolean isOwner(Player player) {
        return this.ownerId.equals(player.getUniqueId());
    }

    public MountType getType() {
        return this.type;
    }

    public double getDistance() {
        return this.distance;
    }

    public void addDistance(double distance) {
        this.distance += distance;
    }

    public ItemStack getItem() {
        return this.type.getItem();
    }

    public ItemStack getItem(Player player) {
        return this.type.getItem(player);
    }

    public void ride(Player player) {
        if (!this.type.hasPermission(player) || player.isInsideVehicle() ||
                !MountsPlugin.getInstance().getRegionControl().canRide(player)) {
            player.sendMessage(Config.messageMountCannotRide());
            return;
        }
        if (Config.mountCooldownSeconds() > 0L &&
                TimeUtil.seconds() - this.lastRideTime <= Config.mountCooldownSeconds()) {
            player.sendMessage(Config.messageMountCooldown());
            return;
        }
        for (Requirement requirement : this.type.getRequirements()) {
            if (!requirement.isSatisfied(player)) {
                player.sendMessage(MessageUtil.colorize(
                        String.format("%s%s", new Object[] { Config.messageMountRequirement(), requirement.getErrorMessage() })));
                return;
            }
        }
        Vehicle vehicle = this.type.spawn(player);
        vehicle.addPassenger((Entity)player);
        vehicle.setInvulnerable(true);
        this.entity = new MountEntity(player, this, vehicle);
        SoundUtil.playSound(player, Config.soundRideMount());
    }

    public void store() {
        if (this.entity != null) {
            this.lastRideTime = TimeUtil.seconds();
            SoundUtil.playSound(this.entity.getPlayer(), Config.soundStoreMount());
            this.entity.remove();
            this.entity = null;
            MountsPlugin.getInstance().getDataStore().save(this);
        }
    }

    public static Mount get(Player player) {
        if (player.isInsideVehicle())
            return get(player.getVehicle());
        return null;
    }

    public static Mount get(Entity entity) {
        return MountEntity.get(entity);
    }

    public static boolean isRiding(Player player) {
        return (get(player) != null);
    }
}