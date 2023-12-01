package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_casketClosed00 extends SuperObject {

    public OBJ_casketClosed00() {
        name = "casketClosed00";
        try {
            image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/casketClosed00.png")));
        } catch (IOException e) {
            System.err.println("Error loading image: " + name);
            e.printStackTrace();
        }
        super.collision = true;
    }
}
