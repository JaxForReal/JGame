package com.jaxforreal.jgame.Tiles;

import com.jaxforreal.jgame.GameManager;

public class Wood extends SimpleTile {

    public Wood(GameManager gameManager) {
        super(gameManager);
        texture = gameManager.assets.get("core/assets/tiles/wood.png");
    }

    @Override
    public Tile getClone() {
        return new Wood(gameManager);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
