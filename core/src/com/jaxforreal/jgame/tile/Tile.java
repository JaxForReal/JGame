package com.jaxforreal.jgame.tile;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.MyCloneable;
import com.sun.istack.internal.Nullable;

public abstract class Tile extends Actor implements MyCloneable<Tile> {
    /**
     * ID is used to save tiles back to text format
     * and to equate tiles
     */
    public final int id;
    public GameManager gameManager;

    private Vector2 positionInParent = new Vector2();

    /**
     * Tile bounds are set in Map when tiles are added
     */
    public Tile(@Nullable GameManager gameManager, int id) {
        this.gameManager = gameManager;
        this.id = id;

        setTouchable(Touchable.enabled);
        setOrigin(0, 0);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Tile) && (this.id == ((Tile) obj).id);
    }

    public abstract Tile getClone();

    public abstract boolean isSolid();

    /**
     * Returns the tile position in parent map.
     * This value is set in Map.setTileAt().
     * Used to avoid searching over an array for tile positions
     */
    public Vector2 getPositionInParent() {
        return positionInParent;
    }
}