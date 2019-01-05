package de.noahwantoch.galaxyproject.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameHandler{

    private static boolean isGameOver = false;
    private static boolean isIntroDone = false;
    private static boolean isAnimationFinished = false;
    private static final int MAX_TOUCHES = 6;

    private GameHandler() {}

    public static void setGameOver(boolean value){
        isGameOver = value;
    }

    public static boolean isGameOver(){
        return isGameOver;
    }

    public static void setAnimationFinished(boolean value){
        isAnimationFinished = value;
    }

    public static boolean isAnimationFinished(){
        return isAnimationFinished;
    }

    public static boolean isIntroDone(){
        return isIntroDone;
    }

    public static void setIsIntroDone(boolean value){
        isIntroDone = value;
    }

    public static int getMaxTouches(){
        return MAX_TOUCHES;
    }

}
