package com.jaxforreal.jgame;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jaxforreal.jgame.entity.Entity;
import com.jaxforreal.jgame.tile.Tile;

public class Map extends Group {
    /**
     * the width and height of each tile
     */
    public final int tileSize = 110;
    private final GameManager gameManager;
    /**
     * Tiles are added after this pointer with addActorAfter(actor, actor);
     */
    private Actor tileActorsPointer = new Actor();

    /**
     * NOTE: tiles are stored in a Tile[x][y] format. Also stored as Actors in this Group
     */
    private Tile[][] tiles;

    public Map(int width, int height, final GameManager gameManager) {
        this.gameManager = gameManager;
        //initialize tile array
        tiles = new Tile[width][height];

        //add new tile at click position
        addListener(new ClickListener(Input.Buttons.LEFT) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("click in map");

                Actor hitActor = hit(x, y, true);
                if (hitActor instanceof Tile) {
                    Tile hitTile = (Tile) hitActor;
                    Vector2 hitTilePos = hitTile.getPositionInParent();
                    setTileAt(
                            (int) hitTilePos.x,
                            (int) hitTilePos.y,
                            gameManager.tileList.STONE.getClone());
                }
            }
        });

    }

    /**
     * Adds a tile to the location, and plays popup animation on tile
     */
    void setTileAt(int x, int y, Tile newTile) {
        removeActor(getTileAt(x, y));

        tiles[x][y] = newTile;
        newTile.getPositionInParent().set(x, y);
        newTile.setBounds(x * tileSize, y * tileSize, tileSize, tileSize);

        //replace old tile with new
        addActorAfter(tileActorsPointer, newTile);

        //add tile zoom up/in animation
        newTile.addAction(
                Actions.sequence(
                        //make tiny
                        Actions.moveBy(tileSize / 2, -tileSize / 2), Actions.scaleTo(0f, 0f),
                        Actions.parallel(
                                //zoom up
                                Actions.moveBy(-tileSize / 2, tileSize / 2, 0.25f, Interpolation.circle),
                                Actions.scaleTo(1, 1, 0.25f, Interpolation.circle)
                        )
                )
        );
    }

    /**
     * notifies tile under entity that it was stepped on by entity
     */
    public void notifyEntityMove(Entity entity) {
        tiles[(int) entity.getTilePosition().x][(int) entity.getTilePosition().y].onEntityOver(entity);
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
