package com.jaxforreal.jgame.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jaxforreal.jgame.Direction;
import com.jaxforreal.jgame.GameManager;

//TODO this is just a placeholder, need to make Mob superclass
public class Zombie extends Entity {
    Texture texture;
    float elapsedTime;

    public Zombie(GameManager gameManager) {
        super(gameManager);
        texture = gameManager.assets.get("core/assets/tiles/brick_red.png", Texture.class);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        elapsedTime += delta;
        //(1 / movePerSecond) is number of seconds a move takes
        if (elapsedTime > (1 / movePerSecond)) {
            tryMove(Direction.random());
            elapsedTime = 0;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch, float x, float y) {
        spriteBatch.draw(texture, x, y, parentMap.tileSize, parentMap.tileSize);
    }

    @Override
    public Entity getClone() {
        return new Zombie(gameManager);
    }
}
