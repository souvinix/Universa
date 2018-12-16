package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Player {

    private static final String TAG = Player.class.getSimpleName();

    private Skin skin;
    private Sprite currentSkin;

    private float elapsedTime;

    public Player(int skinID){
        //be care -> maybe Array Index out of bounce ->
        skin = new Skin(0);

        currentSkin = skin.getSprite();
        currentSkin.setX(Gdx.graphics.getWidth() / 2 - currentSkin.getWidth() / 2);
        currentSkin.setY(500);


    }

    public Sprite getCurrentSkin(){
        return currentSkin;
    }

    public void renderPlayer(){
        this.elapsedTime += Gdx.graphics.getDeltaTime();
        Batch.getBatch().begin();
        Batch.getBatch().draw((TextureRegion) this.getSkin().getAnimation().getKeyFrame(elapsedTime, true), currentSkin.getX(), currentSkin.getY());
        Batch.getBatch().end();
    }

    public Skin getSkin(){
        return this.skin;
    }

    public void dispose(){
        skin.dispose();

    }
}
