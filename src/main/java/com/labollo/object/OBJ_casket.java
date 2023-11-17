package com.labollo.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_casket extends SuperObject {

    public OBJ_casket() {
        name = "casket00";
        try {
            image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/casket00.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
