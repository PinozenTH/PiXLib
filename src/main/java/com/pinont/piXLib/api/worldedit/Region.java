package com.pinont.piXLib.api.worldedit;

import lombok.Getter;

@Getter
public class Region extends WorldEditAPI {
    private final com.sk89q.worldedit.regions.Region region;

    public Region(com.sk89q.worldedit.regions.Region region) {
        this.region = region;
    }

}
