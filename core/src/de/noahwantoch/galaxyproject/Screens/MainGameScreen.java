package de.noahwantoch.galaxyproject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import de.noahwantoch.galaxyproject.AroundThePlayer.Player;
import de.noahwantoch.galaxyproject.AsteroidClasses.AsteroidManagement;
import de.noahwantoch.galaxyproject.Game.Button;
import de.noahwantoch.galaxyproject.Game.Font;
import de.noahwantoch.galaxyproject.HUD.Background;
import de.noahwantoch.galaxyproject.ControllerClasses.Controller;
import de.noahwantoch.galaxyproject.HUD.LogoHandler;
import de.noahwantoch.galaxyproject.Game.GameHandler;
import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;

public class MainGameScreen extends AbstractScreen implements Screen {

    private static final String TAG = MainGameScreen.class.getSimpleName();

    private Controller controller;
    private Background background;
    private AsteroidManagement asteroidManagement;
    private LogoHandler logoHandler;

    /** Game Over stuff */
    private Font gameOverFont;
    private Button gameOverButton;
    private static final String FONT_GAMEOVER_TEXT = "Game Over!";
    private static final String BUTTON_TEXT = "Titelbild";

    @Override
    public void show() {
        logoHandler = new LogoHandler();
        controller = new Controller();
        background = new Background();
        asteroidManagement = new AsteroidManagement();
        asteroidManagement.start();

        /** Game Over stuff */
        gameOverFont = new Font("retganon.ttf", 60f * Gdx.graphics.getDensity(), (int) (5f * Gdx.graphics.getDensity()));
        gameOverFont.setPosition(CurrentSystem.getScreenWidth() / 2, CurrentSystem.getScreenHeight() * 0.75f);
        gameOverButton = new Button(BUTTON_TEXT, CurrentSystem.getScreenWidth() / 2f, CurrentSystem.getScreenHeight() * 0.5f, 30f);
        gameOverButton.setFunctionRestartScreen(getScreenEnum());


    }

    @Override
    public void render(float delta) {
        background.renderBackground(delta);

        if(GameHandler.isExplosionDone()){
            controller.getPlayer().dispose();
        }

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

        if(GameHandler.isGameOver()){
            Batch.getBatch().begin();
            gameOverFont.draw(Batch.getBatch(), FONT_GAMEOVER_TEXT);
            gameOverButton.draw(Batch.getBatch(), delta);
            Batch.getBatch().end();
        }

    }

    public ScreenEnum getScreenEnum(){
        return ScreenEnum.MAIN_GAME_SCREEN;
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
        GameHandler.restart();

        background.dispose();
        controller.dispose();
        asteroidManagement.dispose();
    }
}
