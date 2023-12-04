package com.labollo.object;

import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class OBJ_casket00 extends SuperObject {

    public OBJ_casket00() {
        super.name = "casket00";
        super.status = 0;
        super.collision = true;

        try {
            image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/casketClosed00.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void status(int status) {
        if(status == 0) {
            try {
                image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/casketClosed00.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(status == 1) {
            try {
                image = read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/casketOpened00.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
