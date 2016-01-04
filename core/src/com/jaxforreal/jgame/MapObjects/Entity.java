package com.jaxforreal.jgame.MapObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jaxforreal.jgame.Direction;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.Map;

//TODO what to do about sub-tile objects, like arrows???
//TODO maybe handle own position flag?
public abstract class Entity {
    protected Map parentMap;
    protected GameManager gameManager;
    private int tileX, tileY;

    public Entity(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * update tileX and tileY here
     */
    public abstract void update(float delta);

    /**
     * rendering is handled by Map.render()
     * to move Objects, update tileX and tileY,
     * render x and y will be auto calced
     */
    public abstract void render(SpriteBatch spriteBatch, float x, float y);

    protected void tryMove(Direction direction) {
        //TODO possible optimization: no more object creation??
        int newTileX = tileX + direction.x();
        int newTileY = tileY + direction.y();
        if ((newTileX < 0) || (newTileY < 0) ||
                (newTileX >= parentMap.getWidthInTiles()) || (newTileY >= parentMap.getHeightInTiles())) {
            return;
        }
        if (!parentMap.getTileAt(newTileX, newTileY).isSolid()) {
            tileX = newTileX;
            tileY = newTileY;
        }
    }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    /**
     * remember to do this after cloning a TimeMapObject
     */
    public void setParentMap(Map parentMap) {
        this.parentMap = parentMap;
    }

    //clone the tilemapobject. copies all properties and metadata EXCEPT parentMap
    public abstract Entity clone();
}
