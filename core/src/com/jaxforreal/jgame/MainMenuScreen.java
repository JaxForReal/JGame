package com.jaxforreal.jgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainMenuScreen extends ScreenAdapter {
    private JGameMain game;
    private AssetManager assetManager;
    private Stage stage;
    private Table table;

    //assetmanager passed in from loadingscreen
    public MainMenuScreen(final JGameMain game, final AssetManager assetManager) {
        this.game = game;
        this.assetManager = assetManager;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //setup button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(
                assetManager.get("core/assets/tiles/stone.png", Texture.class)
        ));
        textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(
                assetManager.get("core/assets/tiles/cactus_side.png", Texture.class)
        ));
        textButtonStyle.font = assetManager.get("core/assets/font.fnt");
        textButtonStyle.fontColor = Color.BLUE;

        //setup button
        final TextButton startButton = new TextButton("Start Game", textButtonStyle);
        //onclick
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game, assetManager));
                startButton.setDisabled(true);
                dispose();
            }
        });
        table.add(startButton);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
