package com.jaxforreal.jgame.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jaxforreal.jgame.Direction;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.Map;

public abstract class Entity {
    protected Map parentMap;
    protected GameManager gameManager;
    private Vector2 tilePosition;

    public Entity(GameManager gameManager) {
        this.gameManager = gameManager;
        this.tilePosition = new Vector2();
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

    /**
     * @return whether or not the object actually moved (false if collided with solid tile)
     */
    protected boolean tryMove(Direction direction) {
        //TODO possible optimization: no more object creation??
        Vector2 newPos = new Vector2(
                tilePosition.x + direction.x(),
                tilePosition.y + direction.y());

        //check out of map bounds
        if ((newPos.x < 0) || (newPos.y < 0) ||
                (newPos.x >= parentMap.getWidthInTiles()) || (newPos.y >= parentMap.getHeightInTiles())) {
            return false;
        }
        //if not solid, move to
        if (!parentMap.getTileAt((int)newPos.x, (int)newPos.y).isSolid()) {
            tilePosition.set(newPos);
            return true;
        }
        return false;
    }

    public Vector2 getTilePosition(){
        return tilePosition;
    }

    /**
     *this method is called when you add this entity to parent map
     */
    public void setParentMap(Map parentMap) {
        this.parentMap = parentMap;
    }

    //clone the Entity. copies all properties and metadata EXCEPT parentMap
    //note: named getClone(), not clone() because java.lang.Object's clone() is annoying and gives "call super.clone()" warning
    public abstract Entity getClone();
}
