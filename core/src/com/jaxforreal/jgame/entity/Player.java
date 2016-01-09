package com.jaxforreal.jgame.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.jaxforreal.jgame.Direction;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.Util;

public class Player extends Entity {
    //WASD key codes for com.badlogic.gdx.Input.Keys
    private static final int[] WASD = new int[]{51, 29, 47, 32};

    private Array<Integer> pressedKeys = new Array<Integer>();

    public Player(GameManager gameManager) {
        super(gameManager);

        addListener(new InputListener() {

            public boolean keyDown(InputEvent event, int keycode) {
                if (isKeyRelevant(keycode)) {
                    pressedKeys.add(keycode);
                    return true;
                }
                return false;
            }

            public boolean keyUp(InputEvent event, int keycode) {
                if (isKeyRelevant(keycode)) {
                    pressedKeys.removeValue(keycode, false);
                    return true;
                }
                return false;
            }
        });

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
        Util.drawWithActorProperties(batch, this,
                gameManager.assets.get("core/assets/tiles/dirt.png", Texture.class));
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
}
