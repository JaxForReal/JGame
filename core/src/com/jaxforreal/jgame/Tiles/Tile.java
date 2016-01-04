package com.jaxforreal.jgame.Tiles;

import com.badlogic.gdx.graphics.Texture;

public interface Tile {
    Texture getTexture();

    Tile clone();

    boolean isSolid();
}