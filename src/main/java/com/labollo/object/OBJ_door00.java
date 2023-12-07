package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_door00 extends SuperObject {
    public OBJ_door00() {
        name = "door00";
        try {
            image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door00.png")));
        } catch (IOException e) {
            System.err.println("Error loading image: " + name);
            e.printStackTrace();
        }
        collision = true;
    }
    @Override
    public void status(int status) {
        if(status == 0) {
            super.status = 0;
            try {
                image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door00.png")));
            } catch (IOException e) {
                System.err.println("Error loading image: " + name);
                e.printStackTrace();
            }
        } else if(status == 1) {
            super.status = 1;
            try {
                image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/doorOpened00.png")));
            } catch (IOException e) {
                System.err.println("Error loading image: " + name);
                e.printStackTrace();
            }
        }
    }
}
