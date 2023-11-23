package com.labollo.main;

import com.labollo.object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_casketClosed();
        gp.obj[0].worldX = 12 * gp.tileSize;
        gp.obj[0].worldY = 34 * gp.tileSize;

        gp.obj[1] = new OBJ_key();
        gp.obj[1].worldX = 21 * gp.tileSize;
        gp.obj[1].worldY = 11 * gp.tileSize;

        gp.obj[2] = new OBJ_key();
        gp.obj[2].worldX = 40 * gp.tileSize;
        gp.obj[2].worldY = 22 * gp.tileSize;

        gp.obj[3] = new OBJ_door();
        gp.obj[3].worldX = 12 * gp.tileSize;
        gp.obj[3].worldY = 37 * gp.tileSize;

        gp.obj[4] = new OBJ_heart();
        gp.obj[4].worldX = 10 * gp.tileSize;
        gp.obj[4].worldY = 11 * gp.tileSize;

        gp.obj[5] = new OBJ_casketOpened();
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 34 * gp.tileSize;

    }
}
