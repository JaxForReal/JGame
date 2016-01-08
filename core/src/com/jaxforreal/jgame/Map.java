package com.jaxforreal.jgame;


import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.jaxforreal.jgame.entity.Entity;
import com.jaxforreal.jgame.tile.Tile;

public class Map extends Group {
    /**
     * the width and height of each tile
     */
    public final int tileSize = 100;

    /**
     * NOTE: tiles are stored in a Tile[x][y] format.
     */
    private Tile[][] tiles;

    public Map(int width, int height) {
        //initialize tile array
        tiles = new Tile[width][height];
    }

    /**
     * Used in MapLoader to init tiles
     */
    void setTileAt(int x, int y, Tile tile) {
        tiles[x][y] = tile;
        tile.addAction(Actions.moveTo(x * tileSize, y * tileSize));
        tile.setBounds(x * tileSize, y * tileSize, tileSize, tileSize);
        addActor(tile);
    }

    /**
     * Used in MapLoader to init TileMapObjects
     * automagically sets MapObject's parentMap
     * <p/>
     * Assumes Entity's tile position is set with Entity.setTilePosition
     */
    void addEntityAt(int x, int y, Entity entity) {
        entity.setParentMap(this);
        entity.setTilePosition(x, y);
        addActor(entity);
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
