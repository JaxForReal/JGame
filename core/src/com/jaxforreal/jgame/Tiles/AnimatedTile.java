package com.jaxforreal.jgame.Tiles;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jaxforreal.jgame.GameManager;

public abstract class AnimatedTile extends Tile {
    /**
     * child classes need to instantiate animation
     */
    protected Animation animation;
    private float animationStateTime;

    public AnimatedTile(GameManager gameManager, int id) {
        super(gameManager, id);
    }

    @Override
    public void update(float delta) {
        animationStateTime += delta;
    }

    @Override
    public void render(SpriteBatch spriteBatch, float x, float y, float w, float h) {
        spriteBatch.draw(animation.getKeyFrame(animationStateTime, true), x, y, w, h);
    }

    @Override
    public abstract Tile getClone();

    @Override
    public abstract boolean isSolid();
}
