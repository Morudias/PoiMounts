package me.critikull.mounts;

import me.critikull.mounts.mount.Mount;
import me.critikull.mounts.mount.Mounts;
import me.critikull.mounts.mount.types.MountType;
import me.critikull.mounts.mount.types.MountTypes;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MountManager {
    private final Mounts mounts = new Mounts();

    private final MountTypes mountTypes = new MountTypes();

    private final HashMap<UUID, Mount> presets = new HashMap<>();

    public void storeAll() {
        for (Mount mount : this.mounts)
            mount.store();
    }

    public void removeAll() {
        storeAll();
        this.mounts.removeAll();
        this.mountTypes.removeAll();
        this.presets.clear();
    }

    public Mounts getMounts() {
        return this.mounts;
    }

    public Mounts getMounts(Player player) {
        Mounts playerMounts = new Mounts();
        for (Mount mount : this.mounts) {
            if (mount.isOwner(player) && mount.getType().hasPermission(player))
                playerMounts.add(mount);
        }
        return playerMounts;
    }

    public MountType getMountType(String id) {
        for (MountType type : this.mountTypes) {
            if (type.getId().equals(id))
                return type;
        }
        return null;
    }

    public MountTypes getMountTypes(Player player) {
        MountTypes playerMountTypes = new MountTypes();
        for (MountType mountType : this.mountTypes) {
            if (mountType.hasPermission(player))
                playerMountTypes.add(mountType);
        }
        return playerMountTypes;
    }

    public void addMountType(MountType mountType) {
        this.mountTypes.add(mountType);
    }

    public void addMount(Mount mount) {
        this.mounts.add(mount);
    }

    public Mount getPreset(Player player) {
        Mount mount = this.presets.get(player.getUniqueId());
        if (mount != null && this.mounts.contains(mount))
            return mount;
        return null;
    }

    public void setPreset(Mount mount) {
        this.presets.put(mount.getOwnerId(), mount);
    }

    public Mount getMount(UUID playerId, UUID mountId) {
        for (Mount mount : this.mounts) {
            if (mount.getId().equals(mountId) && mount.getOwnerId().equals(playerId))
                return mount;
        }
        return null;
    }

    public Mount getMountByName(Player player, String mountName) {
        for (Mount mount : this.mounts) {
            if (mount.isOwner(player) && mount.getType().getName().equalsIgnoreCase(mountName))
                return mount;
        }
        return null;
    }

    public Mount getMountById(Player player, String id) {
        for (Mount mount : this.mounts) {
            if (mount.isOwner(player) && mount.getType().getId().equals(id))
                return mount;
        }
        return null;
    }

    public Mount removePreset(Player player) {
        return this.presets.remove(player.getUniqueId());
    }

    public void removeMount(Mount mount) {
        mount.store();
        this.mounts.remove(mount);
    }
}