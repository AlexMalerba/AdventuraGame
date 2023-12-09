package com.labollo.object;

import com.labollo.main.GamePanel;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_casket00 extends SuperObject {

    GamePanel gp;

    public OBJ_casket00(GamePanel gp) {
        super.name = "casket00"; // It sets the object name
        super.collision = true; // It tells if the player can interact with the object
        this.gp = gp; // It sets the GamePanel object
        this.status(0); // It sets the object status
    }

    // This method is called when you need change the status
    @Override
    public void status(int status) {
        super.status = 0;
        if(status == 0) { // If the status is 0 it sets the object sprite to closed
            try {
                super.image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/casketClosed00.png")));
                super.ut.scaleImage(super.image, super.OBJECT_DEFAULT_SIZE, super.OBJECT_DEFAULT_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(status == 1) { // If the status is 1 it sets the object sprite to opened
            super.status = 1;
            try {
                super.image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/casketOpened00.png")));
                super.ut.scaleImage(super.image, super.OBJECT_DEFAULT_SIZE, super.OBJECT_DEFAULT_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
