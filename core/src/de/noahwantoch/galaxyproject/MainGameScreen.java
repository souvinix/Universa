package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import de.noahwantoch.galaxyproject.AsteroidClasses.AsteroidManagement;
import de.noahwantoch.galaxyproject.Helper.Batch;

public class MainGameScreen implements Screen {

    private static final String TAG = MainGameScreen.class.getSimpleName();

    private Controller controller;
    private Background background;
    private AsteroidManagement asteroidManagement;
    private Logo logo;

    @Override
    public void show() {
        logo = new Logo();
        controller = new Controller();
        background = new Background();
        asteroidManagement = new AsteroidManagement();
        asteroidManagement.start();
    }

    @Override
    public void render(float delta) {

        background.renderBackground(delta);

        if(!logo.isDisposed()){
            logo.renderLogo(delta);
        }

        if(logo.isDisposed() && !controller.getPlayer().isIntroDone()){
            controller.getPlayer().renderIntroAnimation(delta);
            Gdx.app.debug(TAG, "LEL");
        }

        if(logo.isDisposed() && controller.getPlayer().isIntroDone()){
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
        Batch.getBatch().dispose();
        background.dispose();
        controller.dispose();
        asteroidManagement.dispose();
    }
}
