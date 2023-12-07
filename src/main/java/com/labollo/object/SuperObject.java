package com.labollo.object;

import com.labollo.main.GamePanel;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class SuperObject {
    public BufferedImage image; // It contains the sprite image
    public String name; // It contains the object name
    public boolean collision = false; // It tells if the player can interact with the object
    public int status = 0; // It tells the status of the object (If it's a chest: 0 == sprite closed; 1 == sprite opened)
    public abstract void status(int status); // This method is called when you need change the status
    public int worldX, worldY; // It tells the X/Y coordinates of the object (It isn't always implements)
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // It sets the object solid area
    public int solidAreaDefaultX = 0; // It sets the object solid area default X coordinate
    public int solidAreaDefaultY = 0; // It sets the object solid area default Y coordinate

    // This method is called when you need to draw the object
    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX  = worldX - gp.player.worldX + gp.player.screenX; // It tells the X coordinate of the object in the screen
        int screenY  = worldY - gp.player.worldY + gp.player.screenY; // It tells the Y coordinate of the object in the screen

        // Check if the object is in the screen (If it's not in the screen it isn't drawn)
        if(worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null); // It draws the object image
        }
    }
}
