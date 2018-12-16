package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Background {

    private static final String TAG = Background.class.getSimpleName();

    private Texture bg;
    private Texture sterne1;
    private Texture sterne2;
    private Texture sterne3;

    private static final int VELOCITY = 3;

    private static float breite = Gdx.graphics.getWidth();
    private static float hoehe = Gdx.graphics.getHeight();

    private float sterne1Y1 = 0;
    private float sterne1Y2 = 0 - hoehe;

    private float sterne2Y1 = 0;
    private float sterne2Y2 = 0 - hoehe;

    private float sterne3Y1 = 0;
    private float sterne3Y2 = 0 - hoehe;

    public Background(){
        bg = new Texture("Hintergrund.png");
        sterne1 = new Texture("sterne.png");
        sterne2 = new Texture("sterne2.png");
        sterne3 = new Texture("sterne3.png");
    }

    public void renderBackground(float delta){
        Batch.getBatch().begin();
        Batch.getBatch().draw(bg, 0, 0, breite, hoehe);

        //Stars far away (little stars) ->
        //--------------------------------------------
        if(sterne3Y1 < 0 - hoehe){
            sterne3Y1 = hoehe;
        }
        if(sterne3Y2 < 0 - hoehe){
            sterne3Y2 = hoehe;
        }
        sterne3Y1-= VELOCITY;
        sterne3Y2 -= VELOCITY;

        Batch.getBatch().draw(sterne3, 0 , sterne3Y1 , breite, hoehe);
        Batch.getBatch().draw(sterne3, 0 , sterne3Y2 , breite, hoehe);
        //--------------------------------------------
        //Stars with the middle velocity (middle stars) ->
        //--------------------------------------------
        if(sterne2Y1 < 0 - hoehe){
            sterne2Y1 = hoehe;
        }
        if(sterne2Y2 < 0 - hoehe){
            sterne2Y2 = hoehe;
        }
        sterne2Y1-= VELOCITY * 2;
        sterne2Y2 -= VELOCITY * 2;

        Batch.getBatch().draw(sterne2, 0 , sterne2Y1 , breite, hoehe);
        Batch.getBatch().draw(sterne2, 0 , sterne2Y2 , breite, hoehe);
        //--------------------------------------------
        //foreground-stars (big stars) ->
        //--------------------------------------------
        if(sterne1Y1 < 0 - hoehe){
            sterne1Y1 = hoehe;
        }
        if(sterne1Y2 < 0 - hoehe){
            sterne1Y2 = hoehe;
        }
        sterne1Y1-= VELOCITY * 3;
        sterne1Y2 -= VELOCITY * 3;

        Batch.getBatch().draw(sterne1, 0 , sterne1Y1 , breite, hoehe);
        Batch.getBatch().draw(sterne1, 0 , sterne1Y2 , breite, hoehe);
        //--------------------------------------------


        Batch.getBatch().end();
    }

    public void dispose(){
        sterne1.dispose();
        sterne2.dispose();
        sterne3.dispose();
        bg.dispose();
    }
}
