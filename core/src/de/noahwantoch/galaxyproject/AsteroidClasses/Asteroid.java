package de.noahwantoch.galaxyproject.AsteroidClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Asteroid{

    private static final String TAG = Asteroid.class.getSimpleName();

    private int id; //0 by default

    private String[] paths;
    private String currentPath;
    private Sprite sprite;

    private Random generator;

    private int maxAsteroids;

    private int rotationBeginning;
    private int currentRotationDegree;

    public Asteroid(String[] path, int maxAsteroids){
        this.maxAsteroids = maxAsteroids;
        generator = new Random();
        this.paths = path;
        currentPath = getRandomPath();
        sprite = new Sprite(new Texture(currentPath));

        rotationBeginning = generator.nextInt(360);
        this.getSprite().setRotation(rotationBeginning);
        this.getSprite().setOrigin(this.getSprite().getWidth() / 2, this.getSprite().getHeight() / 2);

        currentRotationDegree = rotationBeginning;
    }

    public void dispose(){
        sprite.getTexture().dispose();
    }

    public String getRandomPath(){
        String randomPath = "";
        int index = 0;
        try{
            index = generator.nextInt(paths.length);
            if(index > paths.length - 1){
                index -= 1;
            }
            Gdx.app.debug(TAG, "Index: " + Integer.toString(index));
            randomPath = paths[index];
        }catch (Exception e){
            e.printStackTrace();
        }
        return randomPath;
    }


    public void generateNewPosition(){
        float y = Gdx.graphics.getHeight() + (Gdx.graphics.getHeight() * (1f / maxAsteroids) * id);
//        Gdx.app.debug(TAG, "max: " + maxAsteroidsOnScreen + " id: " + id);
//        Gdx.app.debug(TAG, Float.toString(Gdx.graphics.getHeight()) + " + " + Float.toString((Gdx.graphics.getHeight() * (1f / maxAsteroidsOnScreen) * id)));

        sprite.setX(generator.nextInt(Gdx.graphics.getWidth() - 0) - 0);
        sprite.setY(y);

        this.currentPath = getRandomPath();
        sprite.getTexture().dispose();
        sprite.setTexture(new Texture(currentPath));
    }

    public int getId(){
        return this.id;
    }

    public void setId(int x){
        if(x == 0){
            x = 1;
            Gdx.app.debug(TAG, "ID darf nicht: 0 sein.");
        }
        this.id = x;
    }

    public Sprite getSprite(){
        return this.sprite;
    }

    public void setX(float value){
        this.sprite.setX(value);
    }

    public void setY(float value){
        this.sprite.setY(value);
    }

    public float getX(){
        return sprite.getX();
    }

    public float getY(){
        return sprite.getY();
    }

    public void checkCoordinates(){
        if(sprite.getY() < 0 - sprite.getHeight()){
            generateNewPosition();
        }
    }

    public void updateRotation(){
        if(currentRotationDegree >= 360){
            currentRotationDegree = 0;
        }
        currentRotationDegree++;
        this.getSprite().setRotation(currentRotationDegree);
    }

}
