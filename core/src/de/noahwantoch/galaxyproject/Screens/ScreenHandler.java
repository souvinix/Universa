package de.noahwantoch.galaxyproject.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import java.util.ArrayList;

public class ScreenHandler {

    private static final String TAG = ScreenHandler.class.getSimpleName();

    private static ScreenHandler instance;

    private Game game;

    private ArrayList<ScreenEnum> history = new ArrayList<ScreenEnum>();

    private ScreenEnum lastScreenEnum;


    private ScreenHandler() {
        super();
    }

    // Singleton: retrieve instance
    public static ScreenHandler getInstance() {
        if (instance == null) {
            instance = new ScreenHandler();
        }
        return instance;
    }

    // Initialization with the game class
    public void initialize(Game game) {
        this.game = game;
    }

    // Show in the game the screen which enum type is received
    public void showScreen(ScreenEnum screenEnum){
        history.add(screenEnum);

        if(screenEnum.getScreen() != null){

            Screen last = game.getScreen();
            AbstractScreen newScreen = screenEnum.getScreen();

            game.setScreen(newScreen);

            if(last != null){
                last.dispose();
            }
        }
    }



    public void restartScreen(ScreenEnum screenEnum){

        game.getScreen().dispose();
        game.setScreen(screenEnum.getScreen());

    }

    public void printDebug(boolean value, ScreenEnum screenEnum){
        if(value){
            if(getLastScreen() == null){
                Gdx.app.debug(TAG,  "LastScreen: Null");
            }
            if(screenEnum.getScreen() == null){
                Gdx.app.debug(TAG, "Want to change to: Null");
            }
            if(getLastScreen() != null){
                Gdx.app.debug(TAG,  "LastScreen: " + getLastScreen().getName());
            }
            if(screenEnum.getScreen() != null){
                Gdx.app.debug(TAG, "Wanted to change to: " + screenEnum.getName());
            }
        }
    }

    public ScreenEnum getLastScreen(){
        if(history.size() < 2){
            return null;
        }
        return history.get(history.size() - 2);
    }

    public ArrayList<ScreenEnum> history(){
        return history;
    }

    public Screen getCurrentScreen() {
        return game.getScreen();
    }

}