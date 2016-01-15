package com.jaxforreal.jgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.jaxforreal.jgame.Direction;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.Util;

public class TestMob extends Entity {
    Texture texture;
    float elapsedTime;

    public TestMob(GameManager gameManager) {
        super(gameManager);
        elapsedTime = gameManager.random.nextFloat() * 0.5f;
        texture = gameManager.assets.get("core/assets/tiles/tree.png", Texture.class);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!isMoving()) {
            tryMove(Direction.random());
        }
    }

    @Override
    public void draw(Batch spriteBatch, float parentAlpha) {
        Util.drawWithActorProperties(spriteBatch, this, texture);
    }

    @Override
    public Entity getClone() {
        return new TestMob(gameManager);
    }
}
