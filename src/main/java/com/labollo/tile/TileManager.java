package com.labollo.tile;

import com.labollo.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map00.tmx");
    }

    public void getTileImage() {
        try {
            tile[15] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass00.png"))), false);
            tile[16] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass01.png"))), false);

            tile[17] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road00.png"))), false);
            tile[18] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road01.png"))), false);
            tile[19] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road02.png"))), false);
            tile[20] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road03.png"))), false);
            tile[21] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road04.png"))), false);
            tile[22] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road05.png"))), false);
            tile[23] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road06.png"))), false);
            tile[24] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road07.png"))), false);
            tile[25] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road08.png"))), false);
            tile[26] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road09.png"))), false);
            tile[27] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road10.png"))), false);
            tile[28] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road11.png"))), false);
            tile[29] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road12.png"))), false);

            tile[30] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png"))), true);
            tile[32] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png"))), true);
            tile[14] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png"))), false);

            tile[0] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water00.png"))), true);
            tile[1] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water01.png"))), true);
            tile[2] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water02.png"))), true);
            tile[3] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water03.png"))), true);
            tile[4] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water04.png"))), true);
            tile[5] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water05.png"))), true);
            tile[6] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water06.png"))), true);
            tile[7] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water07.png"))), true);
            tile[8] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water08.png"))), true);
            tile[9] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water09.png"))), true);
            tile[10] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water10.png"))), true);
            tile[11] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water11.png"))), true);
            tile[12] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water12.png"))), true);
            tile[13] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water13.png"))), true);

            tile[31] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/trunk.png"))), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            NodeList layerList = doc.getElementsByTagName("layer");
            Element layerElement = (Element) layerList.item(0); // Supponendo che ci sia solo un layer per semplicità

            Element dataElement = (Element) layerElement.getElementsByTagName("data").item(0);
            String data = dataElement.getTextContent().trim();
            String[] tileStrings = data.split(",");

            int row = 0;
            int col = 0;

            for (String tileString : tileStrings) {
                int tileNum = Integer.parseInt(tileString.trim());

                // Assicurati che la posizione corrente sia all'interno dei limiti dell'array
                if (row < gp.maxWorldRow && col < gp.maxWorldCol) {
                    mapTileNum[col][row] = tileNum - 1;  // Sottrai 1 per adattare gli indici dell'array
                }

                col++;

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void paint(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                // Verifica se il valore di tileNum è valido
                if (tileNum >= 0 && tileNum < tile.length && tile[tileNum] != null) {
                    g2.drawImage(tile[tileNum].getImage(), screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}
