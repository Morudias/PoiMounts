package me.critikull.mounts.mount.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MountTypes implements Iterable<MountType> {
    private List<MountType> types = new ArrayList<>();

    public void add(MountType type) {
        this.types.add(type);
    }

    public Iterator<MountType> iterator() {
        return this.types.iterator();
    }

    public int size() {
        return this.types.size();
    }

    public MountType get(String id) {
        for (MountType type : this.types) {
            if (type.getId().equals(id))
                return type;
        }
        return null;
    }

    public void removeAll() {
        this.types.clear();
    }

    public MountType get(int index) {
        try {
            return this.types.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
