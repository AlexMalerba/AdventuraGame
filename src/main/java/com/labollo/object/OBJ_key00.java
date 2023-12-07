package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_key00 extends SuperObject {

    public OBJ_key00() {
        super.name = "key00";
        super.solidArea.x = 16;
        super.solidArea.y = 16;
        super.solidArea.width = 16;
        super.solidArea.height = 16;
        try {
            image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key00.png")));
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
