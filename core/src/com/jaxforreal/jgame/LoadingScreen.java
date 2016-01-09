package com.jaxforreal.jgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

//TODO make loading indicator resize properly
public class LoadingScreen extends ScreenAdapter {
    static final Array<String> TITLE_STRINGS = new Array<String>();

    static {
        TITLE_STRINGS.add("<Title text here>");
        TITLE_STRINGS.add("here is another title string");
        TITLE_STRINGS.add("boring text");
        TITLE_STRINGS.add("hello there");
    }

    private SpriteBatch spriteBatch;
    private JGameMain game;
    private AssetManager assetManager;

    public LoadingScreen(JGameMain game) {
        this.game = game;
        assetManager = new AssetManager();

        Gdx.graphics.setTitle("My Game: " + TITLE_STRINGS.random());

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
        //interesting note: fonts draw below Y value, instead of above...wat?
        assetManager.get("core/assets/font.fnt", BitmapFont.class)
                .draw(spriteBatch, loaded, 0, Gdx.graphics.getHeight());
        spriteBatch.end();

        //goto new screen if finished
        if (assetManager.getProgress() == 1) {
            game.setScreen(new MainMenuScreen(game, assetManager));
            dispose();
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
