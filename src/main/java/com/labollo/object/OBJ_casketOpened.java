package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_casketOpened extends SuperObject {
    public OBJ_casketOpened() {
        name = "casketOpened00";
        try {
            image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/casketOpen00.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
