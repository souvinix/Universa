package de.noahwantoch.galaxyproject.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import de.noahwantoch.galaxyproject.Helper.RandomHelper;

public class Piece {

    private ArrayList<Sprite> allSprites;
    private Sprite currentSprite;

    private Vector3 direction;

    public Piece(String[] paths){

        allSprites = new ArrayList<Sprite>();

        for(String path : paths){
            allSprites.add(new Sprite(new Texture(path)));
        }

        currentSprite = allSprites.get(RandomHelper.getRandomAxisValue(allSprites.size(), 0));

        direction = new Vector3();

    }

    public void generateRandomDirection(){
        int x = RandomHelper.getRandomOperator(RandomHelper.getRandomAxisValue(20, 0));
        int y = RandomHelper.getRandomOperator(RandomHelper.getRandomAxisValue(20, 0));

        setDirection(x, y);
    }

    public Sprite getSprite(){
        return currentSprite;
    }

    protected void setDirection(float x, float y){
        direction.set(x, y, 0);
    }

    public Vector3 getDirection(){
        return direction;
    }

    public Sprite getCurrentSprite(){
        return currentSprite;
    }

    public void dispose(){
        getSprite().getTexture().dispose();
    }

}
