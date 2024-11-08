package com.pinont.piXLib.api.worldedit;

import com.sk89q.worldedit.WorldEdit;
import lombok.Getter;

public class worldEdit {
    @Getter
    protected final WorldEdit worldEdit = WorldEdit.getInstance();
    protected boolean isHooked;

    public void WorldEdit() {
        isHooked = worldEdit != null;
    }

    public String isHooked() {
        return isHooked ? "WorldEdit is hooked!" : "WorldEdit is not hooked!";
    }
}
