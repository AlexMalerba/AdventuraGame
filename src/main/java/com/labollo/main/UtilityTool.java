package com.labollo.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

    // This method is called when you need to draw the object
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType()); // It creates a new BufferedImage object
        Graphics2D g2 = scaledImage.createGraphics(); // It creates a new Graphics2D object
        g2.drawImage(original, 0, 0, width, height, null); // It draws the image
        g2.dispose(); // It disposes the Graphics2D object

        return scaledImage; // It returns the scaled image
    }
}
