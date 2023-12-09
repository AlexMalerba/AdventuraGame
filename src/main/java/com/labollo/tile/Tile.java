package com.labollo.tile;

import java.awt.image.BufferedImage;

public class Tile {

    // ---> Properties of JDK
    public BufferedImage image;

    // ---> Properties of this class
    public boolean collision;

    public Tile(BufferedImage image, boolean collision) {
        this.image = image;
        this.collision = collision;
    }
}
