package com.jaxforreal.jgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jaxforreal.jgame.entity.Player;

public class GameScreen extends ScreenAdapter {
    //TODO not safe...(must be accessed after game init/LoadingScreen)
    public static GameManager gameManager;
    public JGameMain game;
    public Stage stage;

    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;

    private Map map_test_;
    private Player player_test_;

    //assetManager is passed in from LoadingScreen or MainMenuScreen
    public GameScreen(JGameMain game, AssetManager assetManager) {

        //initialization
        this.game = game;
        this.spriteBatch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new FitViewport(3200, 1800, camera));
        stage.getCamera().position.set(-50, -50, 0);

        gameManager = new GameManager();
        gameManager.assets = assetManager;
        gameManager.gameScreen = this;

        //Set up input and player
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
        inputMultiplexer.addProcessor(stage);

        player_test_ = new Player(gameManager);
        inputMultiplexer.addProcessor(player_test_.inputProcessor);


        //load new map and add player
        MapLoader mapLoader = new MapLoader(gameManager);
        map_test_ = mapLoader.loadFromFile("core/assets/testmap.txt", "core/assets/testmap.xml");
        map_test_.addMapObject(player_test_);
        player_test_.setTilePosition(1, 3);

        stage.addActor(map_test_);
    }

    @Override
    public void render(float delta) {
        //clear background
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

}