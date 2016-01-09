package com.jaxforreal.jgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Util {
    public static void drawWithActorProperties(Batch spriteBatch, Actor actor, Texture texture) {
        spriteBatch.draw(texture,
                actor.getX(), actor.getY(),
                actor.getOriginX(), actor.getOriginY(),
                actor.getWidth(), actor.getHeight(),
                actor.getScaleX(), actor.getScaleY(),
                actor.getRotation(),
                0, 0,
                texture.getWidth(), texture.getHeight(),
                false, false);
    }
}
