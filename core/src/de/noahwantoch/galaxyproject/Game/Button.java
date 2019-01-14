package de.noahwantoch.galaxyproject.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import de.noahwantoch.galaxyproject.Helper.Touchdetector;
import de.noahwantoch.galaxyproject.Screens.AbstractScreen;
import de.noahwantoch.galaxyproject.Screens.ScreenEnum;
import de.noahwantoch.galaxyproject.Screens.ScreenHandler;

public class Button {

    private static final String TAG = Button.class.getSimpleName();

    private static final String BUTTON_PATH = "button.png"; //internal path > it's a sprite-sheet
    private static final String FONT = "retganon.ttf";
    private static final int TILESET_WIDTH = 76;
    private static final int TILESET_HEIGHT = 22;

    private boolean isDisposed = false;

    private boolean pressed = false;

    private TextureRegion[] allRegions;

    private Font textFont;

    private Vector3 position;
    private String text;
    private float size;
    private int state = 0;
    private float currentScale = 1f;

    private Touchdetector touchdetector;

    private ScreenEnum functionScreen;

    private int funcCounter;

    private boolean hasRestartScreen;

    public Button(String text, float x, float y, float size){

        position = new Vector3();

        this.text = text;
        this.size = size;

        TextureRegion[][] textureRegions = TextureRegion.split(new Texture(BUTTON_PATH), TILESET_WIDTH / 2 , TILESET_HEIGHT);
        allRegions = textureRegions[state];

        touchdetector = new Touchdetector();

        setPosition(x, y);

        textFont = new Font(FONT, getWidth() * 0.2f, 1);
        textFont.setPosition(getX() + getWidth() / 2f, getY() + getHeight() / 1.5f + Gdx.graphics.getDensity() * 3f);
        textFont.setColor(0f, 0f, 0f, 1f);

        hasRestartScreen = false;

    }

    public void draw(SpriteBatch batch, float delta){
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

                runFunction();
                funcCounter++;

            }
            pressed = false;
            state = 0;
        }
    }

    /** For example for Exitbutton*/
    public void changeTileset(String path, float widthPx, float heightPx){

        for(int i = 0; i < allRegions.length; i++){
            allRegions[i].setTexture(new Texture(path));
            allRegions[i].setRegionWidth((int) widthPx / 2);
            allRegions[i].setRegionHeight((int) heightPx);
        }

    }

    public void setFunctionRestartScreen(ScreenEnum screenEnum){
        hasRestartScreen = true;
        functionScreen = screenEnum;
    }

    public void runFunction(){
        if(funcCounter < 1){
            if(hasRestartScreen && functionScreen != null){
                ScreenHandler.getInstance().restartScreen(functionScreen);
            }

            else if(functionScreen != null){
                ScreenHandler.getInstance().showScreen(functionScreen);
            }
        }
    }

    public void setFunctionScreen(ScreenEnum screen){
        if(screen == null){
            throw new NullPointerException("Screen mustn't be null");
        }
        functionScreen = screen;
    }

    public ScreenEnum getFunctionScreen(){
        return functionScreen;
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

    public void setPosition(float x, float y){
        setX(x);
        setY(y);
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

    private boolean isDisposed(){
        return isDisposed;
    }

    public void dispose(){
        if(!isDisposed()){
            for(TextureRegion textureRegion : allRegions){
                textureRegion.getTexture().dispose();
            }
        }
    }
}
