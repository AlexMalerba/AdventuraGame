package com.labollo.main;

import com.labollo.object.OBJ_heart;
import com.labollo.object.OBJ_key00;
import com.labollo.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    // ---> Properties of com.labollo package
    OBJ_key00 key;
    GamePanel gp;
    SuperObject heart = new OBJ_heart(gp);

    // ---> Properties of JDK
    Font algerian_40; // To set the font
    Font arial_15; // To set the font
    Graphics2D g2; // To draw the images
    Image heartImage; // To set the heart image

    // ---> Properties of this class
    String message = ""; // To set the message
    boolean messageOn = false; // If it's true, the message will be displayed
    int messageTimer = 0; // To set the time of the message (in frames: 60 frames = 1 second)

    public UI(GamePanel gp) {
        this.gp = gp;
        this.algerian_40 = new Font("algerian", Font.ITALIC, 40); // Font(String name, int style, int size)
        this.key = new OBJ_key00(gp);
        this.arial_15 = new Font("arial", Font.ITALIC, 15); // Font(String name, int style, int size)
        this.heart.status(0);
        this.heartImage = heart.image;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2; // It sets the Graphics2D object
        this.g2.setColor(Color.WHITE); // It sets the color

        drawPlayerLife(); // It draws the player life

        if(messageOn) {
            this.g2.setFont(arial_15); // It sets the font
            this.g2.drawString(message, this.gp.TILE_SIZE/2, this.gp.TILE_SIZE*5); // It draws the message

            this.messageTimer++; // It increases the messageTimer

            if(this.messageTimer > 120) { // If the messageTimer is greater than 120
                this.messageOn = false; // It sets the messageOn to false
                this.messageTimer = 0; // It sets the messageTimer to 0
            }
        }
    }

    public void setMessage(String message) {
        this.message = message; // It sets the message
        this.messageOn = true; // It sets the messageOn to true
    }

    public void drawPlayerLife() {
        int heartX = gp.TILE_SIZE/2; // It sets the heart X coordinate
        int heartY = gp.TILE_SIZE/2; // It sets the heart Y coordinate
        int i = 0; // It sets the counter

        while(i < gp.player.maxLife) {
            this.g2.drawImage(this.heartImage, heartX, heartY, 24, 24, null); // It draws the heart image
            i++; // It increases the counter
            heartX += gp.TILE_SIZE/2; // It increases the heart X coordinate
        }
    }
}
