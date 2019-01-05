package de.noahwantoch.galaxyproject.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import de.noahwantoch.galaxyproject.Helper.Touchdetector;

public class Button {

    private static final String TAG = Button.class.getSimpleName();

    private static final String BUTTON_PATH = "button.png"; //internal path > it's a sprite-sheet
    private static final String FONT = "retganon.ttf";
    private static final int TILESET_WIDTH = 76;
    private static final int TILESET_HEIGHT = 22;

    private boolean pressed = false;
    private boolean isWindowChanged = false;

    private TextureRegion[] allRegions;

    private Font textFont;

    private Vector3 position;
    private String text;
    private float size;
    private int state = 0;
    private float currentScale = 1f;

    private Touchdetector touchdetector;

    private Screen screen;

    public Button(String text, float x, float y, float size){

        position = new Vector3();

        this.text = text;
        this.size = size;

        TextureRegion[][] textureRegions = TextureRegion.split(new Texture(BUTTON_PATH), TILESET_WIDTH / 2 , TILESET_HEIGHT);
        allRegions = textureRegions[state];

        touchdetector = new Touchdetector();

        setX(x);
        setY(y);

        textFont = new Font(FONT, getWidth() * 0.2f, 1);
        textFont.setPosition(getX() + getWidth() / 2f, getY() + getHeight() / 1.5f + Gdx.graphics.getDensity() * 3f);
        textFont.setColor(0f, 0f, 0f, 1f);

    }

    public void renderButton(SpriteBatch batch, float delta){
        checkPressed();

        batch.draw(allRegions[state], getX(), getY(),getWidth() / 2 ,getHeight() / 2, getWidth(), getHeight(), getCurrentScale() ,getCurrentScale(), 0);

        textFont.draw(batch, text);

    }

    private void checkPressed(){
        if(touchdetector.isTouching(getX(), getY(), getWidth(), getHeight(), 0)){
            if(!pressed){
                textFont.setY(textFont.getY() - Gdx.graphics.getDensity() * 2);
            }
            pressed = true;
            state = 1;

        }else{
            if(pressed){
                textFont.setY(textFont.getY() + Gdx.graphics.getDensity() * 2);

            }
            pressed = false;
            state = 0;
        }
    }

    /** For example for Exitbutton*/
    public void changeTileset(String path, float widthPx, float heightPx){

        for(int i = 0; i < allRegions.length; i++){
            allRegions[i].setTexture(new Texture(path));
            allRegions[i].setRegionWidth((int) widthPx / 2 * (i + 1));
            allRegions[i].setRegionHeight((int) heightPx);
        }

    }

    public void setScreen(Screen screen){
        this.screen = screen;
    }

    private float getCurrentScale(){
        return currentScale;
    }

    private void setCurrentScale(float value){
        currentScale = value;
    }

    public void setAlphaAndSize(float alpha){
        textFont.setColor(0f, 0f, 0f, alpha);
        textFont.setSize(textFont.getSize() - 10f);
        setCurrentScale(alpha);
    }

    public float getWidth(){
        return allRegions[state].getRegionWidth() * size;
    }

    public float getHeight(){
        return allRegions[state].getRegionHeight() * size;
    }

    public boolean isPressed(){
        return pressed;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public void setX(float value){
        position.x = value - getWidth() / 2;
    }

    public void setY(float value){
        position.y = value - getHeight() / 2;
    }

    public float getSize(){
        return size;
    }

    public String getText(){
        return text;
    }

    public void setText(String newText){
        text = newText;
    }

    public void dispose(){
        for(TextureRegion textureRegion : allRegions){
            textureRegion.getTexture().dispose();
        }
    }
}
