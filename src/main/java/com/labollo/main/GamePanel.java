package com.labollo.main;

import com.labollo.entity.Player;
import com.labollo.object.SuperObject;
import com.labollo.tile.TileManager;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class GamePanel extends JPanel implements Runnable {

    // Properties of this class
    // Screen settings
    public final int originalTileSize = 16; // 16x16 tile
    public final int scale = 3; // to resize the tiles
    public final int tileSize = originalTileSize * scale; // 48x48 tile

    public final int maxScreenCol = 16; // Max number of columns
    public final int maxScreenRow = 12; // Max number of rows
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // World settings
    public final int maxWorldCol = 80; // Columns number of the map: map02.tmx
    public final int maxWorldRow = 80; // Rows number of the map: map02.tmx
    public final int worldWidth = tileSize * maxWorldCol; // Width expressed in pixels (3840px)
    public final int worldHeight = tileSize * maxWorldRow; // Width expressed in pixels (3840px)

    // FPS
    public int FPS = 60; // The startGameThread method will be called 60 times per second

    // Properties of JDK
    public Thread gameThread; // To create an object of type Thread

    // Properties of com.labollo package
    public KeyHandler keyH = new KeyHandler();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    public TileManager tileM = new TileManager(this);
    public SuperObject[] obj = new SuperObject[100];
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    // GamePanel constructor
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // To set your preferred panel size
        this.setDoubleBuffered(true); // To improve the graphics experience
        this.addKeyListener(keyH); // To hear which key is pressed
        this.setFocusable(true); // To enable the ability to receive input
    }

    public void setupGame() {
        this.aSetter.setObject();
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
        this.requestFocusInWindow(); // To set focus on game window
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / this.FPS; // The amount of time in nanoseconds between each frame
        double delta = 0; // The amount of time until the next frame
        long lastTime = System.nanoTime(); // The time since the game started
        long currentTime; // The current time

        // While the game is running
        while (this.gameThread != null) {
            currentTime = System.nanoTime(); // Get the current time in nanoseconds
            delta += (currentTime - lastTime) / drawInterval; // Add the time since the last frame to the delta
            lastTime = currentTime; // Update the last time

            if (delta >= 1) { // If it's time to draw a new frame

                // Calls the update and repaint method 60 times of second

                /* Calls the update method declared in GamePanel
                   witch it calls the update method on the player object */
                this.update(); // Update the player movement animation

                /* Calls the repaint method declared in JComponent (Superclass of JPanel)
                   witch calls the paintComponent method declared in GamePanel */
                repaint(); // Requires the repaint of the component (JComponent) witch in a turn activate the paintComponent method

                delta--;
            }
        }
    }

    public void update() {
        this.player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // Converts g from Graphics class to Graphics2D class To have a better check of the object

        tileM.paint(g2);

        for (int i = 0; i < obj.length; i++) {
            if (this.obj[i] != null) {
                this.obj[i].draw(g2, this);
            }
        }

        this.player.paint(g2);
        this.ui.draw(g2);
        g2.dispose();
    }
}