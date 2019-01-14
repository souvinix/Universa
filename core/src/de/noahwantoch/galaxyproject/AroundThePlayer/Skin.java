package de.noahwantoch.galaxyproject.AroundThePlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.HashMap;

import de.noahwantoch.galaxyproject.Helper.AnimationHandler;

public class Skin {

    private static final String TAG = Skin.class.getSimpleName();

    private static final String SKINDATA = "spaceship.png";

    private static final int TILE_WIDTH = 189; //How big is the size of 1/4 Spritesheet
    private static final int TILE_HEIGHT = 294;

    private String path;
    private Sprite sprite;

    private HashMap<Integer, String> skinDirectoryByID;

    private static final String[] ALL_SKIN_DIRECTORYS = {"spaceshipMain/"};
    private int valueOfSkins = ALL_SKIN_DIRECTORYS.length;

    private static String currentSkinDirectory;

    private AnimationHandler animationHandler;
    private Animation spaceshipAnimation;
    private Animation explosionAnimation;

    public Skin(String internalDirectory) {
        path = internalDirectory + SKINDATA;

        animationHandler = new AnimationHandler();

        spaceshipAnimation = animationHandler.generateAnimation(getSpriteSheet(), 4, TILE_WIDTH, TILE_HEIGHT, 1f/5f);
        explosionAnimation = animationHandler.generateAnimation(getExplosionSpritesheet(), 16, 63, 63, 1f/25f);

        currentSkinDirectory = internalDirectory;

        skinDirectoryByID = new HashMap<Integer, String>();

        for(String x : ALL_SKIN_DIRECTORYS){
            skinDirectoryByID.put(this.valueOfSkins, x);
        }

        Gdx.app.debug(TAG, "Es gibt: " + Integer.toString(this.valueOfSkins) + " Skins.");

        if(internalDirectory.isEmpty()){
            internalDirectory = ALL_SKIN_DIRECTORYS[0];
        }

        sprite = new Sprite(new Texture(path));

    }

    public Texture getExplosionSpritesheet(){
        String path = this.getPath().substring(0, this.getPath().length() - 4) + "_explosion_spritesheet.png";
        Texture t = new Texture(path);
        return t;
    }

    public Skin(int skinID){
        this(ALL_SKIN_DIRECTORYS[skinID]);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public String getPath() {
        return path;
    }

    public int getValueOfSkins(){
        return this.valueOfSkins;
    }

    public HashMap<Integer, String> getSkinByID() {
        return skinDirectoryByID;
    }

    public Texture getSpriteSheet(){
        String path = getPath().substring(0, getPath().length() - 4) + "_spritesheet.png";
        Texture t = new Texture(path);
        return t;
    }

    public float getWidth(){
        return TILE_WIDTH;
    }

    public float getHeight(){
        return TILE_HEIGHT;
    }

    public Animation getAnimation() {
        return spaceshipAnimation;
    }

    public Animation getExplosionAnimation(){
        return explosionAnimation;
    }

    public void dispose(){
        sprite.getTexture().dispose();

        animationHandler.dispose();
    }

    public static String getCurrentSkinDirectory(){
        return currentSkinDirectory;
    }
}
