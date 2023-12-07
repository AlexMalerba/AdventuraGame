package com.labollo.main;

import com.labollo.entity.Entity;

import java.util.Objects;

public class CollisionChecker {
    // Properties of com.labollo package
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    // Checks if an entity collides with a tile that has the collisionOn variable set to "true"
    public void checkTile(Entity entity) {

        // Calculate the rectangle collision coordinates (The calculations are an example. They are made assuming that the player moves from the starting positions)
        int entityLeftWorldX = entity.worldX + entity.solidArea.x; // 3024 + 25 = 3049
        int entityRightWorldX = entityLeftWorldX + entity.solidArea.width; // 3049 + 8 = 3057
        int entityTopWorldY = entity.worldY + entity.solidArea.y; // 1104 + 40 = 1144
        int entityBottomWorldY = entityTopWorldY + entity.solidArea.height; //1144 + 12 = 1156

        int entityLeftCol = entityLeftWorldX / gp.TILE_SIZE; // 3049 / 48 = 63
        int entityRightCol = entityRightWorldX / gp.TILE_SIZE; // 3057 / 48 = 63
        int entityTopRow = entityTopWorldY / gp.TILE_SIZE; // 1144 / 48 = 23
        int entityBottomRow = entityBottomWorldY / gp.TILE_SIZE; // 1156 / 48 = 24

        int tileNum1 = 0, tileNum2 = 0;

        // If you're moving
        if(Objects.equals(entity.direction, "up") || Objects.equals(entity.direction, "down") || Objects.equals(entity.direction, "right") || Objects.equals(entity.direction, "left")){

            // Check the direction you are going
            if(entity.direction.equals("up")){
                entityTopRow = (entityTopWorldY - entity.speed) / gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            }
            else if(entity.direction.equals("down")){
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            }
            if(entity.direction.equals("left")) {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            }
            else if(entity.direction.equals("right")){
                entityRightCol = (entityRightWorldX + entity.speed) / gp.TILE_SIZE;
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

    public int checkObject(Entity entity, boolean player) {
        // Make index 999 because when I call the pickUpObject method (Player class) I first check if index is different from 999
        // If it's different the player has collided with an object because in this method the initial value is modified
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {

                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.collisionOn = true;
                                System.out.println("Up Collision");
                            }
                            // Check if it's the player who has called the method
                            if(player) {
                                index = i;
                            }
                        }
                    } case "down" -> {
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.collisionOn = true;
                                System.out.println("Down Collision");
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    } case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.collisionOn = true;
                                System.out.println("Left Collision");
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    } case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.collisionOn = true;
                                System.out.println("Right Collision");
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                }

                // Reset the initial values to make sure you haven't changed anything
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
