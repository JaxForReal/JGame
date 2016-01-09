package com.jaxforreal.jgame.tile;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
        super.act(delta);
        animationStateTime += delta;
    }

    @Override
    public void draw(Batch spriteBatch, float parentAlpha) {
        TextureRegion textureRegion = animation.getKeyFrame(animationStateTime, true);
        spriteBatch.draw(textureRegion,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation());

    }

    @Override
    public abstract Tile getClone();

    @Override
    public abstract boolean isSolid();
}
