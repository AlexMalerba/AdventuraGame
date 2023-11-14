package com.labollo.main;

import com.labollo.entity.Entity;

import java.util.Objects;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x; //11*48 + 8
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width; //11*48 + 8 + 32
        int entityTopWorldY = entity.worldY + entity.solidArea.y; //11*48 + 16
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height; //11*48 + 16 + 32

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1 = 0, tileNum2 = 0;

        if(Objects.equals(entity.direction, "up") || entity.direction == "down" || entity.direction == "right" || entity.direction == "left"){
            if(entity.direction.equals("up")){
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            }
            else if(entity.direction.equals("down")){
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            }
            if(entity.direction.equals("left")) {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            }
            else if(entity.direction.equals("right")){
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            }
        }else{
                tileNum1 = -1;
                tileNum2 = -1;
            }

        if ((tileNum1 != -1 && gp.tileM.tile[tileNum1].collision) || (tileNum2 != -1 && gp.tileM.tile[tileNum2].collision)) {
            entity.collisionOn = true;
        } else {
            entity.collisionOn = false;
        }
    }
}
