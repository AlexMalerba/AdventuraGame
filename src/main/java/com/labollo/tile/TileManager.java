package com.labollo.tile;

import com.labollo.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map1");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass00.png"))), false);
            tile[1] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass01.png"))), false);

            tile[2] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road00.png"))), false);
            tile[3] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road01.png"))), false);
            tile[4] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road02.png"))), false);
            tile[5] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road03.png"))), false);
            tile[6] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road04.png"))), false);
            tile[7] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road05.png"))), false);
            tile[8] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road06.png"))), false);
            tile[9] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road07.png"))), false);
            tile[10] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road08.png"))), false);
            tile[11] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road09.png"))), false);
            tile[12] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road10.png"))), false);
            tile[13] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road11.png"))), false);
            tile[14] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road12.png"))), false);

            tile[15] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png"))), true);
            tile[16] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png"))), true);
            tile[17] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png"))), false);

            tile[18] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water00.png"))), true);
            tile[19] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water01.png"))), true);
            tile[20] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water02.png"))), true);
            tile[21] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water03.png"))), true);
            tile[22] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water04.png"))), true);
            tile[23] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water05.png"))), true);
            tile[24] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water06.png"))), true);
            tile[25] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water07.png"))), true);
            tile[26] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water08.png"))), true);
            tile[27] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water09.png"))), true);
            tile[28] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water10.png"))), true);
            tile[29] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water11.png"))), true);
            tile[30] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water12.png"))), true);
            tile[31] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water13.png"))), true);

            tile[32] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/trunk.png"))), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;

            while (row < gp.maxWorldRow) {
                String line = br.readLine();

                if (line == null) {
                    break;  // Be sure to stop reading if the file is finished
                }

                String[] numbers = line.split(" ");

                for (int col = 0; col < gp.maxWorldCol && col < numbers.length; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                }

                row++;
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX  = worldCol * gp.tileSize;
            int worldY  = worldRow * gp.tileSize;
            int screenX  = worldX - gp.player.worldX + gp.player.screenX;
            int screenY  = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
