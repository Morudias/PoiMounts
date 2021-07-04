package me.critikull.mounts.mount;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mounts implements Iterable<Mount> {
    private List<Mount> mounts = new ArrayList<>();

    public void add(Mount mount) {
        this.mounts.add(mount);
    }

    public Iterator<Mount> iterator() {
        return this.mounts.iterator();
    }

    public int size() {
        return this.mounts.size();
    }

    public Mount get(Player player) {
        for (Mount mount : this.mounts) {
            if (mount.isOwner(player))
                return mount;
        }
        return null;
    }

    public Mount get(int index) {
        try {
            return this.mounts.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addAll(Mounts mounts) {
        for (Mount mount : mounts)
            this.mounts.add(mount);
    }

    public void removeAll() {
        this.mounts.clear();
    }

    public void remove(Mount mount) {
        this.mounts.remove(mount);
    }

    public boolean contains(Mount mount) {
        return this.mounts.contains(mount);
    }
}
