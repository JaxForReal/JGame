package com.jaxforreal.jgame.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.jaxforreal.jgame.Direction;
import com.jaxforreal.jgame.GameManager;

public class Player extends Entity {
    //WASD key codes for com.badlogic.gdx.Input.Keys
    private static final int[] WASD = new int[]{51, 29, 47, 32};

    public PlayerInputProcessor inputProcessor = new PlayerInputProcessor();
    private Array<Integer> pressedKeys = new Array<Integer>();

    public Player(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!isMoving()) {
            if (pressedKeys.contains(Input.Keys.W, false)) {
                tryMove(Direction.UP);
                return;
            }
            if (pressedKeys.contains(Input.Keys.A, false)) {
                tryMove(Direction.LEFT);
                return;
            }
            if (pressedKeys.contains(Input.Keys.S, false)) {
                tryMove(Direction.DOWN);
                return;
            }
            if (pressedKeys.contains(Input.Keys.D, false)) {
                tryMove(Direction.RIGHT);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(gameManager.assets.get("core/assets/tiles/dirt.png", Texture.class),
                getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public Entity getClone() {
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

    public class PlayerInputProcessor implements InputProcessor {

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
