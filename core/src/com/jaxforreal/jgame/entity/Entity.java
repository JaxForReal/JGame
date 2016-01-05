package com.jaxforreal.jgame.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jaxforreal.jgame.Direction;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.Map;

public abstract class Entity {
    public boolean isMoving;
    /**
     * [if moving] the amount moved from prev to new
     * scale of 0 -> 1
     */
    public float lerpAlpha;
    protected float movePerSecond = 4f;
    protected Map parentMap;
    protected GameManager gameManager;

    /**
     * the current position that this entity is at OR currently moving to
     */
    private Vector2 tilePosition;
    /**
     * previous position that this entity was at before move
     * (or currently moving from this position)
     */
    private Vector2 previousPosition;

    public Entity(GameManager gameManager) {
        this.gameManager = gameManager;
        this.tilePosition = new Vector2();
        this.previousPosition = new Vector2();
    }

    /**
     * update tileX and tileY here
     */
    public void update(float delta) {
        if (isMoving) {
            lerpAlpha += delta * movePerSecond;
        }
        //if close to 1
        if (lerpAlpha >= 0.95f) {
            isMoving = false;
            lerpAlpha = 0;
        }
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
            previousPosition.set(tilePosition);
            tilePosition.set(possiblePos);
            lerpAlpha = 0;
            isMoving = true;
            return true;
        }
        return false;
    }

    public Vector2 getTilePosition() {
        return tilePosition;
    }

    public Vector2 getPreviousPosition() {
        return previousPosition;
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