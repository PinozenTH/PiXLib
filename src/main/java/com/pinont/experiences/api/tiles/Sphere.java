package com.pinont.experiences.api.tiles;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public record Sphere(World world, int radius, int x, int y, int z) {

    private static final List<Entity> containsEntities = new ArrayList<>();

    public Float volume() {
        return (float) ((double) 4 / 3 * Math.PI * Math.pow(radius, 3));
    }

    public Float surfaceArea() {
        return (float) (4 * Math.PI * Math.pow(radius, 2));
    }

    public Float diameter() {
        return (float) (2 * radius);
    }

    public Float getCircumference() {
        return (float) (2 * Math.PI * radius);
    }

    public Float getArea() {
        return (float) (Math.PI * Math.pow(radius, 2));
    }

    public int getMinX() {
        return x - radius;
    }

    public int getMaxX() {
        return x + radius;
    }

    public int getMinY() {
        return y - radius;
    }

    public int getMaxY() {
        return y + radius;
    }

    public int getMinZ() {
        return z - radius;
    }

    public int getMaxZ() {
        return z + radius;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int z() {
        return z;
    }

    public Boolean contains(int x, int y, int z) {
        return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) + Math.pow(z - this.z, 2) <= Math.pow(radius, 2);
    }

    public Boolean contains(Sphere sphere) {
        return Math.pow(sphere.x() - this.x, 2) + Math.pow(sphere.y() - this.y, 2) + Math.pow(sphere.z() - this.z, 2) <= Math.pow(radius, 2);
    }

    public Boolean contains(Cuboid cuboid) {
        return cuboid.contains(this);
    }

    public boolean overlaps(Sphere sphere) {
        return Math.pow(sphere.x() - this.x, 2) + Math.pow(sphere.y() - this.y, 2) + Math.pow(sphere.z() - this.z, 2) <= Math.pow(sphere.radius() + this.radius, 2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sphere sphere) {
            return sphere.radius() == radius && sphere.x() == x && sphere.y() == y && sphere.z() == z;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Sphere{World:" + world.getName() + "[" +
                "radius=" + radius +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                "]}";
    }

    public List<Entity> getContainsEntities() {
        for (int x = getMinX(); x <= getMaxX(); x++) {
            for (int y = getMinY(); y <= getMaxY(); y++) {
                for (int z = getMinZ(); z <= getMinZ(); z++) {
                    containsEntities.addAll(List.of(world.getChunkAt(new Location(world, x, y, z)).getEntities()));
                }
            }
        }
        return containsEntities;
    }
}
