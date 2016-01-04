package com.jaxforreal.jgame;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.jaxforreal.jgame.MapObjects.TileMapObject;
import com.jaxforreal.jgame.Tiles.Tile;

public class TileMap {
    //the width and height of each tile
    public final int tileSize = 100;
    //NOTE: tiles are stored in a Tile[x][y] format.
    private Tile[][] tiles;
    private Array<TileMapObject> tileMapObjects;

    public TileMap(int width, int height) {
        //initialize tile array
        tiles = new Tile[width][height];
        tileMapObjects = new Array<TileMapObject>(false, 10, TileMapObject.class);
    }

    /** Used in MapLoader to init tiles */
    void setTileAt(int x, int y, Tile tile) {
        tiles[x][y] = tile;
    }

    /** Used in MapLoader to init TileMapObjects */
    void addMapObject(TileMapObject tileMapObject) {
        tileMapObjects.add(tileMapObject);
    }

    public void update(float delta) {
        for(TileMapObject object : tileMapObjects) {
            object.update(delta);
        }
    }

    public void render(SpriteBatch spriteBatch, float mapX, float mapY) {
        //render static tiles
        for (int iterX = 0; iterX < tiles.length; iterX++) {
            for (int iterY = 0; iterY < tiles[0].length; iterY++) {
                Tile tile = getTileAt(iterX, iterY);
                if (tile != null) {
                    spriteBatch.draw(tile.getTexture(),
                            mapX + (iterX * tileSize),
                            mapY + (iterY * tileSize),
                            tileSize,
                            tileSize);
                }
            }
        }

        //render mapObjects
        for(TileMapObject object : tileMapObjects) {
            object.render(spriteBatch, mapX + (object.getTileX() * tileSize), mapY + (object.getTileY() * tileSize));
        }
    }

    public int getWidthInTiles() {
        return tiles.length;
    }

    public int getHeightInTiles() {
        return tiles[0].length;
    }

    public Tile getTileAt(int x, int y) {
        return tiles[x][y];
    }
}
