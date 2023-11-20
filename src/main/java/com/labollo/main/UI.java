package com.labollo.main;

import java.awt.*;

public class UI {
    GamePanel gp;
    Font arial_40;

    public UI(GamePanel gp) {
        this.gp = gp;
        this.arial_40 = new Font("Arial", Font.ITALIC, 40);

    }

    public void draw(Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawString("Key = " + gp.player.hasKey, 50, 50);
    }
}
