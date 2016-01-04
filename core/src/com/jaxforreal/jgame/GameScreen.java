package com.jaxforreal.jgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends ScreenAdapter {
    public JGameMain game;
    public GameManager gameManager;

    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private TileMap tileMap_test_;

    //assetManager is passed in from LoadingScreen
    public GameScreen(JGameMain game, AssetManager assetManager) {
        this.game = game;
        this.spriteBatch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(3200, 1800, camera);
        gameManager = new GameManager();
        gameManager.assets = assetManager;

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);

        MapLoader mapLoader = new MapLoader(gameManager);
        tileMap_test_ = mapLoader.loadFromFile("core/assets/testmap.txt", "core/assets/testmap.xml");
        //y up projection
        camera.setToOrtho(false, 3200, 1800);
        camera.position.set(-50, -50, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        tileMap_test_.update(delta);

        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        tileMap_test_.render(spriteBatch, 0, 0);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}