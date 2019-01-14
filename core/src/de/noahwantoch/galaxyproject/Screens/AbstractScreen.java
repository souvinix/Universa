package de.noahwantoch.galaxyproject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import de.noahwantoch.galaxyproject.Game.Button;
import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;
import de.noahwantoch.galaxyproject.Windows.Exitbutton;

public abstract class AbstractScreen implements Screen {

    private static final String TAG = AbstractScreen.class.getSimpleName();

    private Exitbutton exitbutton;

    @Override
    public void show() {

        exitbutton = new Exitbutton(CurrentSystem.getScreenWidth() * 0.9f, CurrentSystem.getScreenHeight() * 0.9f, 7f);

    }

    @Override
    public void render(float delta) {

        Batch.getBatch().begin();
        exitbutton.draw(Batch.getBatch(), delta);
        Batch.getBatch().end();

    }

    public ScreenEnum getScreenEnum(){
        return null;
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
        exitbutton.dispose();
    }
}
