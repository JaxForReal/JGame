package com.jaxforreal.jgame;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.jaxforreal.jgame.MapObjects.TileMapObject;

public class Player extends TileMapObject {
    private static final int[] WASD = new int[]{51, 29, 47, 32};
    private Array<Integer> pressedKeys = new Array<Integer>();
    public PlayerInputProcessor inputProcessor = new PlayerInputProcessor();

    public Player(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void update(float delta) {
        if (pressedKeys.contains(Input.Keys.W, false)) {
            tryMove(Direction.UP);
        }
        if (pressedKeys.contains(Input.Keys.A, false)) {
            tryMove(Direction.LEFT);
        }
        if (pressedKeys.contains(Input.Keys.S, false)) {
            tryMove(Direction.DOWN);
        }
        if (pressedKeys.contains(Input.Keys.D, false)) {
            tryMove(Direction.RIGHT);
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch, float x, float y) {

    }

    @Override
    public TileMapObject clone() {
        return new Player(gameManager);
    }

    //check is key is relevant to player
    private boolean isKeyRelevant(int keyCode) {
        for (int k : WASD) {
            if (keyCode == k) {
                return true;
            }
        }
        return false;
    }

    //TODO performance hit from unboxing/boxing Integer->int
    //No premature optimization!!!
    public class PlayerInputProcessor implements com.badlogic.gdx.InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            if (isKeyRelevant(keycode)) {
                pressedKeys.add(keycode);
                return true;
            }
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            if (isKeyRelevant(keycode)) {
                pressedKeys.removeValue(new Integer(keycode), false);
                return true;
            }
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
