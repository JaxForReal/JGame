package com.jaxforreal.jgame.tile;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
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
    public void act(float delta) {
        animationStateTime += delta;
    }

    @Override
    public void draw(Batch spriteBatch, float parentAlpha) {
        spriteBatch.draw(animation.getKeyFrame(animationStateTime, true), getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public abstract Tile getClone();

    @Override
    public abstract boolean isSolid();
}
