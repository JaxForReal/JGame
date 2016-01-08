package com.jaxforreal.jgame.tile;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.jaxforreal.jgame.GameManager;

public abstract class SimpleTile extends Tile {
    protected Texture texture;

    public SimpleTile(GameManager gameManager, int id) {
        super(gameManager, id);
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void draw(Batch spriteBatch, float parentAlpha) {
        new Integer(0);
        spriteBatch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public abstract Tile getClone();

    @Override
    public abstract boolean isSolid();
}
