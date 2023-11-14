package com.labollo.main;

import com.labollo.entity.Player;
import com.labollo.tile.TileManager;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // to resize the tiles
    public final int tileSize = originalTileSize * scale; // 48x48 tile

    public final int maxScreenCol = 16; // Max number of columns
    public final int maxScreenRow = 12; // Max number of rows
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    Thread  gameThread; // To create an object of type Thread
    KeyHandler keyH = new KeyHandler(); // To create an object of type KeyHandler
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    TileManager tileM = new TileManager(this);

    // World settings
    public final int maxWorldCol = 51;
    public final int maxWorldRow = 51;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    // GamePanel costructor
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // To set your preferred panel size
        this.setBackground(Color.black); // To set the background to black
        this.setDoubleBuffered(true); // To improve the graphics experience
        this.addKeyListener(keyH); // To hear which key is pressed
        this.setFocusable(true); // To enable the ability to receive input
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // The amount of time in nanoseconds between each frame
        double delta = 0; // The amount of time until the next frame
        long lastTime = System.nanoTime(); // The time since the game started
        long currentTime; // The current time
        long timer = 0; // The amount of time since the last time the FPS was printed
        int drawCount = 0; // The number of frames drawn since the last time the FPS was printed

        while (gameThread != null) { // While the game is running

            currentTime = System.nanoTime(); // Get the current time

            // Add the time since the last frame to the delta
            delta += (currentTime - lastTime) / drawInterval;

            timer += (currentTime - lastTime); // Add the time since the last frame to the timer
            lastTime = currentTime; // Update the last time

            if (delta >= 1) { // If it's time to draw a new frame

                update();

                repaint();

                delta--;

                drawCount++;
            }

            if (timer >= 1000000000) { // If it's been a second since the last time the FPS was printed

                System.out.println("FPS: " + drawCount);

                drawCount = 0;

                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileM.paint(g2);
        player.paint(g2);

        g2.dispose();
    }
}
