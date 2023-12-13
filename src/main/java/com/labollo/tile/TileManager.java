package com.labollo.tile;

import com.labollo.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.labollo.main.UtilityTool;
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
        setup(0, "/tiles/water00.png", true);
        setup(1, "/tiles/water01.png", true);
        setup(2, "/tiles/water02.png", true);
        setup(3, "/tiles/water03.png", true);
        setup(4, "/tiles/water04.png", true);
        setup(5, "/tiles/water05.png", true);
        setup(6, "/tiles/water06.png", true);
        setup(7, "/tiles/water07.png", true);
        setup(8, "/tiles/water08.png", true);
        setup(9, "/tiles/water09.png", true);
        setup(10, "/tiles/water10.png", true);
        setup(11, "/tiles/water11.png", true);
        setup(12, "/tiles/water12.png", true);
        setup(13, "/tiles/water13.png", true);
        setup(14, "/tiles/earth.png", false);
        setup(15, "/tiles/grass00.png", false);
        setup(16, "/tiles/grass01.png", false);
        setup(17, "/tiles/road00.png", false);
        setup(18, "/tiles/road01.png", false);
        setup(19, "/tiles/road02.png", false);
        setup(20, "/tiles/road03.png", false);
        setup(21, "/tiles/road04.png", false);
        setup(22, "/tiles/road05.png", false);
        setup(23, "/tiles/road06.png", false);
        setup(24, "/tiles/road07.png", false);
        setup(25, "/tiles/road08.png", false);
        setup(26, "/tiles/road09.png", false);
        setup(27, "/tiles/road10.png", false);
        setup(28, "/tiles/road11.png", false);
        setup(29, "/tiles/road12.png", false);
        setup(31, "/tiles/trunk.png", true);
        setup(32, "/tiles/wall.png", true);
        setup(33, "/tiles/tree00.png", true);
        setup(34, "/tiles/tree01.png", true);
        setup(35, "/tiles/tree01/00.png", true);
        setup(36, "/tiles/tree01/01.png", true);
        setup(37, "/tiles/tree01/02.png", false);
        setup(38, "/tiles/tree01/03.png", false);
        setup(39, "/tiles/tree02/06.png", true);
        setup(40, "/tiles/tree02/07.png", true);
        setup(41, "/tiles/tree02/08.png", true);
        setup(42, "/tiles/tree02/09.png", true);
        setup(43, "/tiles/tree02/10.png", true);
        setup(44, "/tiles/tree02/11.png", true);
        setup(45, "/tiles/tree02/00.png", false);
        setup(46, "/tiles/tree02/01.png", true);
        setup(47, "/tiles/tree02/02.png", false);
        setup(48, "/tiles/tree02/03.png", true);
        setup(49, "/tiles/tree02/04.png", true);
        setup(50, "/tiles/tree02/05.png", true);
    }

    public void setup(int index, String filePath, boolean collision) {
        UtilityTool ut = new UtilityTool(); // It creates a new instance of UtilityTool

        try {
            // It loads the tile image and it scales it+
            tile[index] = new Tile(ut.scaleImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(filePath))), gp.TILE_SIZE, gp.TILE_SIZE), collision);
        } catch (Exception e) {
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
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
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
