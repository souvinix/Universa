package de.noahwantoch.galaxyproject.AsteroidClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Random;

import de.noahwantoch.galaxyproject.Batch;

public class AsteroidManagement {

    private static final String TAG = AsteroidManagement.class.getSimpleName();

    //Important variables! =>
    private static final float VELOCITY = 15;
    private static final int MAX_ASTEROIDS = 5;
    private static final String[] PATHS = {"asteroid.png", "asteroid2.png"};

    private ArrayList<Asteroid> asteroids;
    private boolean isStarted = false;

    public AsteroidManagement() {
        asteroids = new ArrayList<Asteroid>(MAX_ASTEROIDS);

        int id = 0;

        for(int i = 0; i < MAX_ASTEROIDS; i++){
            Asteroid tmp = new Asteroid(PATHS, MAX_ASTEROIDS); //Empty because the path needs to set later >>>
            id++;
            tmp.setId(id);
            tmp.generateNewPosition();
            asteroids.add(tmp);
        }

    }
    public void render(float delta){

        Batch.getBatch().begin();

        if(isStarted){

            for(Asteroid asteroid : asteroids){
                asteroid.getSprite().draw(Batch.getBatch());

                asteroid.setY(asteroid.getY() - VELOCITY);
                asteroid.checkCoordinates();
                asteroid.updateRotation();
            }

        }

        Batch.getBatch().end();
    }

    private int getRandomIndex(int length){
        Random random = new Random();
        int i = random.nextInt(length) - 1;
        return i;
    }

    public void start(){
        isStarted = true;
    }

    public void end(){
        isStarted = false;
    }

    public void dispose(){
        for(Asteroid asteroid : asteroids){
            asteroid.dispose();
        }
    }

}