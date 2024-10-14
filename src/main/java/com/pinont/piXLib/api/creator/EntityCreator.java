package com.pinont.piXLib.api.creator;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

public class EntityCreator {

    private final EntityType entityType;
    private Entity entity;

    public EntityCreator(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityCreator addPassenger(Entity passenger) {
        entity.addPassenger(passenger);
        return this;
    }

    public EntityCreator addScoreboardTag(String tag) {
        entity.addScoreboardTag(tag);
        return this;
    }

    public EntityCreator setFireTicks(int ticks) {
        entity.setFireTicks(ticks);
        return this;
    }

    public EntityCreator setGlowing(boolean glowing) {
        entity.setGlowing(glowing);
        return this;
    }

    public EntityCreator setInvulnerable(boolean invulnerable) {
        entity.setInvulnerable(invulnerable);
        return this;
    }

    public EntityCreator setSilent(boolean silent) {
        entity.setSilent(silent);
        return this;
    }

    public EntityCreator hasGravity(boolean gravity) {
        entity.setGravity(gravity);
        return this;
    }

    public EntityCreator setVelocity(double x, double y, double z) {
        entity.setVelocity(entity.getVelocity().setX(x).setY(y).setZ(z));
        return this;
    }

    public EntityCreator setVelocity(Vector vector) {
        entity.setVelocity(vector);
        return this;
    }

    public EntityCreator setPersistent(Boolean persistent) {
        entity.setPersistent(persistent);
        return this;
    }

    public EntityCreator setFreezeTicks(int ticks) {
        entity.setFreezeTicks(ticks);
        return this;
    }

    public EntityCreator setCustomNameVisible(Boolean visible) {
        entity.setCustomNameVisible(visible);
        return this;
    }

    public EntityCreator setPortalCooldown(int ticks) {
        entity.setPortalCooldown(ticks);
        return this;
    }

    public EntityCreator setFallingDistance(float distance) {
        entity.setFallDistance(distance);
        return this;
    }

    public EntityCreator setRotation(float yaw, float pitch) {
        entity.setRotation(yaw, pitch);
        return this;
    }

    public EntityCreator setTicksLived(int ticks) {
        entity.setTicksLived(ticks);
        return this;
    }

    public EntityCreator setVisibleByDefault(boolean visible) {
        entity.setVisibleByDefault(visible);
        return this;
    }

    public EntityCreator setVisualFire(boolean fire) {
        entity.setVisualFire(fire);
        return this;
    }

    public Entity spawn(World world, Location location) {
        this.entity = world.spawnEntity(location, entityType);
        return entity;
    }

    public Entity spawn(World world, double x, double y, double z) {
        this.entity = world.spawnEntity(new Location(world, x,y,z), entityType);
        return entity;
    }

}
