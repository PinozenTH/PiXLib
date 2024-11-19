package com.pinont.experiences.api.tiles;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Cuboid {
    @Getter
    private final World world;
    @Getter
    private final int minX;
    @Getter
    private final int maxX;
    @Getter
    private final int minY;
    @Getter
    private final int maxY;
    @Getter
    private final int minZ;
    @Getter
    private final int maxZ;
    @Setter
    private List<Entity> containsEntities = new ArrayList<>();

    public Cuboid(Location loc1, Location loc2) {
        this(loc1.getWorld(), loc1.getBlockX(), loc1.getBlockY(), loc1.getBlockZ(), loc2.getBlockX(), loc2.getBlockY(), loc2.getBlockZ());
    }

    public Cuboid(World world, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.world = world;

        minX = Math.min(x1, x2);
        minY = Math.min(y1, y2);
        minZ = Math.min(z1, z2);
        maxX = Math.max(x1, x2);
        maxY = Math.max(y1, y2);
        maxZ = Math.max(z1, z2);
    }

    public Cuboid(World world, double v, double v1, double v2) {
        this(world, (int) v, (int) v1, (int) v2, (int) v, (int) v1, (int) v2);
    }

    public List<Location> getFilteredBlockLocations(Block block) {
        List<Location> filteredLocations = new ArrayList<>();
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    if (this.contains(block.getLocation())) {
                        filteredLocations.add(new Location(world, x, y, z));
                    }
                }
            }
        } return filteredLocations;
    }

    public List<Location> getFilteredBlockLocations(Material block) {
        List<Location> filteredLocations = new ArrayList<>();
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    if (new Location(world, x, y, z).getBlock().getType().equals(block)) {
                        filteredLocations.add(new Location(world, x, y, z));
                    }
                }
            }
        } return filteredLocations;
    }

    public boolean contains(Cuboid cuboid) {
        return cuboid.getWorld().equals(world) &&
                cuboid.getMinX() >= minX && cuboid.getMaxX() <= maxX &&
                cuboid.getMinY() >= minY && cuboid.getMaxY() <= maxY &&
                cuboid.getMinZ() >= minZ && cuboid.getMaxZ() <= maxZ;
    }

    public boolean contains(Location location) {
        return contains(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public boolean contains(int x, int y, int z) {
        return x >= minX && x <= maxX &&
                y >= minY && y <= maxY &&
                z >= minZ && z <= maxZ;
    }

    public boolean contains(Material block) {
        return block.equals(new Location(world, minX, minY, minZ).getBlock().getType());
    }

    public boolean contains(Block block) {
        return block.getLocation().equals(new Location(world, minX, minY, minZ));
    }

    public boolean contains(Sphere sphere) {
        return sphere.contains(this);
    }

    public boolean overlaps(Cuboid cuboid) {
        return cuboid.getWorld().equals(world) &&
                !(cuboid.getMinX() > maxX || cuboid.getMinY() > maxY || cuboid.getMinZ() > maxZ ||
                        minZ > cuboid.getMaxX() || minY > cuboid.getMaxY() || minZ > cuboid.getMaxZ());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cuboid other)) {
            return false;
        }
        return world.equals(other.world)
                && minX == other.minX
                && minY == other.minY
                && minZ == other.minZ
                && maxX == other.maxX
                && maxY == other.maxY
                && maxZ == other.maxZ;
    }

    @Override
    public String toString() {
        return "Cuboid[world:" + world.getName() +
                ", minX:" + minX +
                ", minY:" + minY +
                ", minZ:" + minZ +
                ", maxX:" + maxX +
                ", maxY:" + maxY +
                ", maxZ:" + maxZ + "]";
    }

    public List<Entity> getContainsEntities() {
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    containsEntities.addAll(List.of(world.getChunkAt(new Location(world, x, y, z)).getEntities()));
                }
            }
        }
        return containsEntities;
    }
}
