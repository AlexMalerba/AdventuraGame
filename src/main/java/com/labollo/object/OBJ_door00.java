package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_door00 extends SuperObject {

    public OBJ_door00() {
        super.name = "door00"; // It sets the object name
        super.collision = true; // It tells if the player can interact with the object

        status(0);
    }

    @Override
    public void status(int status) {
        if(status == 0) { // If the status is 0 it sets the object sprite to closed
            super.status = 0;
            try {
                image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door00.png")));
            } catch (IOException e) {
                System.err.println("Error loading image: " + name);
                e.printStackTrace();
            }
        } else if(status == 1) { // If the status is 1 it sets the object sprite to opened
            super.status = 1;
            try {
                image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/doorOpened00.png")));
            } catch (IOException e) {
                System.err.println("Error loading image: " + name);
                e.printStackTrace();
            }
        }
    }
}
