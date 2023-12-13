package com.labollo.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public GamePanel gp; // It contains the GamePanel object

    public KeyHandler(GamePanel gp) {
        this.gp = gp; // It sets the GamePanel object
    }

    // It tells if the player is pressing the keys
    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, cPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // It gets the key code of the pressed key

        // It sets the pressed key to true
        if(gp.gameState == gp.TITLESTATE) { // Title state
            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNumber--;
                if (gp.ui.commandNumber < 0)
                    gp.ui.commandNumber = 1;
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNumber++;
                if (gp.ui.commandNumber > 1)
                    gp.ui.commandNumber = 0;
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.gameState == gp.TITLESTATE) {
                    if (gp.ui.commandNumber == 0) {
                        gp.gameState = gp.PLAYSTATE;
                    } else if (gp.ui.commandNumber == 1) {
                        System.exit(0);
                    }
                }
            }
        } else if(gp.gameState == gp.PLAYSTATE) { // Game state
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
                upPressed = true;
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
                downPressed = true;
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
                leftPressed = true;
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
                rightPressed = true;

            if (code == KeyEvent.VK_SHIFT)
                shiftPressed = true;
            if (code == KeyEvent.VK_C)
                cPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); // It gets the key code of the released key

        // It sets the released key to false because when the key is released the player isn't pressing it
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            upPressed = false;
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            downPressed = false;
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            leftPressed = false;
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            rightPressed = false;

        if(code == KeyEvent.VK_SHIFT)
            shiftPressed = false;
        if(code == KeyEvent.VK_C)
            cPressed = false;
    }
}
