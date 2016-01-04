package com.jaxforreal.jgame.tile;

import com.jaxforreal.jgame.GameManager;

public class Grass extends SimpleTile {
    public Grass(GameManager gameManager, int id) {
        super(gameManager, id);
        texture = gameManager.assets.get("core/assets/tiles/grass_top.png");
    }

    @Override
    public Tile getClone() {
        return new Grass(gameManager, id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
