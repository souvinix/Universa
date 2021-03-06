package de.noahwantoch.galaxyproject.HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import java.util.ArrayList;

import de.noahwantoch.galaxyproject.Game.Button;
import de.noahwantoch.galaxyproject.Game.Font;
import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;
import de.noahwantoch.galaxyproject.Screens.AbstractScreen;
import de.noahwantoch.galaxyproject.Screens.ScreenEnum;

public class LogoHandler {

    private static final String TAG = LogoHandler.class.getSimpleName();

    private static final String TITLE = "Universia";
    private static final String TTP_TEXT = "Tap to play";
    private static final String FONT_DATA_NAME = "retganon.ttf";

    private static final float LOGO_MOVES_SEC = 0.5f;

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
    private boolean isDisposed;

    private float logoMoveCounter;
    private boolean shouldLogoMove;

    private boolean turnAlpha = false;
    private float ttp_counter = 0;

    private Button skinsButton;
    private Button statsButton;
    private Button optionsButton;

    private static final ScreenEnum SKINS_SCREEN = ScreenEnum.SKINS_SCREEN;
    private static final ScreenEnum STATS_SCREEN = ScreenEnum.STATS_SCREEN;
    private static final ScreenEnum OPTIONS_SCREEN = ScreenEnum.OPTIONS_SCREEN;

    private ArrayList<Button> buttons;

    public LogoHandler(){
        isDisposed = false;

        buttons = new ArrayList<Button>();

        font = new Font(FONT_DATA_NAME, TITLE_SIZE, (int) (5f * Gdx.graphics.getDensity()));
        font.setPosition(CurrentSystem.getScreenWidth() / 2, CurrentSystem.getScreenHeight() * 0.8f);
        ttpFont = new Font(FONT_DATA_NAME, TTP_SIZE, (int) (5f * Gdx.graphics.getDensity()));
        ttpFont.setPosition(CurrentSystem.getScreenWidth() / 2, CurrentSystem.getScreenHeight() * 0.25f);

        skinsButton = new Button("Skins", CurrentSystem.getScreenWidth() / 2, CurrentSystem.getScreenHeight() / 2, 3f * Gdx.graphics.getDensity());
        statsButton = new Button("Statistik", CurrentSystem.getScreenWidth() / 2, CurrentSystem.getScreenHeight() / 2 + (int) skinsButton.getHeight(), 3f * Gdx.graphics.getDensity());
        optionsButton = new Button("Optionen", CurrentSystem.getScreenWidth() / 2, CurrentSystem.getScreenHeight() - (CurrentSystem.getScreenHeight() / 2 + (int) skinsButton.getHeight()), 3f * Gdx.graphics.getDensity());

        skinsButton.setFunctionScreen(SKINS_SCREEN);
        statsButton.setFunctionScreen(STATS_SCREEN);
        optionsButton.setFunctionScreen(OPTIONS_SCREEN);

        buttons.add(skinsButton);
        buttons.add(statsButton);
        buttons.add(optionsButton);

        /** Colors >>> */
        r = 1f;
        g = 1f;
        b = 1f;

        font.setColor(r, g, b, logoAlpha);
    }

    public void renderLogo(float delta){

        logoMoveCounter += delta;

        if(logoMoveCounter >= LOGO_MOVES_SEC && shouldLogoMove){
            font.setY(font.getY() - Gdx.graphics.getDensity() * 2.5f);
        }

        else if(!shouldLogoMove && logoMoveCounter >= LOGO_MOVES_SEC){
            font.setY(font.getY() + Gdx.graphics.getDensity() * 2.5f);
        }

        if(logoMoveCounter >= LOGO_MOVES_SEC){
            logoMoveCounter = 0;
            shouldLogoMove = !shouldLogoMove;
        }


        Batch.getBatch().begin();

        font.draw(Batch.getBatch(), TITLE);
        font.setColor(r, g, b, logoAlpha);
        ttpFont.draw(Batch.getBatch(), TTP_TEXT);
        ttpFont.setColor(r, g, b, ttpAlpha);

        for(Button button : buttons){
            button.draw(Batch.getBatch(), delta); //Touch-detection is intern
            button.setAlphaAndSize(logoAlpha); //If the screen around the buttons was touched
        }

        Batch.getBatch().end();

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

        if(Gdx.input.justTouched() && !skinsButton.isPressed() && !statsButton.isPressed() && !optionsButton.isPressed()){
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
