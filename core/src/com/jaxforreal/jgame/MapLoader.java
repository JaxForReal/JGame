package com.jaxforreal.jgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.jaxforreal.jgame.MapObjects.TileMapObject;
import com.jaxforreal.jgame.MapObjects.Zombie;
import com.jaxforreal.jgame.Tiles.Grass;
import com.jaxforreal.jgame.Tiles.Tile;
import com.jaxforreal.jgame.Tiles.Wood;

import java.io.IOException;
import java.util.HashMap;

/**
 * Static class that loads TileMap's from plaintext files
 * Also holds information about converting from strings (in plaintext file) to tiles
 */
public class MapLoader {
    private HashMap<String, Tile> tileIds;
    private HashMap<String, TileMapObject> objectIds;

    GameManager gameManager;
    XmlReader xmlReader = new XmlReader();
    /**
     * @param gameManager the game's GameManager.
     *                    all tiles and tilemaps that are loaded with this MapLoader will be passed this value;
     */
    public MapLoader(GameManager gameManager) {
        this.gameManager = gameManager;

        //these are all the mappings for string -> tileType when reading map txt files
        tileIds = new HashMap<String, Tile>();
        tileIds.put(".", new Grass(gameManager));
        tileIds.put("w", new Wood(gameManager));

        //all the mappings for loading objects into map from xml
        objectIds = new HashMap<String, TileMapObject>();
        //pass null for tilemap because these objects only serve as a template for cloning
        objectIds.put("zombie", new Zombie(gameManager));
    }

    /**
     * map must be rectangular
     *
     * tile data format:
     * linebreaks separate rows; spaces separate columns
     * each cell in the textfile tilemap must correspond
     * to a key in the tilIds map so it can be converted to a Tile object
     *
     * object data format:
     * <objects>
     *     <obj type="type to look up in objectIds map" x="0" y="0" />
     *     <obj type="another" x="2" y="5" />
     * </objects>
     */
    public TileMap loadFromFile(String tileDataPath, String objectDataPath) {
        TileMap newMap = loadTiles(tileDataPath);
        loadMapObjects(newMap, objectDataPath);
        return newMap;
    }

    /**
     * load all static tiles from tileDataPath to a new map, returns that map
     * uses TileMap.setTileAt(...)
     */
    private TileMap loadTiles(String tileDataPath) {
        String[] mapLines = Gdx.files.internal(tileDataPath).readString().split("\n");

        int width = mapLines[0].split(" ").length;
        int height = mapLines.length;

        TileMap newMap = new TileMap(width, height);

        for (int yIter = 0; yIter < height; yIter++) {
            String[] tileStrings = mapLines[yIter].split(" ");

            for (int xIter = 0; xIter < width; xIter++) {
                String tileString = tileStrings[xIter];
                //get a new tile by cloning it from the tile database
                //TODO consider moving this database to seperate file if it become unwieldy
                Tile newTile = tileIds.get(tileString).clone();

                //"newMap.getHeightInTiles() - yIter" because of y-up rendering, but y-down text
                //"-1" because off by one errors with getHeightInTiles() vs index
                newMap.setTileAt(xIter, newMap.getHeightInTiles() - yIter - 1, newTile);
            }

        }
        return newMap;
    }

    /**
     * load all tileMapObjects on map
     * uses TileMap.addMapObject(...)
     */
    private void loadMapObjects(TileMap map, String objectDataPath) {
        try {
            XmlReader.Element objectXmlData = xmlReader.parse(Gdx.files.internal(objectDataPath));

            for(XmlReader.Element childObjectXml : objectXmlData.getChildrenByName("obj")) {
                //get new object by cloning it from the string->TileMapObject map
                TileMapObject newMapObject = objectIds.get(childObjectXml.get("type")).clone();
                newMapObject.setTileX(childObjectXml.getInt("x"));
                newMapObject.setTileY(childObjectXml.getInt("y"));
                map.addMapObject(newMapObject);
            }

        } catch (IOException e) {
            Gdx.app.error("load", "Error while reading xml of TileMap", e);
            e.printStackTrace();
        }
    }

}