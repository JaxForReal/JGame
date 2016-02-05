package com.jaxforreal.jgame.tile;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.Util;
import com.jaxforreal.jgame.entity.Entity;
import com.jaxforreal.jgame.entity.Player;

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

    @Override
    public void onEntityOver(Entity entity) {
        if(entity instanceof Player) {
            addAction(Actions.rotateBy(360, 1f));
        }
    }
}
