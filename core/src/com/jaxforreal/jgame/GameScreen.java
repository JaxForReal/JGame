package com.jaxforreal.jgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jaxforreal.jgame.entity.Player;
import com.jaxforreal.jgame.tile.TestAnimatedTile;

public class GameScreen extends ScreenAdapter {
    public JGameMain game;
    //TODO not safe...(must be accessed after game init/LoadingScreen)
    public static GameManager gameManager;

    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Vector2 temp;

    private Map map_test_;
    private Player player_test_;

    //assetManager is passed in from LoadingScreen
    public GameScreen(JGameMain game, AssetManager assetManager) {
        this.game = game;
        this.spriteBatch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(3200, 1800, camera);
        this.temp = new Vector2();
        gameManager = new GameManager();
        gameManager.assets = assetManager;

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
        player_test_ = new Player(gameManager);
        inputMultiplexer.addProcessor(player_test_.inputProcessor);
        player_test_.getTilePosition().set(1, 3);

        MapLoader mapLoader = new MapLoader(gameManager);
        map_test_ = mapLoader.loadFromFile("core/assets/testmap.txt", "core/assets/testmap.xml");
        map_test_.addMapObject(player_test_);
        map_test_.setTileAt(0, 0, new TestAnimatedTile(gameManager, 2));
        Gdx.app.log("tileName", mapLoader.getSaveNameById(1));

        //y up projection
        camera.setToOrtho(false, 3200, 1800);
        camera.position.set(-50, -50, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        map_test_.update(delta);
        temp.set(player_test_.getTilePosition());
        camera.position.set(map_test_.tileToWorldCoords(temp), 0);
        camera.update();

        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        map_test_.render(spriteBatch, 0, 0);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}