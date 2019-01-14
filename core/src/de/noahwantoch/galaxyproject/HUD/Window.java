package de.noahwantoch.galaxyproject.HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.noahwantoch.galaxyproject.Helper.CurrentSystem;
import de.noahwantoch.galaxyproject.Helper.Touchdetector;
import de.noahwantoch.galaxyproject.Windows.Exitbutton;

public class Window{

    private static final String TAG = Window.class.getSimpleName();

    private static final String WINDOW_DATA_NAME = "window.png";

    private static final float WIDTH = CurrentSystem.getScreenWidth() * 0.75f;
    private static final float HEIGHT = CurrentSystem.getScreenHeight() * 0.75f;

    private boolean isDisposed;
    private boolean focus;

    private Sprite window;
    private Touchdetector touchdetector;

    private Exitbutton exitbutton;

    public Window(){
        setDisposed(false);
        setFocus(true);

        touchdetector = new Touchdetector();
        touchdetector.show();

        window = new Sprite(new Texture(WINDOW_DATA_NAME));
        window.setSize(WIDTH, HEIGHT);
        window.setPosition(CurrentSystem.getScreenWidth() / 2 - WIDTH / 2, CurrentSystem.getScreenHeight() / 2 - HEIGHT / 2);
        window.setOrigin(WIDTH / 2, HEIGHT / 2);

        exitbutton = new Exitbutton(getX() - getWidth() * 0.25f, getY() + getHeight() - (getHeight() * 0.05f), 2f * Gdx.graphics.getDensity());
    }

    public void renderWindow(SpriteBatch batch, float delta){
        if(!isDisposed() && focus){

            window.draw(batch);
            exitbutton.draw(batch, delta);

        }

        updateFocus();
    }

    private void updateFocus(){

    }

    public float getWidth(){
        return window.getWidth();
    }

    public float getHeight(){
        return window.getHeight();
    }

    public float getX(){
        return window.getX();
    }

    public float getY(){
        return window.getY();
    }

    public void setX(float value){
        window.setX(value);
    }

    public void setY(float value){
        window.setY(value);
    }

    public boolean isDisposed(){
        return isDisposed;
    }

    public boolean getFocus(){
        return focus;
    }

    public void setFocus(boolean value){
        focus = value;
    }

    public void dispose(){
        window.getTexture().dispose();
        setDisposed(true);
    }

    private void setDisposed(boolean value){
        isDisposed = value;
    }

}
