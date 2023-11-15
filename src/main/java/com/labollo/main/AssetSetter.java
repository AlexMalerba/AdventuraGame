package com.labollo.main;

import com.labollo.object.OBJ_casket;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_casket();
        gp.obj[0].worldX = 25 * gp.tileSize;
        gp.obj[0].worldY = 11 * gp.tileSize;
    }
}
