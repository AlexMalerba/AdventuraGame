package com.labollo.object;

import com.labollo.main.GamePanel;

import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_heart extends SuperObject {

    GamePanel gp;

    public OBJ_heart(GamePanel gp) {
        super.name = "heart"; // It sets the object name
        super.collision = true; // It tells if the player can interact with the object
        this.gp = gp; // It sets the GamePanel object
        status(0); // It sets the object status
    }

    // This method is called when you need change the status
    @Override
    public void status(int status) {
        super.status = 0;
        if(status == 0) {
            try {
                super.image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heartFull.png")));
                super.image = super.ut.scaleImage(super.image, super.OBJECT_DEFAULT_SIZE, super.OBJECT_DEFAULT_SIZE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(status == 1) {
            super.status = 1;
            try {
                super.image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heartHalf.png")));
                super.image = super.ut.scaleImage(super.image, super.OBJECT_DEFAULT_SIZE, super.OBJECT_DEFAULT_SIZE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(status == 2) {
            super.status = 2;
            try {
                super.image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heartBlank.png")));
                super.image = super.ut.scaleImage(super.image, super.OBJECT_DEFAULT_SIZE, super.OBJECT_DEFAULT_SIZE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}