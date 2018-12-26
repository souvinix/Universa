package de.noahwantoch.galaxyproject.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Touchdetector {

    private static final String TAG = Touchdetector.class.getSimpleName();

    private static final int MAX_TOUCHES = 3;

    public Touchdetector() {}

    public boolean isTouching(Sprite sprite, float hitbox) {
        for(int i = 0; i < MAX_TOUCHES; i++){
            if(Gdx.input.isTouched(i)){
                if (Gdx.input.getX(i) > (sprite.getX() - hitbox) && Gdx.input.getX(i) < (sprite.getX() + sprite.getWidth() + hitbox) ) {
                    if (CurrentSystem.getScreenHeight() - Gdx.input.getY(i) > (sprite.getY() - hitbox) &&
                            CurrentSystem.getScreenHeight() - Gdx.input.getY(i) < (sprite.getY() + sprite.getHeight() + hitbox)) {
                        return true;
                    }
                }
            }
        }
        return  false;
    }

}
