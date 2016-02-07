package com.jaxforreal.jgame.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jaxforreal.jgame.GameManager;

public class ItemContainerScreen extends ScreenAdapter{

    public Stage stage = new Stage(new FitViewport(1000, 1000));

    public ItemContainerScreen(AssetManager assets) {
        GameManager manager = new GameManager();
        manager.assets = assets;

        stage.addActor(new ItemConainer(3, 3, manager));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

}
