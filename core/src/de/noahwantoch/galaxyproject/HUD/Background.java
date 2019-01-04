package de.noahwantoch.galaxyproject.HUD;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;
import de.noahwantoch.galaxyproject.Game.GameHandler;

public class Background {

    private static final String TAG = Background.class.getSimpleName();
    private static final String BG_PATH = "Hintergrund.png";
    private static final String STARS_AHEAD_PATH = "sterne.png";
    private static final String STARS_CENTER_PATH = "sterne2.png";
    private static final String STARS_BEHIND_PATH = "sterne3.png";

    private static final int MAX_STARS_OBJECTS = 2; //should'nt be 1 | pre-creating the stars, because then it looks timeless
    private float velocity = 3;

    private static Sprite bg = new Sprite(new Texture(BG_PATH));
    private static ArrayList<Sprite> starsAhead = new ArrayList<Sprite>(MAX_STARS_OBJECTS);
    private static ArrayList<Sprite> starsCenter = new ArrayList<Sprite>(MAX_STARS_OBJECTS);
    private static ArrayList<Sprite> starsBehind = new ArrayList<Sprite>(MAX_STARS_OBJECTS);

    public Background() {
        for (int i = 0; i < MAX_STARS_OBJECTS; i++) {
            starsAhead.add(new Sprite(new Texture(STARS_AHEAD_PATH)));
            starsCenter.add(new Sprite(new Texture(STARS_CENTER_PATH)));
            starsBehind.add(new Sprite(new Texture(STARS_BEHIND_PATH)));

            starsAhead.get(i).setSize(CurrentSystem.getScreenWidth(), CurrentSystem.getScreenHeight());
            starsCenter.get(i).setSize(CurrentSystem.getScreenWidth(), CurrentSystem.getScreenHeight());
            starsBehind.get(i).setSize(CurrentSystem.getScreenWidth(), CurrentSystem.getScreenHeight());

            if(i == 0){
                starsAhead.get(i).setPosition(0, 0);
                starsCenter.get(i).setPosition(0, 0);
                starsBehind.get(i).setPosition(0, 0);
            }
            else{
                starsAhead.get(i).setPosition(0, CurrentSystem.getScreenHeight());
                starsCenter.get(i).setPosition(0, CurrentSystem.getScreenHeight());
                starsBehind.get(i).setPosition(0, CurrentSystem.getScreenHeight());
            }

        }
    }

    public void renderBackground(float delta){
        if(GameHandler.isGameOver()){
            if(!(velocity <= 0)){
                velocity -= delta;
            }else{
                velocity = 0;
            }
        }

        Batch.getBatch().begin();

        Batch.getBatch().draw(bg, 0, 0, CurrentSystem.getScreenWidth(), CurrentSystem.getScreenHeight()); //The camera zooms in

        for(Sprite s : starsAhead){
            if(s.getY() < - CurrentSystem.getScreenHeight()){
                s.setY(CurrentSystem.getScreenHeight());
            }else{
                s.setY(s.getY() - velocity * 3);
            }
            Batch.getBatch().draw(s, 0,s.getY(),CurrentSystem.getScreenWidth(), CurrentSystem.getScreenHeight());
        }

        for(Sprite s : starsCenter){
            if(s.getY() < -CurrentSystem.getScreenHeight()){
                s.setY(CurrentSystem.getScreenHeight());
            }else{
                s.setY(s.getY() - velocity * 2);
            }
            Batch.getBatch().draw(s, 0,s.getY(),CurrentSystem.getScreenWidth(), CurrentSystem.getScreenHeight());
        }

        for(Sprite s : starsBehind){
            if(s.getY() < -CurrentSystem.getScreenHeight()){
                s.setY(CurrentSystem.getScreenHeight());
            }else{
                s.setY(s.getY() - velocity);
            }
            Batch.getBatch().draw(s, 0,s.getY(),CurrentSystem.getScreenWidth(), CurrentSystem.getScreenHeight());
        }

        Batch.getBatch().end();
    }

    public static float getBackgroundWidth() {
        return bg.getWidth();
    }

    public static float getBackgroundHeight() {
        return bg.getHeight();
    }

    public void dispose() {
        for (int i = 0; i < MAX_STARS_OBJECTS; i++) {
            starsAhead.get(i).getTexture().dispose();
            starsCenter.get(i).getTexture().dispose();
            starsBehind.get(i).getTexture().dispose();
        }
        bg.getTexture().dispose();
    }

    public ArrayList<Texture> getTextures(){ //later for AssetManager to load
        ArrayList<Texture> textures = new ArrayList<Texture>();
        for (int i = 0; i < MAX_STARS_OBJECTS; i++) {
            textures.add(starsAhead.get(i).getTexture());
            textures.add(starsCenter.get(i).getTexture());
            textures.add(starsBehind.get(i).getTexture());
        }
        textures.add(bg.getTexture());
        return textures;
    }
}