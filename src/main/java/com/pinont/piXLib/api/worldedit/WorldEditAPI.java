package com.pinont.piXLib.api.worldedit;

import com.google.common.eventbus.Subscribe;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.platform.Actor;
import lombok.Getter;
import org.bukkit.entity.Player;

public class WorldEditAPI {
    @Getter
    protected final WorldEdit worldEdit = WorldEdit.getInstance();
    protected boolean isHooked;
    protected Actor actor;
    
    public void WorldEdit() {
        isHooked = worldEdit != null;
    }

    public String isHooked() {
        return isHooked ? "WorldEdit is hooked!" : "WorldEdit is not hooked!";
    }

    public void setActor(Player actor) {
        this.actor = (Actor) actor;
    }
}
