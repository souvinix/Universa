package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Batch{
    private static SpriteBatch batch = new SpriteBatch();

    private Batch(){

    }

    public static SpriteBatch getBatch(){
        return batch;
    }

}
