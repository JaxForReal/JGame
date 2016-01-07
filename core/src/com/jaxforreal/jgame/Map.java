package com.jaxforreal.jgame;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
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
    }

    /**
     * Used in MapLoader to init TileMapObjects
     * automagically sets MapObject's parentMap
     */
    void addMapObject(Entity entity) {
        addActor(entity);
        entity.setParentMap(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for (Tile[] column : tiles) {
            for (Tile tile : column) {
                tile.update(delta);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //render static tiles
        for (int iterX = 0; iterX < tiles.length; iterX++) {
            for (int iterY = 0; iterY < tiles[0].length; iterY++) {
                Tile tile = getTileAt(iterX, iterY);
                if (tile != null) {
                    tile.render(batch, getX() + (iterX * tileSize), getY() + (iterY * tileSize), tileSize, tileSize);
                }
            }
        }

        super.draw(batch, parentAlpha);
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
