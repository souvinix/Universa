package de.noahwantoch.galaxyproject.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class Touchdetector {

    private Vector3 position;

    private boolean isTouched = false;

    public Touchdetector(){
        position = new Vector3();
    }

    public void renderTouches(){
        if (Gdx.input.isTouched()) {
            position.set(Gdx.input.getX(), CurrentSystem.getScreenHeight() - Gdx.input.getY(), 0);
            isTouched = true;
        }else{
            isTouched = false;
        }

    }

    public boolean isTouching(Sprite sprite, float hitbox){
        if(isTouched){
            if(position.x > sprite.getX() + hitbox && position.x < sprite.getX() + sprite.getWidth() - hitbox){
                if(position.y > sprite.getY() + hitbox && position.y < sprite.getY() + sprite.getHeight() - hitbox){
                    //The left Sprite was touched ->
                    return true;
                }
            }
        }
        return false;
    }
}
