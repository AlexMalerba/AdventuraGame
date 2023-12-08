package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_casket00 extends SuperObject {

    public OBJ_casket00() {
        super.name = "casket00"; // It sets the object name
        super.collision = true; // It tells if the player can interact with the object

        this.status(0);
    }

    // This method is called when you need change the status
    @Override
    public void status(int status) {
        super.status = 0;
        if(status == 0) { // If the status is 0 it sets the object sprite to closed
            try {
                image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/casketClosed00.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(status == 1) { // If the status is 1 it sets the object sprite to opened
            super.status = 1;
            try {
                image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/casketOpened00.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
