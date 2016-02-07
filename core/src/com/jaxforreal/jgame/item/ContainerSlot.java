package com.jaxforreal.jgame.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jaxforreal.jgame.GameManager;
import com.jaxforreal.jgame.Util;

public class ContainerSlot extends Actor {
    public static final int SIZE = 100;

    private Texture texture;

    public ContainerSlot(GameManager manager) {
        texture = manager.assets.get("core/assets/containerSlot.png", Texture.class);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Util.drawWithActorProperties(batch, this, texture);
    }
}
