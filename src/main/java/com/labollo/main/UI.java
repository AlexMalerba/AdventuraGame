package com.labollo.main;

import com.labollo.object.OBJ_heart;
import com.labollo.object.OBJ_key00;
import com.labollo.object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.io.IOException;
import java.util.Objects;

public class UI {

    // ---> Properties of com.labollo package
    public OBJ_key00 key;
    public GamePanel gp;
    public SuperObject heart = new OBJ_heart(gp);

    // ---> Properties of JDK
    public Font arial_60; // To set the font
    public Font arial_15; // To set the font
    public Font arial_35; // To set the font
    public Graphics2D g2; // To draw the images
    public Image heartImage; // To set the heart image

    // ---> Properties of this class
    public String message = ""; // To set the message
    public boolean messageOn = false; // If it's true, the message will be displayed
    public int messageTimer = 0; // To set the time of the message (in frames: 60 frames = 1 second)
    public int commandNumber = 0; // To set the command number

    public UI(GamePanel gp) {
        this.arial_60 = new Font("arial", Font.BOLD, 70); // Font(String name, int style, int size)
        this.arial_15 = new Font("arial", Font.BOLD, 15); // Font(String name, int style, int size)
        this.arial_35 = new Font("arial", Font.BOLD, 35); // Font(String name, int style, int size)
        this.gp = gp; // It sets the GamePanel object
        this.key = new OBJ_key00(gp); // It creates the key object
        this.heart.status(0); // It sets the heart status
        this.heartImage = heart.image; // It sets the heart image
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2; // It sets the Graphics2D object
        this.g2.setColor(Color.WHITE); // It sets the color

        if(gp.gameState == gp.TITLESTATE) {
            drawTitleScreen(); // It draws the title screen
        } else {
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
    }

    public void drawTitleScreen() {
        this.g2.setColor(Color.BLACK); // It sets the color
        this.g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT); // It draws a rectangle that covers the entire screen

        // It draws the title text
        this.g2.setFont(arial_60); // It sets the font
        this.g2.setColor(Color.DARK_GRAY); // It sets the color of g2 from black to white
        String text = "Adventure Game"; // It sets the title text
        // It calculates the X and Y coordinates of the text
        int textX = getXforCenteredText(text); // It calculates the X coordinate of the text
        int textY = gp.SCREEN_HEIGHT / 7; // It calculates the Y coordinate of the text
        // It draws the shadow of the text
        this.g2.drawString(text, textX+3, textY+3);
        // It draws the text
        this.g2.setColor(Color.WHITE); // It sets the color of g2 from black to white
        this.g2.drawString(text, textX, textY); // It draws the text

        // It draws the player image
        Image image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/down1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // It draws the player image
        this.g2.setFont(arial_35); // It sets the font
        textX = gp.SCREEN_WIDTH / 2 - gp.TILE_SIZE; // It calculates the X coordinate of the text
        textY = gp.SCREEN_HEIGHT / 4;
        this.g2.drawImage(image, textX, textY, gp.TILE_SIZE+(gp.TILE_SIZE- gp.TILE_SIZE/2), gp.TILE_SIZE+(gp.TILE_SIZE- gp.TILE_SIZE/2), null);

        // It draws the text
        this.g2.setColor(Color.DARK_GRAY);
        text = "New Game"; // It sets the text
        textX = getXforCenteredText(text); // It calculates the X coordinate of the text
        textY = gp.SCREEN_HEIGHT / 2; // It calculates the Y coordinate of the text
        this.g2.drawString(text, textX+2, textY+2); // It draws the text
        this.g2.setColor(Color.WHITE);
        this.g2.drawString(text, textX, textY); // It draws the text
        if(commandNumber == 0) {
            this.g2.setColor(Color.WHITE);
            this.g2.drawString(">", textX - 38, textY); // It draws the text
        }

        this.g2.setColor(Color.DARK_GRAY);
        text = "Exit"; // It sets the text
        textX = getXforCenteredText(text); // It calculates the X coordinate of the text
        textY = gp.SCREEN_HEIGHT / 2 + 40; // It calculates the Y coordinate of the text
        this.g2.drawString(text, textX+2, textY+2); // It draws the text
        this.g2.setColor(Color.WHITE);
        this.g2.drawString(text, textX, textY); // It draws the text
        if(commandNumber == 1) {
            this.g2.setColor(Color.WHITE);
            this.g2.drawString(">", textX - 38, textY); // It draws the text
        }
    }

    public void setMessage(String message) { 
        this.message = message; // It sets the message
        this.messageOn = true; // It sets the messageOn to true
    }

    public void drawPlayerLife() {
        int heartX = gp.TILE_SIZE/6; // It sets the heart X coordinate
        int heartY = gp.TILE_SIZE/6; // It sets the heart Y coordinate
        int i = 0; // It sets the counter

        while(i < gp.player.maxLife) {
            this.g2.drawImage(this.heartImage, heartX, heartY, 32, 32, null); // It draws the heart image
            i++; // It increases the counter
            heartX += gp.TILE_SIZE/2; // It increases the heart X coordinate
        }
    }

    public int getXforCenteredText(String text) {
        int textWidth = this.g2.getFontMetrics().stringWidth(text); // It calculates the width of the text
        return (gp.SCREEN_WIDTH - textWidth) / 2; // It returns the X coordinate of the text
    }
}
