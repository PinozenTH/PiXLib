package com.pinont.experiences.api.builders;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EntityBuilder {

    private final EntityType entityType;
    private Entity passenger = null;
    private List<String> ScoreboardTag;
    private int fireTicks;
    private boolean glowing;
    private boolean invulnerable;
    private boolean silent;
    private boolean gravity;
    private boolean persistent;
    private int freezeTicks;
    private boolean customNameVisible;
    private int portalCooldown;
    private float fallingDistance;
    private float[] rotation = null;
    private Vector vector = null;
    private boolean visualFire;
    private boolean visibleByDefault;
    private int ticksLived;

    public EntityBuilder(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityBuilder addPassenger(Entity passenger) {
        this.passenger = passenger;
        return this;
    }


    public EntityBuilder addScoreboardTag(String... ScoreboardTag) {
        Collections.addAll(this.ScoreboardTag, ScoreboardTag);
        return this;
    }

    public EntityBuilder setFireTicks(int ticks) {
        this.fireTicks = ticks;
        return this;
    }

    public EntityBuilder setGlowing(boolean glowing) {
        this.glowing = glowing;
        return this;
    }

    public EntityBuilder setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
        return this;
    }

    public EntityBuilder setSilent(boolean silent) {
        this.silent = silent;
        return this;
    }

    public EntityBuilder hasGravity(boolean gravity) {
        this.gravity = gravity;
        return this;
    }

    public EntityBuilder setVelocity(Vector vector) {
        this.vector = vector;
        return this;
    }

    public EntityBuilder setPersistent(Boolean persistent) {
        this.persistent = persistent;
        return this;
    }

    public EntityBuilder setFreezeTicks(int ticks) {
        this.freezeTicks = ticks;
        return this;
    }

    public EntityBuilder setCustomNameVisible(Boolean visible) {
        this.customNameVisible = visible;
        return this;
    }

    public EntityBuilder setPortalCooldown(int ticks) {
        this.portalCooldown = ticks;
        return this;
    }

    public EntityBuilder setFallingDistance(float distance) {
        this.fallingDistance = distance;
        return this;
    }

    public EntityBuilder setRotation(float yaw, float pitch) {
        this.rotation = new float[]{yaw, pitch};
        return this;
    }

    public EntityBuilder setTicksLived(int ticks) {
        this.ticksLived = ticks;
        return this;
    }

    public EntityBuilder setVisibleByDefault(boolean visible) {
        this.visibleByDefault = visible;
        return this;
    }

    public EntityBuilder setVisualFire(boolean fire) {
        this.visualFire = fire;
        return this;
    }

    public Entity spawn(Location location) {
        Entity entity = Objects.requireNonNull(location.getWorld()).spawnEntity(location, entityType);

        if (passenger != null) entity.addPassenger(passenger);
        if (ScoreboardTag != null) {
            for (String tag : ScoreboardTag) {
                entity.addScoreboardTag(tag);
            }
        }
        if (entity.getFireTicks() != fireTicks) entity.setFireTicks(fireTicks);
        if (entity.isGlowing() != glowing) entity.setGlowing(glowing);
        if (entity.isInvulnerable() != invulnerable) entity.setInvulnerable(invulnerable);
        if (entity.isSilent() != silent) entity.setSilent(silent);
        if (entity.hasGravity() != gravity) entity.setGravity(gravity);
        if (vector != null) entity.setVelocity(vector);
        if (entity.isPersistent() != persistent) entity.setPersistent(persistent);
        if (entity.getFreezeTicks() != freezeTicks) entity.setFreezeTicks(freezeTicks);
        if (entity.isCustomNameVisible() != customNameVisible) entity.setCustomNameVisible(customNameVisible);
        if (entity.getPortalCooldown() != portalCooldown) entity.setPortalCooldown(portalCooldown);
        if (entity.getFallDistance() != fallingDistance) entity.setFallDistance(fallingDistance);
        if (rotation != null) entity.setRotation(rotation[0], rotation[1]);
        if (entity.getTicksLived() != ticksLived) entity.setTicksLived(ticksLived);
        if (entity.isVisibleByDefault() != visibleByDefault) entity.setVisibleByDefault(visibleByDefault);
        if (entity.isVisualFire() != visualFire) entity.setVisualFire(visualFire);

        return entity;
    }

    public Entity spawn(World world, double x, double y, double z) {
        return spawn(new Location(world, x, y, z));
    }
}
