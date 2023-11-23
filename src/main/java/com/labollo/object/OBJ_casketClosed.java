package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_casketClosed extends SuperObject {

    public OBJ_casketClosed() {
        name = "casketClosed00";
        try {
            image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/casketClosed00.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
