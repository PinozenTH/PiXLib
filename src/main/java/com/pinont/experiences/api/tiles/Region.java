package com.pinont.experiences.api.tiles;

public record Region(int x, int z, int width, int height) {

    public boolean contains(int x, int z) {
        return x >= this.x && x < this.x + width && z >= this.z && z < this.z + height;
    }

    public boolean contains(Region region) {
        return contains(region.x, region.z) && contains(region.x + region.width - 1, region.z + region.height - 1);
    }

    public boolean intersects(Region region) {
        return contains(region.x, region.z) || contains(region.x + region.width - 1, region.z) || contains(region.x, region.z + region.height - 1) || contains(region.x + region.width - 1, region.z + region.height - 1);
    }

    public Region intersection(Region region) {
        int x1 = Math.max(x, region.x);
        int z1 = Math.max(z, region.z);
        int x2 = Math.min(x + width, region.x + region.width);
        int z2 = Math.min(z + height, region.z + region.height);
        return new Region(x1, z1, x2 - x1, z2 - z1);
    }

    public Region union(Region region) {
        int x1 = Math.min(x, region.x);
        int z1 = Math.min(z, region.z);
        int x2 = Math.max(x + width, region.x + region.width);
        int z2 = Math.max(z + height, region.z + region.height);
        return new Region(x1, z1, x2 - x1, z2 - z1);
    }

    public Region expand(int x, int z) {
        return new Region(this.x - x, this.z - z, width + 2 * x, height + 2 * z);
    }

    public Region expand(int x, int z, int width, int height) {
        return new Region(this.x - x, this.z - z, this.width + 2 * x, this.height + 2 * z);
    }

    public Region expand(int x, int z, int width, int height, int maxWidth, int maxHeight, int minWidth, int minHeight) {
        return new Region(this.x - x, this.z - z, Math.min(Math.max(this.width + 2 * x, minWidth), maxWidth), Math.min(Math.max(this.height + 2 * z, minHeight), maxHeight));
    }
}
