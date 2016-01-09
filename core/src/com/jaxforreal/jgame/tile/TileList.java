package com.jaxforreal.jgame.tile;

import com.badlogic.gdx.graphics.Texture;
import com.jaxforreal.jgame.GameManager;

/**
 * A list of all the available tile types in the game
 */
public class TileList {
    public Tile WOOD;
    public Tile GRASS;

    public TileList(GameManager gameManager) {
        WOOD = new SimpleTile(gameManager,
                gameManager.assets.get("core/assets/tiles/wood.png", Texture.class),
                true,
                0);
        GRASS = new SimpleTile(gameManager,
                gameManager.assets.get("core/assets/tiles/grass_top.png", Texture.class),
                false,
                1);
    }
}
