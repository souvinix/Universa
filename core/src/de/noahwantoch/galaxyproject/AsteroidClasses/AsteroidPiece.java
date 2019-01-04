package de.noahwantoch.galaxyproject.AsteroidClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import de.noahwantoch.galaxyproject.Game.Piece;
import de.noahwantoch.galaxyproject.Helper.RandomHelper;

public class AsteroidPiece extends Piece{

    private static final String[] ASTEROID_PIECES_PATHS = {"solo.png", "solo2.png", "solo3.png"};

    public AsteroidPiece(){
        super(ASTEROID_PIECES_PATHS);
    }

    public float getX(){
        return getSprite().getX();
    }

    public float getY(){
        return getSprite().getX();
    }

    public Vector3 getDirection(){
        return super.getDirection();
    }

    public Sprite getSprite(){
        return super.getCurrentSprite();
    }

    public void dispose(){
        getSprite().getTexture().dispose();
    }
}
