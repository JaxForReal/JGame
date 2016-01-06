package com.jaxforreal.jgame.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jaxforreal.jgame.Direction;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.Map;

public abstract class Entity {
    protected Map parentMap;
    protected GameManager gameManager;

    /**
     * the current position that this entity is at OR currently moving to
     */
    private Vector2 tilePosition;

    public Entity(GameManager gameManager) {
        this.gameManager = gameManager;
        this.tilePosition = new Vector2();
    }

    /**
     * update tileX and tileY here
     */
    public void update(float delta) {
    }

    /**
     * rendering is handled by Map.render()
     * to move Objects, update tileX and tileY,
     * render x and y will be auto calced
     */
    public abstract void render(SpriteBatch spriteBatch, float x, float y);

    /**
     * @return whether or not the object actually moved (false if collided with solid tile)
     */
    protected boolean tryMove(Direction direction) {

        Vector2 possiblePos = new Vector2(
                getTilePosition().x + direction.x(),
                getTilePosition().y + direction.y());

        //check out of map bounds
        if ((possiblePos.x < 0) || (possiblePos.y < 0) ||
                (possiblePos.x >= parentMap.getWidthInTiles()) || (possiblePos.y >= parentMap.getHeightInTiles())) {
            return false;
        }

        //if not solid, move to
        if (!parentMap.getTileAt((int) possiblePos.x, (int) possiblePos.y).isSolid()) {
            tilePosition.set(possiblePos);
            return true;
        }
        return false;
    }

    public Vector2 getTilePosition() {
        return tilePosition;
    }

    /**
     * this method is called when you add this entity to parent map
     */
    public void setParentMap(Map parentMap) {
        this.parentMap = parentMap;
    }

    //clone the Entity. copies all properties and metadata EXCEPT parentMap
    //note: named getClone(), not clone() because java.lang.Object's clone() is annoying and gives "call super.clone()" warning
    public abstract Entity getClone();
}