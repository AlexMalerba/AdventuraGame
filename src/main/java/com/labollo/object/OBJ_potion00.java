package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_potion00 extends SuperObject {

    public OBJ_potion00 () {
        super.name = "potion00"; // It sets the object name
        super.collision = true; // It tells if the player can interact with the object

        status(0);
    }

    @Override
    public void status(int status) {
        if(status == 0) {
            try {
                image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/potion00.png")));
            } catch (IOException e) {
                System.err.println("Error loading image: " + name);
                e.printStackTrace();
            }
        }
    }
}
