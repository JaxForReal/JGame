package com.jaxforreal.jgame.Tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Tile {
    void update(float delta);

    void render(SpriteBatch spriteBatch, float x, float y, float w, float h);

    Tile getClone();

    boolean isSolid();
}