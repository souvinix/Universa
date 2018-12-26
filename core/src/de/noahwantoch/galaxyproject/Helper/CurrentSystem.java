package de.noahwantoch.galaxyproject.Helper;

import com.badlogic.gdx.Gdx;

public class CurrentSystem{
    private static int screenWidth = Gdx.graphics.getWidth(); //For example 1700
    private static int screenHeight = Gdx.graphics.getHeight(); //For example 3000

    private CurrentSystem(){}

    public static int getScreenWidth(){ return screenWidth; }
    public static int getScreenHeight(){ return screenHeight; }

}
