package com.jaxforreal.jgame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * put globally needed objects here (eg. AssetManager),
 * because this object is passed to most screens
 */
public class JGameMain extends Game {

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        //LoadingScreen inits GameScreen
        //setScreen(new LoadingScreen(this));

        setScreen(new LoadingScreen(this));
    }
}
