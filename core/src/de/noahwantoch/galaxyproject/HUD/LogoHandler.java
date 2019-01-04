package de.noahwantoch.galaxyproject.HUD;

import com.badlogic.gdx.Gdx;

import de.noahwantoch.galaxyproject.Game.Font;
import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;

public class LogoHandler {

    private static final String TAG = LogoHandler.class.getSimpleName();

    private static final String TITLE = "Universia";
    private static final String TTP_TEXT = "Tap to play";
    private static final String FONT_DATA_NAME = "retganon.ttf";

    private static final float TITLE_SIZE = 60f * Gdx.graphics.getDensity();
    private static final float TTP_SIZE = 50f * Gdx.graphics.getDensity();

    private Font font;
    private Font ttpFont;

    private float r;
    private float g;
    private float b;

    private float ttpAlpha = 1;
    private float logoAlpha = 1;
    private boolean started = false;
    private boolean isDisposed = false;

    private boolean turnAlpha = false;
    private float ttp_counter = 0;

    public LogoHandler(){
        font = new Font(FONT_DATA_NAME, TITLE_SIZE);
        font.setPosition(CurrentSystem.getScreenWidth() / 2, CurrentSystem.getScreenHeight() * 0.8f);
        ttpFont = new Font(FONT_DATA_NAME, TTP_SIZE);
        ttpFont.setPosition(CurrentSystem.getScreenWidth() / 2, CurrentSystem.getScreenHeight() * 0.3f);

        r = 1f;
        g = 1f;
        b = 1f;
        font.setColor(r, g, b, logoAlpha);
    }

    public void renderLogo(float delta){

        font.draw(Batch.getBatch(), TITLE);
        font.setColor(r, g, b, logoAlpha);
        ttpFont.draw(Batch.getBatch(), TTP_TEXT);
        ttpFont.setColor(r, g, b, ttpAlpha);

        if(started && logoAlpha != 0f){ //if taped on the screen
            if(!(logoAlpha < 0)){
                logoAlpha -= delta;
                ttpAlpha = logoAlpha;
            }
            if(logoAlpha <= 0){
                dispose();
            }
        }

        if(!isDisposed()){ //If the logo isn't disposed yet

            ttp_counter += delta;

            if(!turnAlpha){
                if(ttpAlpha > 0.3f){
                    ttpAlpha -= delta;
                }
            }else{
                ttpAlpha += delta;
            }
            if(ttp_counter > 1f){
                ttp_counter = 0f;
                turnAlpha = !turnAlpha;
            }


        }

        if(Gdx.input.justTouched()){
            started = true;
        }
    }

    public void dispose(){
        font.dispose();
        isDisposed = true;
    }

    public boolean isDisposed(){
        return isDisposed;
    }
}
