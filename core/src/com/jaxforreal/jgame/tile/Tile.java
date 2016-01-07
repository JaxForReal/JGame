package com.jaxforreal.jgame.tile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.jaxforreal.jgame.GameManager;
import com.sun.istack.internal.Nullable;

public abstract class Tile {
    //All tiles in game here

    /**
     * ID is used to save tiles back to text format
     * and to equate tiles
     */
    public final int id;
    public GameManager gameManager;

    public Tile(@Nullable GameManager gameManager, int id) {
        this.gameManager = gameManager;
        this.id = id;
    }

    public abstract void update(float delta);

    @Override
    public boolean equals(Object obj) {
        return id == ((Tile)obj).id;
    }

    public abstract void render(Batch spriteBatch, float x, float y, float w, float h);

    public abstract Tile getClone();

    public abstract boolean isSolid();
}