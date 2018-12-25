package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;

public class Logo {

    private static final String TAG = Logo.class.getSimpleName();
    private static final String LOGO_PATH = "logo.png";
    private static final String TTP_LOGO_PATH = "tap_to_start.png";

    private Sprite logo;
    private Sprite ttp_logo;

    private float logoAlpha = 1;
    private boolean started = false;
    private boolean isDisposed = false;

    private float ttp_counter;

    public Logo(){
        logo = new Sprite(new Texture(LOGO_PATH));
        ttp_logo = new Sprite(new Texture(TTP_LOGO_PATH));

        logo.setSize(CurrentSystem.getScreenWidth() * 0.9f, CurrentSystem.getScreenHeight() * 0.1f);
        logo.setPosition(CurrentSystem.getScreenWidth() / 2 - logo.getWidth() / 2, CurrentSystem.getScreenHeight() * 0.7f);
        ttp_logo.setSize(CurrentSystem.getScreenWidth() * 0.7f, CurrentSystem.getScreenHeight() * 0.04f);
        ttp_logo.setPosition(CurrentSystem.getScreenWidth() / 2 - ttp_logo.getWidth() / 2, CurrentSystem.getScreenHeight() / 2 - ttp_logo.getHeight() / 2);
    }

    public void renderLogo(float delta){
        if(started && logoAlpha != 0f){
            if(!(logoAlpha < 0)){
                logoAlpha -= 0.01f;
            }
            if(logoAlpha <= 0){
                dispose();
            }
        }

        if(!isDisposed()){
            ttp_counter += delta;

            Batch.getBatch().begin();
            logo.draw(Batch.getBatch());
            if(ttp_counter > 0.3f){
                ttp_logo.draw(Batch.getBatch());
            }
            if(ttp_counter > 1f){
                ttp_counter = 0;
            }
            Batch.getBatch().end();
            logo.setAlpha(logoAlpha);
            ttp_logo.setAlpha(logoAlpha);
        }

        if(Gdx.input.justTouched()){
            started = true;
        }
    }

    public void dispose(){
        logo.getTexture().dispose();
        ttp_logo.getTexture().dispose();
        isDisposed = true;
    }

    public boolean isDisposed(){
        return isDisposed;
    }

}
