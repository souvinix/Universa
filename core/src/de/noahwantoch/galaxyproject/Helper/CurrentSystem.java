package de.noahwantoch.galaxyproject.Helper;

import com.badlogic.gdx.Gdx;

import de.noahwantoch.galaxyproject.Background;

public class CurrentSystem{
    private static int screenWidth = Gdx.graphics.getWidth(); //For example 1700
    private static int screenHeight = Gdx.graphics.getHeight(); //For example 3000

    private static float backgroundWidth = Background.getBackgroundWidth(); //288
    private static float backgroundHeight = Background.getBackgroundHeight(); //513

    private CurrentSystem(){}

    public static int getScreenWidth(){ return screenWidth; }
    public static int getScreenHeight(){ return screenHeight; }
    public static float getBackgroundWidth(){ return backgroundWidth; }
    public static float getBackgroundHeight(){ return backgroundHeight; }


}
