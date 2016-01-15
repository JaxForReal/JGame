package com.jaxforreal.jgame.tile;

import com.badlogic.gdx.graphics.Texture;
import com.jaxforreal.jgame.GameManager;

/**
 * A list of all the available tile types in the game.
 * <p/>
 * NOTE: Remember to clone these values, not use them directly!
 */
public class TileList {
    public Tile WOOD;
    public Tile GRASS;
    public Tile WATER;
    public Tile STONE;

    public TileList(GameManager gameManager) {
        WOOD = new SimpleTile(gameManager,
                gameManager.assets.get("core/assets/tiles/wood.png", Texture.class),
                true,
                0);
        GRASS = new SimpleTile(gameManager,
                gameManager.assets.get("core/assets/tiles/grass.png", Texture.class),
                false,
                1);
        WATER = new Water(gameManager, 2);

        STONE = new SimpleTile(gameManager,
                gameManager.assets.get("core/assets/tiles/stone.png", Texture.class),
                true,
                3);
    }
}
