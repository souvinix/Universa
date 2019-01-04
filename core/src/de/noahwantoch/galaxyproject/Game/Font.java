package de.noahwantoch.galaxyproject.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

public class Font {

    private static final String TAG = Font.class.getSimpleName();
    private static final String FONT_DIRECTORY = "Fonts/";

    private Vector3 position;

    private float size;
    private FileHandle fontFile;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    private BitmapFont textFont;

    private boolean isDisposed = false;

    public Font(String fontDataName, float size){
        this.size = size;
        position = new Vector3();

        fontFile = Gdx.files.internal(FONT_DIRECTORY + fontDataName);
        generator = new FreeTypeFontGenerator(fontFile);
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) size;
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;
        parameter.shadowOffsetY = (int) (5f * Gdx.graphics.getDensity());

        textFont = generator.generateFont(parameter);

        generator.dispose();
    }

    public void draw(SpriteBatch batch, String text){
        batch.begin();

        if(!isDisposed()){
            textFont.draw(batch, text, position.x - size / 2, position.y, 1, text.length(), size, 1, false, text);
        }

        batch.end();
    }

    public void setX(float x){
        position.x = x;
    }

    public void setY(float y){
        position.y = y;
    }

    public void setPosition(float x, float y){
        position.set(x, y,0);
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public void setColor(float r, float g, float b, float a){
        textFont.setColor(r, g, b, a);
    }

    public void dispose(){
        if(!isDisposed()){
            textFont.dispose();

            isDisposed = true;
        }
    }

    public boolean isDisposed(){
        return isDisposed;
    }
}
