package de.noahwantoch.galaxyproject.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Objects;

import de.noahwantoch.galaxyproject.Game.GameHandler;

public class Touchdetector {

    private static final String TAG = Touchdetector.class.getSimpleName();

    private static final int MAX_TOUCHES = GameHandler.getMaxTouches();

    private ShapeRenderer shapeRenderer;
    private boolean isShown = false;

    public Touchdetector() {

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

    }

    public boolean isTouching(Sprite sprite, float hitbox) {
        return isTouching(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), hitbox);
    }

    public boolean isTouching(TextureRegion textureRegion, float hitbox){
        return isTouching(textureRegion.getRegionX(), textureRegion.getRegionY(), textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), hitbox);
    }

    public boolean isTouching(float x, float y, float width, float height, float hitbox){
        if(isShown){
            shapeRenderer.begin();
            shapeRenderer.rect(x - hitbox, y - hitbox, width + hitbox, height + hitbox);
            shapeRenderer.end();
        }

        for(int i = 0; i < MAX_TOUCHES; i++){
            if(Gdx.input.isTouched(i)){
                if(Gdx.input.getX(i) > x - hitbox && Gdx.input.getX(i) < x + width + hitbox){ //x
                    if(CurrentSystem.getScreenHeight() - Gdx.input.getY(i) > y - hitbox && CurrentSystem.getScreenHeight() - Gdx.input.getY(i) < y + height + hitbox){ //y
                        return true;
                    }
                }
            }
        }
        return  false;
    }

    public void show(){
        isShown = true;
    }

    public void dontShow(){
        isShown = false;
    }



}
