package com.jaxforreal.jgame.Tiles;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jaxforreal.jgame.GameManager;

public abstract class SimpleTile extends Tile {
    protected Texture texture;

    public SimpleTile(GameManager gameManager, int id) {
        super(gameManager, id);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(SpriteBatch spriteBatch, float x, float y, float w, float h) {
        spriteBatch.draw(texture, x, y, w, h);
    }

    @Override
    public abstract Tile getClone();

    @Override
    public abstract boolean isSolid();
}
