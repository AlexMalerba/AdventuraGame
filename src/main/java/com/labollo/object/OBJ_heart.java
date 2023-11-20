package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_heart extends SuperObject{

    public OBJ_heart () {
        name = "heart00";
        try {
            image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart00.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
