package com.labollo.entity;

import com.labollo.main.GamePanel;
import com.labollo.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        super.solidArea = new Rectangle(25, 40, 8, 12);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = 25 * gp.tileSize;
        worldY = 25 * gp.tileSize;
        speed = 4;
        direction = "down";
    }

    // Create player's object via the sprites in path: /resources/player/
    public void getPlayerImage() {

        try {
            Image player1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/up1.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/down2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/right2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/left2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the player's state based on user input and game logic.
     * Checks for collision with tiles and updates the player's position accordingly.
     * Manages player sprite animations based on movement.
     */
    public void update() {

        // Check if any key is pressed
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Moving on the map, but first, check for collision
            if(keyH.upPressed) {
                direction = "up";
                if(!collisionOn) { // Move up only if there is no collision
                    if(keyH.shiftPressed)
                        worldY -= (speed * 2);
                    else
                        worldY -= speed;
                }
            }
            if(keyH.downPressed) {
                direction = "down";
                if (!collisionOn) { // Move down only if there is no collision
                    if(keyH.shiftPressed)
                        worldY += (speed * 2);
                    else
                        worldY += speed;
                }
            }
            if(keyH.leftPressed) {
                direction = "left";
                if(!collisionOn) { // Move left only if there is no collision
                    if(keyH.shiftPressed)
                        worldX -= (speed * 2);
                    else
                        worldX -= speed;
                }
            }
            if(keyH.rightPressed){
                direction = "right";
                if(!collisionOn) { // Move right only if there is no collision
                    if(keyH.shiftPressed)
                        worldX += (speed * 2);
                    else
                        worldX += speed;
                }
            }


            // Manage sprite animation
            spriteCounter++;
            if(keyH.shiftPressed) {
                if(spriteCounter > 5) {
                    if(spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            } else {
                if(spriteCounter > 10) {
                    if(spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

            // Change sprite putting the second (running) if he was still (first was still, now he's in moving)
            if(wasStill) {
                spriteNum = 2;
                wasStill = false;
            }


        } else {
            spriteNum = 1;
            spriteCounter = 10;
            wasStill = true;
        }
    }

    public void paint(Graphics g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
