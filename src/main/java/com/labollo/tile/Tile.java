package com.labollo.tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    public boolean collision;

    public Tile(BufferedImage image, boolean collision) {
        this.image = image;
        this.collision = collision;
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean hasCollision() {
        return collision;
    }
}
