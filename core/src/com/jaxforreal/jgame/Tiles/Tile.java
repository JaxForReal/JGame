package com.jaxforreal.jgame.Tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jaxforreal.jgame.GameManager;

public abstract class Tile {
    //All tiles in game here
    public static final Tile GRASS = new Grass(null, 0);
    public static final Tile WOOD = new Wood(null, 1);

    public final int id;
    public GameManager gameManager;

    public Tile(GameManager gameManager, int id) {
        this.gameManager = gameManager;
        this.id = id;
    }

    public abstract void update(float delta);

    public abstract void render(SpriteBatch spriteBatch, float x, float y, float w, float h);

    public abstract Tile getClone();

    public abstract boolean isSolid();
}