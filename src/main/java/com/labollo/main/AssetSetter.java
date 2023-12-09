package com.labollo.main;

import com.labollo.object.*;

public class AssetSetter {
    // ---> Properties of com.labollo package
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // Creates class instances that extend SuperObject class (Each object is placed in a static array)
    public void setObject() {
        this.gp.obj[0] = new OBJ_casket00(gp); // Create the OBJ_casket00 object
        this.gp.obj[0].status(0); // Set the status to 0 (For the casket: closed sprite)
        this.gp.obj[0].worldX = 64 * gp.TILE_SIZE; // Set the x coordinate in the map
        this.gp.obj[0].worldY = 16 * gp.TILE_SIZE; // Set the y coordinate in the map

        this.gp.obj[1] = new OBJ_casket00(gp);
        this.gp.obj[1].status(0);
        this.gp.obj[1].worldX = 62 * gp.TILE_SIZE;
        this.gp.obj[1].worldY = 16 * gp.TILE_SIZE;

        this.gp.obj[2] = new OBJ_key00(gp);
        this.gp.obj[2].worldX = 41 * gp.TILE_SIZE;
        this.gp.obj[2].worldY = 49 * gp.TILE_SIZE;

        this.gp.obj[3] = new OBJ_door00(gp);
        this.gp.obj[3].status(0);
        this.gp.obj[3].worldX = 63 * gp.TILE_SIZE;
        this.gp.obj[3].worldY = 19 * gp.TILE_SIZE;

        this.gp.obj[4] = new OBJ_potion00(gp);
        this.gp.obj[5] = new OBJ_potion01(gp);

        this.gp.obj[6] = new OBJ_key00(gp);
        this.gp.obj[6].worldX = 62 * gp.TILE_SIZE;
        this.gp.obj[6].worldY = 14 * gp.TILE_SIZE;

        this.gp.obj[7] = new OBJ_casket00(gp);
        this.gp.obj[7].status(0);
        this.gp.obj[7].worldX = 63 * gp.TILE_SIZE;
        this.gp.obj[7].worldY = 16 * gp.TILE_SIZE;
    }
}
