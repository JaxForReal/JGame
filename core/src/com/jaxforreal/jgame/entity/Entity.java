package com.jaxforreal.jgame.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.jaxforreal.jgame.Direction;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.Map;
import com.jaxforreal.jgame.MyCloneable;

/**
 * Entity in the game that can move
 */
public abstract class Entity extends Actor implements MyCloneable<Entity> {
    protected Map parentMap;
    protected GameManager gameManager;

    private MoveByAction moveByAction;
    private float moveDuration = 0.2f;
    private boolean isMoving;
    private float timeSinceLastMove = 0f;

    /**
     * the current position that this entity is at OR currently moving to
     */
    private Vector2 tilePosition;

    public Entity(GameManager gameManager) {
        this.gameManager = gameManager;
        this.tilePosition = new Vector2();
        this.moveByAction = new MoveByAction();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        //handle tracking fields
        if (isMoving) {
            timeSinceLastMove += delta;
            if (timeSinceLastMove >= moveDuration) {
                isMoving = false;
            }
        }
    }

    /**
     * Entity must have a parent map for this to work!
     */
    public void setTilePosition(int x, int y) {
        tilePosition.set(x, y);
        addAction(Actions.moveTo(x * parentMap.tileSize, y * parentMap.tileSize));
        setBounds(x, y, parentMap.tileSize, parentMap.tileSize);
    }

    /**
     * @return whether or not the Entity is moving because tryMove() was called
     */
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * Attempts to move in the specified Direction
     * also updates parent map tiles of entity movement
     *
     * @return whether or not the object actually moved (false if collided with solid tile)
     */
    protected boolean tryMove(Direction direction) {
        //TODO optimize obj creation
        //lol who cares
        Vector2 possiblePos = new Vector2(
                getTilePosition().x + direction.x(),
                getTilePosition().y + direction.y());

        if (canMoveTo(possiblePos)) {

            //update animation
            moveByAction.reset();
            moveByAction.setAmount(
                    direction.x() * parentMap.tileSize,
                    direction.y() * parentMap.tileSize);
            moveByAction.setDuration(moveDuration);
            addAction(moveByAction);

            //update tilePosition
            tilePosition.set(possiblePos);

            //update tracker vars
            isMoving = true;
            timeSinceLastMove = 0f;

            //notify map that an entity moved to new position
            parentMap.notifyEntityMove(this);

            return true;
        }
        return false;
    }

    /**
     * @return true if tilePosition is a collision/invalid move
     */
    private boolean canMoveTo(Vector2 tilePosition) {
        return (tilePosition.x >= 0) &&
                (tilePosition.y >= 0) &&
                (tilePosition.x < parentMap.getWidthInTiles()) &&
                (tilePosition.y < parentMap.getHeightInTiles()) &&
                (!parentMap.getTileAt((int) tilePosition.x, (int) tilePosition.y).isSolid());
    }

    public Vector2 getTilePosition() {
        return tilePosition;
    }

    /**
     * this method is called when you add this entity to parent map
     * <p/>
     * must be called before setting tile position
     */
    public void setParentMap(Map parentMap) {
        this.parentMap = parentMap;
    }

    /**
     * clone the Entity. copies all properties and metadata EXCEPT parentMap
     * note: named getClone(), not clone() because java.lang.Object's clone() is annoying
     * and gives "call super.clone()" warning
     */
    public abstract Entity getClone();
}