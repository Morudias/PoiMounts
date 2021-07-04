package me.critikull.mounts.datastore;

import me.critikull.mounts.mount.Mount;
import me.critikull.mounts.mount.Mounts;
import me.critikull.mounts.mount.types.MountType;
import me.critikull.mounts.mount.types.MountTypes;

public interface IDataStore {
    void save(Mount paramMount);

    void save(MountType paramMountType);

    void remove(Mount paramMount);

    Mounts loadMounts();

    MountTypes loadMountTypes();
}
