package me.critikull.mounts.mount;

import me.critikull.mounts.MountsPlugin;
import me.critikull.mounts.utils.TimeUtil;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class MountEntity implements Runnable {
    private static final String METADATA_KEY = "Mounts.Mount";

    private static final long DISTANCE_INTERVAL = 1000L;

    private final Player player;

    private final Vehicle vehicle;

    private final Mount mount;

    private final int taskId;

    private Location lastLocation;

    private long lastDistanceTime;

    public MountEntity(Player player, Mount mount, Vehicle vehicle) {
        this.player = player;
        this.mount = mount;
        this.vehicle = vehicle;
        this.vehicle.setMetadata("Mounts.Mount", (MetadataValue)new FixedMetadataValue((Plugin) MountsPlugin.getInstance(), this.mount));
        this.lastLocation = vehicle.getLocation();
        this.lastDistanceTime = 0L;
        this
                .taskId = MountsPlugin.getInstance().getServer().getScheduler().runTaskTimer((Plugin)MountsPlugin.getInstance(), this, 0L, 1L).getTaskId();
    }

    public Player getPlayer() {
        return this.player;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public Entity getEntity() {
        return (Entity)this.vehicle;
    }

    public void remove() {
        this.vehicle.removeMetadata("Mounts.Mount", (Plugin)MountsPlugin.getInstance());
        this.vehicle.eject();
        this.vehicle.remove();
        MountsPlugin.getInstance().getServer().getScheduler().cancelTask(this.taskId);
    }

    public static Mount get(Entity entity) {
        if (entity.hasMetadata("Mounts.Mount"))
            return (Mount)((MetadataValue)entity.getMetadata("Mounts.Mount").get(0)).value();
        return null;
    }

    private void recordDistance() {
        if (TimeUtil.milliseconds() - this.lastDistanceTime >= 1000L) {
            this.mount.addDistance(this.lastLocation.distance(this.vehicle.getLocation()));
            this.lastLocation = this.vehicle.getLocation();
            this.lastDistanceTime = TimeUtil.milliseconds();
        }
    }

    public void run() {
        recordDistance();
    }
}