package com.labollo.main;

import com.labollo.object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_casketClosed00();
        gp.obj[0].worldX = 64 * gp.tileSize;
        gp.obj[0].worldY = 16 * gp.tileSize;

        gp.obj[10] = new OBJ_casketClosed00();
        gp.obj[10].worldX = 63 * gp.tileSize;
        gp.obj[10].worldY = 16 * gp.tileSize;

        gp.obj[8] = new OBJ_casketClosed00();
        gp.obj[8].worldX = 62 * gp.tileSize;
        gp.obj[8].worldY = 16 * gp.tileSize;

        gp.obj[8] = new OBJ_key00();
        gp.obj[8].worldX = 62 * gp.tileSize;
        gp.obj[8].worldY = 14 * gp.tileSize;

        gp.obj[2] = new OBJ_key00();
        gp.obj[2].worldX = 41 * gp.tileSize;
        gp.obj[2].worldY = 49 * gp.tileSize;

        gp.obj[3] = new OBJ_door00();
        gp.obj[3].worldX = 63 * gp.tileSize;
        gp.obj[3].worldY = 19 * gp.tileSize;

        gp.obj[4] = new OBJ_heart00();
        gp.obj[5] = new OBJ_casketOpened00();

        gp.obj[6] = new OBJ_potion00();
        gp.obj[7] = new OBJ_potion01();
    }
}
