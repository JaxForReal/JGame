package com.jaxforreal.jgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreen extends ScreenAdapter {
    SpriteBatch spriteBatch;
    JGameMain game;
    AssetManager assetManager;

    //TODO (maybe) specify which assets to load in a different class??
    public LoadingScreen(JGameMain game) {
        this.game = game;

        assetManager = new AssetManager();

        //load font
        assetManager.load("core/assets/font.fnt", BitmapFont.class);
        //need to finish so we can display loaded percentage in render()
        assetManager.finishLoadingAsset("core/assets/font.fnt");

        //load all tile textures
        FileHandle tilesFolder = Gdx.files.internal("core/assets/tiles");
        for (FileHandle tileFile : tilesFolder.list(".png")) {
            assetManager.load(tileFile.path(), Texture.class);
        }
        assetManager.load("core/assets/test_animation.png", Texture.class);

        this.spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        assetManager.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        String loaded = "Loading " + (int) (assetManager.getProgress() * 100f) + "%";
        //interesting note: fonts render below Y value, instead of above...wat?
        assetManager.get("core/assets/font.fnt", BitmapFont.class)
                .draw(spriteBatch, loaded, 0, Gdx.graphics.getHeight());
        spriteBatch.end();

        //goto new screen if finished
        if (assetManager.getProgress() == 1) {
            game.setScreen(new GameScreen(game, assetManager));
            dispose();
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
