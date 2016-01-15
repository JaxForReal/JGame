package com.jaxforreal.jgame.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.jaxforreal.jgame.GameManager;

public class Water extends AnimatedTile {

    public Water(GameManager gameManager, int id) {
        super(gameManager, id);

        Texture animSource = gameManager.assets.get("core/assets/tiles/water.png");
        TextureRegion[][] grid = TextureRegion.split(animSource, 16, 16);
        Array<TextureRegion> regionList = new Array<TextureRegion>();
        for (TextureRegion[] column : grid) {
            for (TextureRegion textureRegion : column) {
                regionList.add(textureRegion);
            }
        }
        animation = new Animation(0.25f, regionList, Animation.PlayMode.LOOP);
    }

    @Override
    public Tile getClone() {
        return new Water(gameManager, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
