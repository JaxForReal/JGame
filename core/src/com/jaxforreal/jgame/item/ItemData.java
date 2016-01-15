package com.jaxforreal.jgame.item;

import com.badlogic.gdx.graphics.Texture;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.tile.Tile;

public class ItemData {
    public GameManager gameManager;
    public Texture texture;
    public boolean isPlaceable;
    public Tile onPlaceTile;
}
