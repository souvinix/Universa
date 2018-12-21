package de.noahwantoch.galaxyproject.AsteroidClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

import de.noahwantoch.galaxyproject.Helper.CurrentSystem;

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

    //Min and max size -->
    private float minWidth;
    private float maxWidth;
    private float minHeight;
    private float maxHeight;

    public Asteroid(String[] path, int maxAsteroids){
        this.maxAsteroids = maxAsteroids;
        generator = new Random();
        this.paths = path;
        currentPath = getRandomPath();
        sprite = new Sprite(new Texture(currentPath));

        rotationBeginning = generator.nextInt(360);
        this.getSprite().setRotation(rotationBeginning);

        currentRotationDegree = rotationBeginning;

        minWidth = Gdx.graphics.getDensity() * sprite.getWidth() / 4f;
        maxWidth = minWidth * 1.5f;
        minHeight = Gdx.graphics.getDensity() * sprite.getHeight() / 4f;
        maxHeight = minHeight * 1.5f;
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

        float randomWidth = generator.nextFloat() * (maxWidth - minWidth) + minWidth;
        float randomHeight = generator.nextFloat() * (maxHeight - minHeight) + minHeight;

        sprite.setX(generator.nextInt(Gdx.graphics.getWidth() - 0) - 0);
        sprite.setY(y);

        this.currentPath = getRandomPath();
        sprite.getTexture().dispose();
        sprite.setTexture(new Texture(currentPath));

        sprite.setSize(randomWidth, randomHeight);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
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

    public float getWidth(){
        return this.getSprite().getWidth();
    }
    public float getHeight(){
        return this.getSprite().getHeight();
    }

}
