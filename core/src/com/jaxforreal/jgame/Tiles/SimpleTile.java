package com.jaxforreal.jgame.Tiles;


import com.badlogic.gdx.graphics.Texture;
import com.jaxforreal.jgame.GameManager;

public abstract class SimpleTile implements Tile {
    protected GameManager gameManager;
    protected Texture texture;

    public SimpleTile(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public abstract Tile clone();

    @Override
    public abstract boolean isSolid();
}
