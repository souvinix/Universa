package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Skin {

    private static final String TAG = Skin.class.getSimpleName();

    private int valueOfSkins = ALL_SKIN_PATHS.length;

    private String path;
    private Texture texture;
    private Sprite sprite;

    private HashMap<Integer, String> skinPathByID;
    private static final String[] ALL_SKIN_PATHS = {"spaceship.png"};

    private TextureRegion[] animationFrames;
    private Animation animation;

    public Skin(String internalPath) {
        skinPathByID = new HashMap<Integer, String>();

        for(String x : ALL_SKIN_PATHS){
            skinPathByID.put(this.valueOfSkins, x);
        }

        Gdx.app.debug(TAG, "Es gibt: " + Integer.toString(this.valueOfSkins) + " Skins.");

        if(internalPath.isEmpty()){
            internalPath = ALL_SKIN_PATHS[0];
        }

        this.path = internalPath;

        try{

            this.texture = new Texture(this.path);
            this.sprite = new Sprite(texture);

        }catch(Exception e){

            Gdx.app.debug(TAG, "Exception");
            e.printStackTrace();

        }

        TextureRegion[][] tempFrames = TextureRegion.split(this.getSpriteSheet(), 189, 294);
        animationFrames = new TextureRegion[4];

        int index = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                animationFrames[index++] = tempFrames[j][i];
            }
        }

        animation = new Animation(1f/5f, animationFrames);

    }
    public Skin(int skinID){
        this(ALL_SKIN_PATHS[skinID]);
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
        return skinPathByID;
    }

    public Texture getSpriteSheet(){
        path = this.getPath().substring(0, this.getPath().length() - 4) + "_spritesheet.png";
        Texture t = new Texture(path);
        return t;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void dispose(){
        texture.dispose();
    }
}
