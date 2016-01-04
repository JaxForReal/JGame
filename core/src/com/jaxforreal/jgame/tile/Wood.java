package com.jaxforreal.jgame.tile;

import com.jaxforreal.jgame.GameManager;

public class Wood extends SimpleTile {

    public Wood(GameManager gameManager, int id) {
        super(gameManager, id);
        texture = gameManager.assets.get("core/assets/tiles/wood.png");
    }

    @Override
    public Tile getClone() {
        return new Wood(gameManager, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
