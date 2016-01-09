package com.jaxforreal.jgame;


import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.jaxforreal.jgame.entity.Entity;
import com.jaxforreal.jgame.tile.Tile;

public class Map extends Group {
    /**
     * the width and height of each tile
     */
    public final int tileSize = 60;

    /**
     * NOTE: tiles are stored in a Tile[x][y] format.
     */
    private Tile[][] tiles;

    public Map(int width, int height) {
        //initialize tile array
        tiles = new Tile[width][height];
    }

    /**
     * Adds a tile to the location, and plays popup animation on tile
     */
    void setTileAt(int x, int y, Tile tile) {
        tiles[x][y] = tile;
        addActor(tile);
        tile.setBounds(x * tileSize, y * tileSize, tileSize, tileSize);
        //add tile zoom up/in animation
        tile.addAction(
                Actions.sequence(
                        //make tiny
                        Actions.moveBy(tileSize / 2, -tileSize), Actions.scaleTo(0f, 0f),
                        Actions.parallel(
                                //zoom up
                                Actions.moveBy(-tileSize / 2, tileSize, 0.5f, Interpolation.circle),
                                Actions.scaleTo(1, 1, 0.5f, Interpolation.circle)
                        )
                )
        );
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

    /**
     * Assumes Map is rectangular
     */
    public int getHeightInTiles() {
        return tiles[0].length;
    }

    public Tile getTileAt(int x, int y) {
        return tiles[x][y];
    }
}
