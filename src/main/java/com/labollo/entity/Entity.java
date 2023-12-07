package com.labollo.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    // Properties of this class
    public int worldX, worldY; // It tells the X/Y coordinates of the object (It isn't always implements)
    public int speed; // It tells the speed of the object

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2; // It contains the sprite images
    public String direction; // It tells the direction of the object

    public int spriteCounter = 0; // It tells the sprite counter
    public int spriteNum = 1; // It tells the sprite number
    public boolean wasStill = false; // It tells if the object was still

    public Rectangle solidArea; // It sets the object solid area
    public int solidAreaDefaultX, solidAreaDefaultY; // It sets the object solid area default X/Y coordinates
    public boolean collisionOn = false; // It tells if the object is colliding with another object
}
