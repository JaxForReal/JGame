package com.jaxforreal.jgame;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.jaxforreal.jgame.MapObjects.Entity;

public class Player extends Entity {
    private static final int[] WASD = new int[]{51, 29, 47, 32};
    public PlayerInputProcessor inputProcessor = new PlayerInputProcessor();
    private Array<Integer> pressedKeys = new Array<Integer>();

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
        new Integer(0);
        spriteBatch.draw(
                gameManager.assets.get("core/assets/tiles/dirt_snow.png", Texture.class),
                x,
                y,
                parentMap.tileSize,
                parentMap.tileSize
        );
    }

    @Override
    public Entity clone() {
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
                pressedKeys.removeValue(keycode, false);
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
