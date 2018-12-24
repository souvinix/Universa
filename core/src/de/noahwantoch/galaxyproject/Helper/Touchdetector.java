package de.noahwantoch.galaxyproject.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import de.noahwantoch.galaxyproject.Controller;

public class Touchdetector {

    private static final String TAG = Touchdetector.class.getSimpleName();

    private Vector3 position;

    private boolean isTouched = false;
    private boolean isTouched2 = false;

    public Touchdetector() {
        position = new Vector3();
    }

    public void renderTouches() {
        if (Gdx.input.isTouched()) {
            position.set(Gdx.input.getX(), CurrentSystem.getScreenHeight() - Gdx.input.getY(), 0);
            isTouched = true;
        } else if (Gdx.input.isTouched(1) || Gdx.input.isTouched(2)) {
            isTouched = false;
        } else {
            isTouched = false;
        }

        for(int i = 0; i < 3; i++){
            if(Gdx.input.isTouched(i)){
                if (Gdx.input.getX(i) > Controller.getFirebutton().getX() + Controller.getHitbox() && Gdx.input.getX(i) < Controller.getFirebutton().getX() + Controller.getFirebutton().getWidth() - Controller.getHitbox()) {
                    if (CurrentSystem.getScreenHeight() - Gdx.input.getY(i) > Controller.getFirebutton().getY() + Controller.getHitbox() &&
                            CurrentSystem.getScreenHeight() - Gdx.input.getY(i) < Controller.getFirebutton().getY() + Controller.getFirebutton().getHeight() - Controller.getHitbox()) {
                        isTouched2 = true;
                        return;
                    }
                }
            }
            isTouched2 = false;
        }
    }

    public boolean isTouching(Sprite sprite, float hitbox) {
        if (isTouched) {
            if (position.x > sprite.getX() + hitbox && position.x < sprite.getX() + sprite.getWidth() - hitbox) {
                if (position.y > sprite.getY() + hitbox && position.y < sprite.getY() + sprite.getHeight() - hitbox) {
                    //Any sprite with pointer 0 was touched
                    return true;
                }

            }
        }
        return false;
    }

    public boolean isFiring(){
        return isTouched2;
    }
}
