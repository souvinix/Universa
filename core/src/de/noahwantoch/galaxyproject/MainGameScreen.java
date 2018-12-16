package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.Screen;

import de.noahwantoch.galaxyproject.AsteroidClasses.AsteroidManagement;

public class MainGameScreen implements Screen {

    private static final String TAG = MainGameScreen.class.getSimpleName();

    private Controller controller;
    private Background background;
    private AsteroidManagement asteroidManagement;

    @Override
    public void show() {
        controller = new Controller();
        background = new Background();
        asteroidManagement = new AsteroidManagement();
        asteroidManagement.start();


    }

    @Override
    public void render(float delta) {

        background.renderBackground(delta);
        controller.renderController(delta);
        asteroidManagement.render(delta);


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
