package com.jaxforreal.jgame.Tiles;

import com.jaxforreal.jgame.GameManager;

public class Grass extends SimpleTile {
    public Grass(GameManager gameManager) {
        super(gameManager);
        texture = gameManager.assets.get("core/assets/tiles/grass_top.png");
    }

    @Override
    public Tile clone() {
        return new Grass(gameManager);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
