package com.jaxforreal.jgame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class JGameMain extends Game {

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        //LoadingScreen inits GameScreen
        setScreen(new LoadingScreen(this));
    }
}
