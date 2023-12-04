package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_potion00 extends SuperObject {

    public OBJ_potion00 () {
        name = "potion00";
        try {
            image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/potion00.png")));
        } catch (IOException e) {
            System.err.println("Error loading image: " + name);
            e.printStackTrace();
        }
        collision = true;
    }
    @Override
    public void status(int status) {

    }
}
