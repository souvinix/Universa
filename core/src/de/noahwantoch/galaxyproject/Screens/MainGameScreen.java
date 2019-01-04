package de.noahwantoch.galaxyproject.Screens;

import com.badlogic.gdx.Screen;

import de.noahwantoch.galaxyproject.AsteroidClasses.AsteroidManagement;
import de.noahwantoch.galaxyproject.HUD.Background;
import de.noahwantoch.galaxyproject.ControllerClasses.Controller;
import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.HUD.LogoHandler;
import de.noahwantoch.galaxyproject.Game.GameHandler;

public class MainGameScreen implements Screen {

    private static final String TAG = MainGameScreen.class.getSimpleName();

    private Controller controller;
    private Background background;
    private AsteroidManagement asteroidManagement;
    private LogoHandler logoHandler;

    @Override
    public void show() {
        logoHandler = new LogoHandler();
        controller = new Controller();
        background = new Background();
        asteroidManagement = new AsteroidManagement();
        asteroidManagement.start();
    }

    @Override
    public void render(float delta) {
        background.renderBackground(delta);


        if(!logoHandler.isDisposed()){
            logoHandler.renderLogo(delta);
        }

        if(logoHandler.isDisposed() && !GameHandler.isIntroDone()){
            controller.getPlayer().renderIntroAnimation(delta);
        }

        if(logoHandler.isDisposed() && GameHandler.isIntroDone()){
            asteroidManagement.render(delta);
            controller.renderController(delta);
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        controller.dispose();
        asteroidManagement.dispose();

        Batch.getBatch().dispose();
    }
}
