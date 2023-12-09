package com.labollo.object;

import com.labollo.main.GamePanel;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_potion00 extends SuperObject {

    GamePanel gp;

    public OBJ_potion00 (GamePanel gp) {
        super.name = "potion00"; // It sets the object name
        super.collision = true; // It tells if the player can interact with the object
        this.gp = gp; // It sets the GamePanel object
        status(0); // It sets the object status
    }

    @Override
    public void status(int status) {
        if(status == 0) {
            try {
                super.image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/potion00.png")));
                super.ut.scaleImage(super.image, super.OBJECT_DEFAULT_SIZE, super.OBJECT_DEFAULT_SIZE);
            } catch (IOException e) {
                System.err.println("Error loading image: " + name);
                e.printStackTrace();
            }
        }
    }
}
