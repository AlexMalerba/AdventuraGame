package com.labollo.entity;

import com.labollo.main.GamePanel;
import com.labollo.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = this.gp.screenWidth / 2 - (this.gp.tileSize / 2);
        screenY = this.gp.screenHeight / 2 - (this.gp.tileSize / 2);

        super.solidArea = new Rectangle();
        super.solidArea.x = 25;
        super.solidArea.y = 40;
        super.solidArea.width = 8;
        super.solidArea.height = 12;

        super.solidAreaDefaultX = solidArea.x;
        super.solidAreaDefaultY = solidArea.y;

        this.setDefaultValues();
        this.getPlayerImage();
    }

    public void setDefaultValues() {
        super.worldX = 25 * gp.tileSize;
        super.worldY = 25 * gp.tileSize;
        super.speed = 4;
        super.direction = "down";
        this.hasKey = 2;
    }

    // Create player's object via the sprites in path: /resources/player/
    public void getPlayerImage() {

        try {
            Image player1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/up1.png")));
            super.up1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/up1.png")));
            super.up2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/up2.png")));
            super.down1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/down1.png")));
            super.down2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/down2.png")));
            super.right1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/right1.png")));
            super.right2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/right2.png")));
            super.left1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/left1.png")));
            super.left2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/left2.png")));

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
        if(this.keyH.upPressed || this.keyH.downPressed || this.keyH.leftPressed || this.keyH.rightPressed) {

            // Check tile collision
            super.collisionOn = false;
            this.gp.cChecker.checkTile(this);

            // Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            this.pickUpObject(objIndex);

            // Moving on the map, but first, check for collision
            if(this.keyH.upPressed) {
                super.direction = "up";
                if(!super.collisionOn) { // Move up only if there is no collision
                    if(this.keyH.shiftPressed)
                        super.worldY -= (super.speed * 2);
                    else
                        super.worldY -= super.speed;
                }
            }
            if(keyH.downPressed) {
                super.direction = "down";
                if (!super.collisionOn) { // Move down only if there is no collision
                    if(this.keyH.shiftPressed)
                        super.worldY += (super.speed * 2);
                    else
                        super.worldY += super.speed;
                }
            }
            if(this.keyH.leftPressed) {
                super.direction = "left";
                if(!super.collisionOn) { // Move left only if there is no collision
                    if(this.keyH.shiftPressed)
                        super.worldX -= (super.speed * 2);
                    else
                        super.worldX -= super.speed;
                }
            }
            if(this.keyH.rightPressed){
                super.direction = "right";
                if(!super.collisionOn) { // Move right only if there is no collision
                    if(this.keyH.shiftPressed)
                        super.worldX += (super.speed * 2);
                    else
                        super.worldX += super.speed;
                }
            }


            // Manage sprite animation
            super.spriteCounter++;
            if(this.keyH.shiftPressed) {
                if(super.spriteCounter > 5) {
                    if(super.spriteNum == 1) {
                        super.spriteNum = 2;
                    } else if (super.spriteNum == 2) {
                        super.spriteNum = 1;
                    }
                    super.spriteCounter = 0;
                }
            } else {
                if(super.spriteCounter > 10) {
                    if(super.spriteNum == 1) {
                        super.spriteNum = 2;
                    } else if (super.spriteNum == 2) {
                        super.spriteNum = 1;
                    }
                    super.spriteCounter = 0;
                }
            }

            // Change sprite putting the second (running) if he was still (first was still, now he's in moving)
            if(super.wasStill) {
                super.spriteNum = 2;
                super.wasStill = false;
            }


        } else {
            super.spriteNum = 1;
            super.spriteCounter = 10;
            super.wasStill = true;
        }
    }

    public void pickUpObject(int index) {
        if(index != 999) {
            String objName = this.gp.obj[index].name;

            switch (objName) {
                case "potion00", "potion01" -> this.gp.obj[index] = null;

                case "key00" -> {
                    this.hasKey++;
                    this.gp.obj[index] = null;
                }

                case "door00" -> {
                    if (this.hasKey > 0) {
                        this.gp.obj[index] = null;
                        this.hasKey--;
                    } else
                        System.out.println("You don't have key!!!");
                }
                case "casketClosed00" -> {
                    if (keyH.cPressed) {
                        if (this.hasKey > 0) {
                            Random rand = new Random();
                            int num = Math.abs(rand.nextInt(2));
                            System.out.println(num);

                            this.gp.obj[index] = null;
                            //this.gp.obj[5].worldX = 12 * gp.tileSize;
                            //this.gp.obj[5].worldY = 34 * gp.tileSize;

                            if (num == 0) {
                                if (this.gp.obj[6] != null) {
                                    //this.gp.obj[6].worldX = 12 * gp.tileSize;
                                    //this.gp.obj[6].worldY = 36 * gp.tileSize;
                                } else
                                    System.out.println("a");
                            } else {
                                if (this.gp.obj[7] != null) {
                                    //this.gp.obj[7].worldX = 12 * gp.tileSize;
                                    //this.gp.obj[7].worldY = 36 * gp.tileSize;
                                } else
                                    System.out.println("b");
                            }
                            this.hasKey--;
                        } else {
                            System.out.println("You don't have key!!!");
                        }
                    }

                }

            }
        }
    }

    public void paint(Graphics g2) {
        BufferedImage image = null;
        switch (super.direction) {
            case "up" -> {
                if (super.spriteNum == 1) {
                    image = super.up1;
                }
                if (super.spriteNum == 2) {
                    image = super.up2;
                }
            }
            case "down" -> {
                if (super.spriteNum == 1) {
                    image = super.down1;
                }
                if (super.spriteNum == 2) {
                    image = super.down2;
                }
            }
            case "left" -> {
                if (super.spriteNum == 1) {
                    image = super.left1;
                }
                if (super.spriteNum == 2) {
                    image = super.left2;
                }
            }
            case "right" -> {
                if (super.spriteNum == 1) {
                    image = super.right1;
                }
                if (super.spriteNum == 2) {
                    image = super.right2;
                }
            }
        }
        g2.drawImage(image, this.screenX, this.screenY, this.gp.tileSize, this.gp.tileSize, null);
    }
}
