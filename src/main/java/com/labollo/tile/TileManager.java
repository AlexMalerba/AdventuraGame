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

    // ---> Properties of com.labollo package
    GamePanel gp;
    public Tile[] tile;

    // ---> Properties of this class
    public int[][] mapTileNum; // It stores the tile number of the map (80x80 tiles)

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[100]; // 100 is the maximum number of tiles
        mapTileNum = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];

        getTileImage(); // It loads the tile images
        loadMap("/maps/map02.tmx"); // It loads the map
    }

    public void getTileImage() {

        // Load the tile images
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

        // Load the map from a file
        try {
            InputStream is = getClass().getResourceAsStream(filePath); // It loads the map file with the path filePath

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); // It creates a new instance of DocumentBuilderFactory
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); // It creates a new instance of DocumentBuilder
            Document doc = dBuilder.parse(is); // It parses the map file

            NodeList layerList = doc.getElementsByTagName("layer"); // It gets the layer list from the map file
            Element layerElement = (Element) layerList.item(0); // It gets the first layer from the layer list

            Element dataElement = (Element) layerElement.getElementsByTagName("data").item(0); // It gets the data element from the layer
            String data = dataElement.getTextContent().trim(); // It gets the data from the data element
            String[] tileStrings = data.split(","); // It splits the data into an array of strings

            int row = 0; // It stores the current row
            int col = 0; // It stores the current column

            for (String tileString : tileStrings) { // It loops through the tileStrings array
                int tileNum = Integer.parseInt(tileString.trim()); // It converts the tileString to an integer

                // Check if the tileNum is valid
                if (row < gp.MAX_WORLD_ROW && col < gp.MAX_WORLD_COL) {
                    mapTileNum[col][row] = tileNum - 1;  // It stores the tileNum in the mapTileNum array
                }

                col++; // It increments the column

                // Check if the column is equal to the maximum number of columns
                if (col == gp.MAX_WORLD_COL) {
                    col = 0; // It resets the column
                    row++; // It increments the row
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // It draws the map
    public void draw(Graphics2D g2) {
        int worldCol = 0; // It stores the current world column
        int worldRow = 0; // It stores the current world row

        // It loops through the mapTileNum array
        while (worldCol < gp.MAX_WORLD_COL && worldRow < gp.MAX_WORLD_ROW) {
            int tileNum = mapTileNum[worldCol][worldRow]; // It gets the tile number from the mapTileNum array

            int worldX = worldCol * gp.TILE_SIZE; // It calculates the world X coordinate
            int worldY = worldRow * gp.TILE_SIZE; // It calculates the world Y coordinate
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // It calculates the screen X coordinate
            int screenY = worldY - gp.player.worldY + gp.player.screenY; // It calculates the screen Y coordinate

            // Check if the tile is in the screen
            if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {

                // Check if the tileNum is valid and if the tile isn't null
                if (tileNum >= 0 && tileNum < tile.length && tile[tileNum] != null) {
                    // It draws the tile
                    g2.drawImage(tile[tileNum].getImage(), screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
                }
            }

            worldCol++; // It increments the world column

            // Check if the world column is equal to the maximum number of columns
            if (worldCol == gp.MAX_WORLD_COL) {
                worldCol = 0; // It resets the world column
                worldRow++; // It increments the world row
            }
        }
    }

}
