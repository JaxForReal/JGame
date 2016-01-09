package com.jaxforreal.jgame.tile;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.Util;

public abstract class SimpleTile extends Tile {
    protected Texture texture;

    public SimpleTile(GameManager gameManager, int id) {
        super(gameManager, id);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Util.drawWithActorProperties(batch, this, texture);
    }

    @Override
    public abstract Tile getClone();

    @Override
    public abstract boolean isSolid();
}
