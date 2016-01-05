package com.jaxforreal.jgame;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jaxforreal.jgame.entity.Entity;
import com.jaxforreal.jgame.tile.Tile;

public class Map {
    /**
     * the width and height of each tile
     */
    public final int tileSize = 100;

    /**
     * NOTE: tiles are stored in a Tile[x][y] format.
     */
    private Tile[][] tiles;
    private Array<Entity> entities;

    /**
     * temporary object to cut down on GC
     * used to calculate animations/locations
     */
    private Vector2 entityPositionUtil;

    public Map(int width, int height) {
        //initialize tile array
        tiles = new Tile[width][height];
        entities = new Array<Entity>(false, 10, Entity.class);
        entityPositionUtil = new Vector2();
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
        entities.add(entity);
        entity.setParentMap(this);
    }

    public void update(float delta) {
        for (Entity object : entities) {
            object.update(delta);
        }

        for (Tile[] column : tiles) {
            for (Tile tile : column) {
                tile.update(delta);
            }
        }
    }

    public void render(SpriteBatch spriteBatch, float mapX, float mapY) {
        //render static tiles
        for (int iterX = 0; iterX < tiles.length; iterX++) {
            for (int iterY = 0; iterY < tiles[0].length; iterY++) {
                Tile tile = getTileAt(iterX, iterY);
                if (tile != null) {
                    tile.render(spriteBatch, mapX + (iterX * tileSize), mapY + (iterY * tileSize), tileSize, tileSize);
                }
            }
        }

        //render mapObjects
        for (Entity entity : entities) {
            entityPositionUtil = getLerpedPosition(entity);
            entity.render(spriteBatch,
                    mapX + (entityPositionUtil.x * tileSize),
                    mapY + (entityPositionUtil.y * tileSize));
        }
    }

    //Note: this does not take into account x and y that map is rendered at...

    /**
     * converts tile coordinates to World coordinates
     * assumes map is rendered at (0, 0). Take this into account!
     */
    public Vector2 unproject(Vector2 tileCoords) {
        return tileCoords.scl((float) tileSize);
    }

    //TODO maybe move somewhere else?

    /**
     * returns the animated position of entity
     * takes into account tilePosition, previousPosition, and lerpAlpha
     */
    public Vector2 getLerpedPosition(Entity entity) {
        if (entity.isMoving) {
            entityPositionUtil.set(entity.getPreviousPosition());
            entityPositionUtil.lerp(entity.getTilePosition(), entity.lerpAlpha);
        } else {
            entityPositionUtil.set(entity.getTilePosition());
        }
        return entityPositionUtil;
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
