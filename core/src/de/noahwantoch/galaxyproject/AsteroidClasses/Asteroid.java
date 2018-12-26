package de.noahwantoch.galaxyproject.AsteroidClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.noahwantoch.galaxyproject.Helper.CurrentSystem;
import de.noahwantoch.galaxyproject.Helper.RandomHelper;

public class Asteroid{

    private static final String TAG = Asteroid.class.getSimpleName();

    private int id; //0 by default

    private String[] paths;
    private String currentPath;
    private Sprite sprite;

    private int maxAsteroids;

    private int rotationBeginning;
    private int currentRotationDegree;

    //Min and max size -->
    private float minWidth;
    private float maxWidth;
    private float minHeight;
    private float maxHeight;

    private boolean isExploded = false;
    private static final int EXPLOSION_DURATION = 5;
    private float counter;
    private boolean hitbox = true;

    //Used for handling asteroid-pieces, which spawns after explosion
    private AsteroidPieceHandler asteroidPieceHandler;

    public Asteroid(String[] path, int maxAsteroids){
        asteroidPieceHandler = new AsteroidPieceHandler();

        this.maxAsteroids = maxAsteroids;
        this.paths = path;
        currentPath = getRandomPath();
        sprite = new Sprite(new Texture(currentPath));

        rotationBeginning = RandomHelper.getRandomAxisValue(360, 0);
        this.getSprite().setRotation(rotationBeginning);

        currentRotationDegree = rotationBeginning;

        minWidth = Gdx.graphics.getDensity() * sprite.getWidth() / 4f;
        maxWidth = minWidth * 1.5f;
        minHeight = Gdx.graphics.getDensity() * sprite.getHeight() / 4f;
        maxHeight = minHeight * 1.5f;
    }

    public boolean isExploded(){
        return isExploded;
    }

    public void setIsExploded(boolean value){
        counter = 0;
        isExploded = value;
        if(value){
            asteroidPieceHandler.spawn(this);
        }else{
            asteroidPieceHandler.dispose();
        }
    }

    public void renderExplosion(float delta){
        if(isExploded()){
            counter += delta;
            setHitbox(false);
            if(counter < EXPLOSION_DURATION){

                asteroidPieceHandler.renderAsteroidPieces(delta);

            }else{
                setIsExploded(false);
            }

        }
    }

    public void dispose(){
        sprite.getTexture().dispose();
    }

    public String getRandomPath(){
        String randomPath = "";
        int index = 0;
        try{
            index = RandomHelper.getRandomAxisValue(paths.length, 0);
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
        setHitbox(true);
        float y = Gdx.graphics.getHeight() + (Gdx.graphics.getHeight() * (1f / maxAsteroids) * id);

        float randomWidth = RandomHelper.getRandomAxisValue(maxWidth, minWidth);
        float randomHeight = RandomHelper.getRandomAxisValue(maxHeight,  minHeight);

        sprite.setX(RandomHelper.getRandomAxisValue(CurrentSystem.getScreenWidth(), 0));
        sprite.setY(y);

        this.currentPath = getRandomPath();
        sprite.setTexture(new Texture(currentPath));

        sprite.setSize(randomWidth, randomHeight);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        isExploded = false;
        counter = 0;
    }

    public void setHitbox(boolean value){
        hitbox = value;
    }

    public boolean getHitbox(){
        return hitbox;
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
