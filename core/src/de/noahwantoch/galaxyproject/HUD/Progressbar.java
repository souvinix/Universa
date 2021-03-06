package de.noahwantoch.galaxyproject.HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.font.LineMetrics;
import java.awt.geom.Line2D;

import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;

public class Progressbar {

    private static final String TAG = Progressbar.class.getSimpleName();

    private static final String PROGRESSBAR_PATH = "progressbar.png";
    private static final String PROGRESSBAR_BALKEN_PATH = "progressbar_balken.png";

    private static final float DISTANCE_TO_BORDER = 0.3f; //In percentage
    private static final float PROGRESSBAR_WIDTH = CurrentSystem.getScreenWidth() - CurrentSystem.getScreenWidth() * DISTANCE_TO_BORDER;
    private static final float PROGRESSBAR_HEIGHT = CurrentSystem.getScreenHeight() * 0.02f;
    private static final float MAX_HITS = 250f;
    private static final float PROGRESSBAR_Y = CurrentSystem.getScreenHeight() - PROGRESSBAR_HEIGHT - CurrentSystem.getScreenWidth() * (DISTANCE_TO_BORDER / 6);

    private Sprite progressbar;
    private Sprite balken;

    public Progressbar(){

        progressbar = new Sprite(new Texture(Gdx.files.internal(PROGRESSBAR_PATH)));
        balken = new Sprite(new Texture(Gdx.files.internal(PROGRESSBAR_BALKEN_PATH)));

        progressbar.setSize(PROGRESSBAR_WIDTH, PROGRESSBAR_HEIGHT);
        balken.setSize(PROGRESSBAR_WIDTH, PROGRESSBAR_HEIGHT);

        progressbar.setPosition(CurrentSystem.getScreenWidth() * (DISTANCE_TO_BORDER / 2), PROGRESSBAR_Y);
        balken.setPosition(CurrentSystem.getScreenWidth() / 2 - balken.getWidth() / 2, PROGRESSBAR_Y);

    }

    public float getDeltaDamage(){
        float x = getProgress() / getMaxHits(); //CurrentSystem.getScreenWidth() - CurrentSystem.getScreenWidth() * DISTANCE_TO_BORDER / 40
        return x;
    }

    public float getMaxHits(){
        return MAX_HITS;
    }

    public void renderProgressbar(float delta){
        Batch.getBatch().begin();

        Batch.getBatch().draw(balken, CurrentSystem.getScreenWidth() / 2 - balken.getWidth() / 2, balken.getY(), balken.getWidth(), balken.getHeight());
        Batch.getBatch().draw(progressbar, progressbar.getX(), progressbar.getY(), progressbar.getWidth(), progressbar.getHeight());

        Batch.getBatch().end();
    }

    public void set(float value){
        if(value <= 0){
            value = 0;
        }
        balken.setSize(value, balken.getHeight());
    }

    public float getProgress(){
        return balken.getWidth();
    }

}
