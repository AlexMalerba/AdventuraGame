package com.labollo.entity;

import com.labollo.main.GamePanel;
import com.labollo.main.KeyHandler;
import com.labollo.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Player extends Entity {
    // ---> Properties of com.labollo package
    GamePanel gp;
    KeyHandler keyH;

    // ---> Properties of this class
    public final int screenX; // It tells the X coordinates of the object (It isn't always implements)
    public final int screenY; // It tells the Y coordinates of the object (It isn't always implements)
    public int hasKey = 0; // It tells the number of keys that the player has
    // private boolean chestOpenedThisIteration = false; // It tells if the player opened a chest in this iteration

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = this.gp.SCREEN_WIDTH / 2 - (this.gp.TILE_SIZE / 2); // 768 / 2 - 24 = 360
        screenY = this.gp.SCREEN_HEIGHT / 2 - (this.gp.TILE_SIZE / 2); // 576 / 2 - 24 = 264

        super.solidArea = new Rectangle(); // It sets the object solid area
        super.solidArea.x = 25; // 25: 48 - 8 - 15 = 25 (48 is the tile size, 8 is the solid area width, 15 is the solid area x)
        super.solidArea.y = 40; // 40: 48 - 12 - 15 = 40 (48 is the tile size, 12 is the solid area height, 15 is the solid area y)
        super.solidArea.width = 8; // 8: 48 - 15 - 25 = 8 (48 is the tile size, 15 is the solid area x, 25 is the solid area width)
        super.solidArea.height = 12; // 12: 48 - 15 - 40 = 12 (48 is the tile size, 15 is the solid area y, 40 is the solid area height)

        super.solidAreaDefaultX = solidArea.x; // 25: 48 - 8 - 15 = 25 (48 is the tile size, 8 is the solid area width, 15 is the solid area x)
        super.solidAreaDefaultY = solidArea.y; // 40: 48 - 12 - 15 = 40 (48 is the tile size, 12 is the solid area height, 15 is the solid area y)

        this.setDefaultValues(); // It sets the default values such as the player's position
        this.getPlayerImage(); // It creates the player's object via the sprites in path: /resources/player/
    }

    public void setDefaultValues() {
        super.worldX = 63 * gp.TILE_SIZE; // 63 * 48 = 3024
        super.worldY = 23 * gp.TILE_SIZE; // 23 * 48 = 1104
        super.speed = 4; // It sets the player's speed (4 pixels per frame: 4 * 60 = 240 pixels per second)
        super.direction = "down"; // It sets the player's direction
        super.maxLife = 6; // It sets the player's max life
        super.life = maxLife; // It sets the player's life
        this.hasKey = 5; // It sets the number of keys that the player has
    }

    // Create player's object via the sprites in path: /resources/player/
    public void getPlayerImage() {

        // Load the sprites
        super.up1 = setup("/player/up1.png");
        super.up2 = setup("/player/up2.png");
        super.down1 = setup("/player/down1.png");
        super.down2 = setup("/player/down2.png");
        super.right1 = setup("/player/right1.png");
        super.right2 = setup("/player/right2.png");
        super.left1 = setup("/player/left1.png");
        super.left2 = setup("/player/left2.png");
    }

    // Load the sprites
    public BufferedImage setup(String filePath) {
        UtilityTool ut = new UtilityTool(); // It creates a new UtilityTool object
        BufferedImage image = null; // It contains the sprite image

        // It reads the sprite image and scales it
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource(filePath))); // It reads the sprite image
            image = ut.scaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE); // It scales the sprite image
        } catch (IOException e) {
            throw new RuntimeException(e); // It throws a new RuntimeException
        }

        return image;
    }

    /**
     * Updates the player's state based on user input and game logic.
     * Checks for collision with tiles and updates the player's position accordingly.
     * Manages player sprite animations based on movement.
     */
    public void update() {
        //chestOpenedThisIteration = false;

        // Check if any key is pressed
        if(this.keyH.upPressed || this.keyH.downPressed || this.keyH.leftPressed || this.keyH.rightPressed) {

            // Check tile collision
            super.collisionOn = false;
            this.gp.cChecker.checkTile(this);

            // Check object collision
            int objIndex = gp.cChecker.checkObject(this, true); // Check if the player is colliding with an object
            if (objIndex != 999) // If the player is colliding with an object, pick it up (objIndex != 999)
                this.pickUpObject(objIndex); // Pick up the object

            // Moving on the map, but first, check for collision
            if(this.keyH.upPressed) {
                super.direction = "up"; // Set the direction
                if(!super.collisionOn) { // Move up only if there is no collision
                    if(this.keyH.shiftPressed)
                        super.worldY -= (super.speed * 2); // Move up faster if shift is pressed
                    else
                        super.worldY -= super.speed; // Move up
                }
            }
            if(keyH.downPressed) {
                super.direction = "down"; // Set the direction
                if (!super.collisionOn) { // Move down only if there is no collision
                    if(this.keyH.shiftPressed)
                        super.worldY += (super.speed * 2); // Move down faster if shift is pressed
                    else
                        super.worldY += super.speed; // Move down
                }
            }
            if(this.keyH.leftPressed) {
                super.direction = "left"; // Set the direction
                if(!super.collisionOn) { // Move left only if there is no collision
                    if(this.keyH.shiftPressed)
                        super.worldX -= (super.speed * 2); // Move left faster if shift is pressed
                    else
                        super.worldX -= super.speed; // Move left
                }
            }
            if(this.keyH.rightPressed){
                super.direction = "right"; // Set the direction
                if(!super.collisionOn) { // Move right only if there is no collision
                    if(this.keyH.shiftPressed)
                        super.worldX += (super.speed * 2); // Move right faster if shift is pressed
                    else
                        super.worldX += super.speed; // Move right
                }
            }


            // Manage sprite animation
            super.spriteCounter++; // Increase the sprite counter
            if(this.keyH.shiftPressed) { // If shift is pressed, change sprite putting the second (running) every 5 frames
                if(super.spriteCounter > 5) { // If the sprite counter is greater than 5
                    if(super.spriteNum == 1) { // If the sprite number is 1
                        super.spriteNum = 2; // Set the sprite number to 2
                    } else if (super.spriteNum == 2) { // If the sprite number is 2
                        super.spriteNum = 1; // Set the sprite number to 1
                    }
                    super.spriteCounter = 0; // Reset the sprite counter
                }
            } else { // If shift isn't pressed, change sprite putting the second (running) every 10 frames
                if(super.spriteCounter > 10) { // If the sprite counter is greater than 10
                    if(super.spriteNum == 1) { // If the sprite number is 1
                        super.spriteNum = 2; // Set the sprite number to 2
                    } else if (super.spriteNum == 2) { // If the sprite number is 2
                        super.spriteNum = 1; // Set the sprite number to 1
                    }
                    super.spriteCounter = 0; // Reset the sprite counter to 0 because it's time to change sprite
                }
            }

            // Change sprite putting the second (running) if he was still (first was still, now he's in moving)
            if(super.wasStill) { // If he was still (first was still)
                super.spriteNum = 2; // Set the sprite number to 2
                super.wasStill = false; // Set wasStill to false
            }
        } else {
            super.spriteNum = 1; // Set the sprite number to 1
            super.spriteCounter = 10; // Set the sprite counter to 10
            super.wasStill = true; // Set wasStill to true
        }
    }

    // Pick up the object (index != 999)
    public void pickUpObject(int index) {
        if (this.gp.obj[index] != null) { // If the object isn't null
            String objName = this.gp.obj[index].name; // Get the object name

            switch (objName) { // Check the object name

                // If the object name is potion00 or potion01, set the object to null
                case "potion00", "potion01" -> this.gp.obj[index] = null;

                // If the object name is heart00, set the object to null and increase the player's health
                case "key00" -> {
                    this.hasKey++;
                    this.gp.obj[index] = null;
                    this.gp.ui.setMessage("YOU TOOK A KEY");
                }

                // If the object name is door00, check if the player has the key
                case "door00" -> {
                    if (this.hasKey > 0) {
                        this.gp.obj[index] = null;
                        this.hasKey--;
                        this.gp.ui.setMessage("YOU OPENED THE DOOR");
                    } else {
                        this.gp.ui.setMessage("YOU NEED A KEY TO OPEN THE DOOR");
                    }
                }

                // If the object name is casket00, check if the player has the key and open the casket
                case "casket00" -> {
                    if(gp.obj[index].status == 0) { // If the casket is closed
                        if (keyH.cPressed) { // If the player pressed the C key
                            if (this.hasKey > 0) { // If the player has the key
                                this.hasKey--; // Decrease the number of keys
                                this.gp.ui.setMessage("YOU OPENED THE CASKET");
                                Random rand = new Random(); // It creates a random number
                                int num = Math.abs(rand.nextInt(2)); // It creates a random number between 0 and 1

                                gp.obj[index].status(1); // Set the casket status to 1 (opened sprite)

                                if (num == 0) { // If the random number is 0
                                    this.gp.obj[4].worldX = gp.obj[index].worldX; // Set the potion00 X coordinate
                                    this.gp.obj[4].worldY = gp.obj[index].worldY + 2 * gp.TILE_SIZE; // Set the potion00 Y coordinate
                                    System.out.println("P1");
                                    System.out.println("X: " + this.gp.obj[6].worldX);
                                    System.out.println("Y: " + this.gp.obj[6].worldY);
                                } else { // If the random number is 1
                                    this.gp.obj[5].worldX = gp.obj[index].worldX; // Set the potion01 X coordinate
                                    this.gp.obj[5].worldY = gp.obj[index].worldY + 2 * gp.TILE_SIZE; // Set the potion01 Y coordinate
                                    System.out.println("P2");
                                    System.out.println("X: " + this.gp.obj[7].worldX);
                                    System.out.println("Y: " + this.gp.obj[7].worldY);
                                }
                            } else // If the player hasn't the key
                                this.gp.ui.setMessage("YOU NEED A KEY TO OPEN THE CASKET");
                        }
                    }
                }
            }
        }
    }

    // Draw the player's object
    public void draw(Graphics g2) {
        BufferedImage image = null; // It contains the sprite image
        switch (super.direction) { // Check the player's direction
            case "up" -> {
                if (super.spriteNum == 1) // If the sprite number is 1
                    image = super.up1; // Set the image to up1
                if (super.spriteNum == 2) // If the sprite number is 2
                    image = super.up2; // Set the image to up2
            }
            case "down" -> { // If the player's direction is down
                if (super.spriteNum == 1) // If the sprite number is 1
                    image = super.down1; // Set the image to down1
                if (super.spriteNum == 2) // If the sprite number is 2
                    image = super.down2; // Set the image to down2
            }
            case "left" -> { // If the player's direction is left
                if (super.spriteNum == 1) // If the sprite number is 1
                    image = super.left1; // Set the image to left1
                if (super.spriteNum == 2) // If the sprite number is 2
                    image = super.left2; // Set the image to left2
            }
            case "right" -> { // If the player's direction is right
                if (super.spriteNum == 1) // If the sprite number is 1
                    image = super.right1; // Set the image to right1
                if (super.spriteNum == 2) // If the sprite number is 2
                    image = super.right2; // Set the image to right2
            }
        }

        g2.drawImage(image, this.screenX, this.screenY, null); // Draw the player's object

        /*
         * image: It contains the sprite image
         * this.screenX / this.screenY: It tells the X/Y coordinates of the object
         * this.gp.TILE_SIZE: It tells the tile size (48 * 48 px)
         * null: It tells the image observer (observer: It's an object that wishes to be notified when the image is being updated)
        */
    }
}