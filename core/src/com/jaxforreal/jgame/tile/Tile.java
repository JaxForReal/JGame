package com.jaxforreal.jgame.tile;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jaxforreal.jgame.GameManager;
import com.sun.istack.internal.Nullable;

public abstract class Tile extends Actor {
    //All tiles in game here

    /**
     * ID is used to save tiles back to text format
     * and to equate tiles
     */
    public final int id;
    public GameManager gameManager;

    /**
     * Tile bounds are set in Map when tiles are added
     */
    public Tile(@Nullable GameManager gameManager, int id) {
        this.gameManager = gameManager;
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Tile) && (this.id == ((Tile) obj).id);
    }

    public abstract Tile getClone();

    public abstract boolean isSolid();
}