package com.jaxforreal.jgame.Tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.jaxforreal.jgame.GameManager;

public class TestAnimatedTile extends AnimatedTile {

    public TestAnimatedTile(GameManager gameManager, int id) {
        super(gameManager, id);
        Texture animSource = gameManager.assets.get("core/assets/tiles/stone_gold.png");
        TextureRegion[][] grid = TextureRegion.split(animSource, 32, 32);
        Array<TextureRegion> regionList = new Array<TextureRegion>();
        for(TextureRegion[] column : grid) {
            for(TextureRegion textureRegion : column) {
                regionList.add(textureRegion);
            }
        }
        animation = new Animation(0.25f, regionList, Animation.PlayMode.LOOP);
    }

    @Override
    public Tile getClone() {
        return new TestAnimatedTile(gameManager, id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
