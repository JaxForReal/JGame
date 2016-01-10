package com.jaxforreal.jgame;

import com.badlogic.gdx.assets.AssetManager;
import com.jaxforreal.jgame.tile.TileList;

import java.util.Random;

/**
 * Globals for all members of GameScreen to interact with.
 * This is passed to most game objects, so put needed globals here.
 */
public class GameManager {
    public AssetManager assets;
    public Random random = new Random();
    public GameScreen gameScreen;
    TileList tileList;
}
