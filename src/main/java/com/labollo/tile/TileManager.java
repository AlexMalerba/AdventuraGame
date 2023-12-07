package com.labollo.tile;

import com.labollo.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
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
        mapTileNum = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];

        getTileImage();
        loadMap("/maps/map02.tmx");
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

            tile[33] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree00.png"))), true);
            tile[34] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree01.png"))), true);
            tile[32] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png"))), true);
            tile[14] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png"))), false);

            tile[35] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree01/00.png"))), true);
            tile[36] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree01/01.png"))), true);
            tile[37] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree01/02.png"))), false);
            tile[38] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree01/03.png"))), false);

            tile[45] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree02/00.png"))), false);
            tile[46] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree02/01.png"))), true);
            tile[47] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree02/02.png"))), false);
            tile[48] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree02/03.png"))), true);
            tile[49] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree02/04.png"))), true);
            tile[50] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree02/05.png"))), true);
            tile[39] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree02/06.png"))), true);
            tile[40] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree02/07.png"))), true);
            tile[41] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree02/08.png"))), true);
            tile[42] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree02/09.png"))), true);
            tile[43] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree02/10.png"))), true);
            tile[44] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree02/11.png"))), true);

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
                if (row < gp.MAX_WORLD_ROW && col < gp.MAX_WORLD_COL) {
                    mapTileNum[col][row] = tileNum - 1;  // Sottrai 1 per adattare gli indici dell'array
                }

                col++;

                if (col == gp.MAX_WORLD_COL) {
                    col = 0;
                    row++;
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.MAX_WORLD_COL && worldRow < gp.MAX_WORLD_ROW) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.TILE_SIZE;
            int worldY = worldRow * gp.TILE_SIZE;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {

                // Verifica se il valore di tileNum è valido
                if (tileNum >= 0 && tileNum < tile.length && tile[tileNum] != null) {
                    g2.drawImage(tile[tileNum].getImage(), screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
                }
            }

            worldCol++;

            if (worldCol == gp.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}
