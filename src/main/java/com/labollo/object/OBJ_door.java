package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_door extends SuperObject {
    public OBJ_door() {
        name = "door00";
        try {
            image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door00.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
