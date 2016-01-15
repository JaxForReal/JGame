package com.jaxforreal.jgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jaxforreal.jgame.entity.Player;
import com.jaxforreal.jgame.item.ItemData;
import com.jaxforreal.jgame.item.ItemEntity;
import com.jaxforreal.jgame.tile.TileList;
import com.jaxforreal.jgame.tile.Water;

public class GameScreen extends ScreenAdapter {
    public static GameManager gameManager = new GameManager();

    public JGameMain game;

    private OrthographicCamera camera = new OrthographicCamera();
    private Stage stage = new Stage(new FitViewport(3200, 1800, camera));

    private Vector2 smoothCameraPos = new Vector2();
    private Vector2 tempCoords = new Vector2();

    private Map map;
    private Player player;

    //assetManager is passed in from LoadingScreen or MainMenuScreen
    public GameScreen(JGameMain game, AssetManager assetManager) {
        this.game = game;

        gameManager.assets = assetManager;
        gameManager.gameScreen = this;
        gameManager.tileList = new TileList(gameManager);

        Gdx.input.setInputProcessor(stage);

        player = new Player(gameManager);

        //load new map and add player
        MapLoader mapLoader = new MapLoader(gameManager);
        map = mapLoader.loadFromFile("core/assets/testmap.txt", "core/assets/testmap.xml");
        //ProceduralMapLoader mapLoader = new ProceduralMapLoader(gameManager);
        //map = mapLoader.load(33);
        map.addEntityAt(1, 1, player);
        stage.setKeyboardFocus(player);

        ItemData itemData = new ItemData();
        itemData.gameManager = gameManager;
        itemData.isPlaceable = false;
        itemData.onPlaceTile = new Water(gameManager, 45);
        itemData.texture = assetManager.get("core/assets/tiles/wood.png", Texture.class);

        map.addEntityAt(2, 2, new ItemEntity(itemData));

        stage.addActor(map);
    }

    @Override
    public void render(float delta) {
        //clear background
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        smoothCameraPos.lerp(new Vector2(player.getX(), player.getY()), 0.02f);
        stage.getCamera().position.set(smoothCameraPos, 0);
        camera.update();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

}