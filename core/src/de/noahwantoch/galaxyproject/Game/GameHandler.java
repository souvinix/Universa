package de.noahwantoch.galaxyproject.Game;

public class GameHandler{

    private static boolean isGameOver = false;
    private static boolean isIntroDone = false;
    private static boolean isAnimationFinished = false;
    private static boolean isExplosionDone = false;
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

    public static boolean isExplosionDone(){
        return isExplosionDone;
    }

    public static void setIsExplosionDone(boolean value){
        isExplosionDone = value;
    }

    public static void restart(){
        setGameOver(false);
        setIsIntroDone(false);
        setAnimationFinished(false);
        setIsExplosionDone(false);
    }

}
