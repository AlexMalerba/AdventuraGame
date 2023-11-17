package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_key extends SuperObject {
    public OBJ_key() {
        name = "key00";
        try {
            image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key00.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
