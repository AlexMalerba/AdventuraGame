package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_casketOpened00 extends SuperObject {
    public OBJ_casketOpened00() {
        name = "casketOpened00";
        try {
            image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/casketOpened00.png")));
        } catch (IOException e) {
            System.err.println("Error loading image: " + name);
            e.printStackTrace();
        }
        collision = true;
    }
}
