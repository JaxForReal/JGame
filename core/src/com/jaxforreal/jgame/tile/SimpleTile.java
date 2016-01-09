package com.jaxforreal.jgame.tile;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.Util;

/**
 * Simple tile without custom behaviour
 */
public class SimpleTile extends Tile {
    protected Texture texture;
    private boolean isSolid;

    public SimpleTile(GameManager gameManager, Texture texture, boolean isSolid, int id) {
        super(gameManager, id);
        this.texture = texture;
        this.isSolid = isSolid;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Util.drawWithActorProperties(batch, this, texture);
    }

    @Override
    public Tile getClone() {
        return new SimpleTile(gameManager, texture, isSolid, id);
    }

    public boolean isSolid() {
        return isSolid;
    }
}
