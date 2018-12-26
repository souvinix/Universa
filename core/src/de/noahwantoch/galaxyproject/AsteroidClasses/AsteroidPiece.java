package de.noahwantoch.galaxyproject.AsteroidClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import de.noahwantoch.galaxyproject.Helper.RandomHelper;

public class AsteroidPiece {

    private static final String TAG = AsteroidPiece.class.getSimpleName();

    private static final String[] ASTEROID_PIECES_PATHS = {"solo.png", "solo2.png", "solo3.png"};

    private ArrayList<Sprite> allSprites;
    private Sprite currentSprite;

    private Vector3 direction;


    public AsteroidPiece(){
        allSprites = new ArrayList<Sprite>();

        for(String path : ASTEROID_PIECES_PATHS){
            allSprites.add(new Sprite(new Texture(path)));
        }

        currentSprite = allSprites.get(RandomHelper.getRandomAxisValue(allSprites.size(), 0));

        direction = new Vector3();

    }

    public void generateNewDirection(){
        int x = RandomHelper.getRandomOperator(RandomHelper.getRandomAxisValue(20, 0));
        int y = RandomHelper.getRandomOperator(RandomHelper.getRandomAxisValue(20, 0));
        direction.set(x, y,0);
    }

    public static int getMaxPaths(){
        return ASTEROID_PIECES_PATHS.length;
    }

    public float getX(){
        return getSprite().getX();
    }

    public float getY(){
        return getSprite().getX();
    }

    public Vector3 getDirection(){
        return direction;
    }

    public Sprite getSprite(){
        return currentSprite;
    }

    public void dispose(){
        getSprite().getTexture().dispose();
    }
}
