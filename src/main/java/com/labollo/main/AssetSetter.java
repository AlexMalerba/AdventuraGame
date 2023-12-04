package com.labollo.main;

import com.labollo.object.*;

public class AssetSetter {
    // Properties of com.labollo package
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        this.gp.obj[0] = new OBJ_casket00();
        this.gp.obj[0].worldX = 64 * gp.tileSize;
        this.gp.obj[0].worldY = 16 * gp.tileSize;

        this.gp.obj[10] = new OBJ_casket00();
        this.gp.obj[10].worldX = 63 * gp.tileSize;
        this.gp.obj[10].worldY = 16 * gp.tileSize;

        this.gp.obj[8] = new OBJ_casket00();
        this.gp.obj[8].worldX = 62 * gp.tileSize;
        this.gp.obj[8].worldY = 16 * gp.tileSize;

        this.gp.obj[8] = new OBJ_key00();
        this.gp.obj[8].worldX = 62 * gp.tileSize;
        this.gp.obj[8].worldY = 14 * gp.tileSize;

        this.gp.obj[2] = new OBJ_key00();
        this.gp.obj[2].worldX = 41 * gp.tileSize;
        this.gp.obj[2].worldY = 49 * gp.tileSize;

        this.gp.obj[3] = new OBJ_door00();
        this.gp.obj[3].worldX = 63 * gp.tileSize;
        this.gp.obj[3].worldY = 19 * gp.tileSize;

        this.gp.obj[4] = new OBJ_heart00();

        this.gp.obj[6] = new OBJ_potion00();
        this.gp.obj[7] = new OBJ_potion01();
    }
}
