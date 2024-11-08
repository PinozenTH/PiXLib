package com.pinont.piXLib.api.worldedit;

import com.sk89q.worldedit.regions.Region;
import lombok.Getter;

@Getter
public class WERegion {
    private final Region region;

    public WERegion(com.sk89q.worldedit.regions.Region region) {
        this.region = region;
    }

}
