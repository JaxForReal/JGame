package com.jaxforreal.jgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jaxforreal.jgame.entity.Player;

public class GameScreen extends ScreenAdapter {
    //TODO not safe...(must be accessed after game init/LoadingScreen)
    public static GameManager gameManager;
    public JGameMain game;
    public Stage stage;
    private OrthographicCamera camera;

    private Map map;
    private Player player;

    //assetManager is passed in from LoadingScreen or MainMenuScreen
    public GameScreen(JGameMain game, AssetManager assetManager) {

        //initialization
        this.game = game;
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new FitViewport(3200, 1800, camera));

        gameManager = new GameManager();
        gameManager.assets = assetManager;
        gameManager.gameScreen = this;

        //Set up input and player
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
        inputMultiplexer.addProcessor(stage);

        player = new Player(gameManager);
        inputMultiplexer.addProcessor(player.inputProcessor);


        //load new map and add player
        MapLoader mapLoader = new MapLoader(gameManager);
        map = mapLoader.loadFromFile("core/assets/testmap.txt", "core/assets/testmap.xml");
        map.addMapObject(player);
        player.setTilePosition(1, 1);

        stage.addActor(map);
    }

    @Override
    public void render(float delta) {
        //clear background
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getCamera().position.set(player.getX(), player.getY(), 0);
        camera.update();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

}