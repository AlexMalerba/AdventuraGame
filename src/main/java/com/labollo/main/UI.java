package com.labollo.main;

import com.labollo.object.OBJ_key00;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UI {

    // Properties of com.labollo package
    OBJ_key00 key;
    GamePanel gp;

    // Properties of JDK
    Font arial_40;
    BufferedImage keyImage;

    public UI(GamePanel gp) {
        this.gp = gp;
        this.arial_40 = new Font("algerian", Font.ITALIC, 40);
        this.key = new OBJ_key00();
        this.keyImage = key.image;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage, gp.TILE_SIZE /2, gp.TILE_SIZE /2-9, gp.TILE_SIZE, gp.TILE_SIZE, null);
        g2.drawString("x " + gp.player.hasKey, 74, 50);
    }
}
