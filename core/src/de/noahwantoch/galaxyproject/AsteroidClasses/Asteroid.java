package de.noahwantoch.galaxyproject.AsteroidClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.noahwantoch.galaxyproject.AroundThePlayer.Skin;
import de.noahwantoch.galaxyproject.Helper.AnimationHandler;
import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;
import de.noahwantoch.galaxyproject.Helper.RandomHelper;

public class Asteroid{

    private static final String TAG = Asteroid.class.getSimpleName();

    /** ALL attributes about the explosion sprite-sheet */
    private static final String EXPLOSION_FILE = "asteroid_explosion.png";
    private static final int EXPLOSION_FILE_WIDTH = 64;
    private static final int EXPLOSION_FILE_HEIGHT = 64;
    private static final float EXPLOSION_FILE_SIZE_MULTIPLIER = 2f * Gdx.graphics.getDensity();
    private static final float EXPLOSION_FILE_FRAME_DURATION = 1f/32f;
    private static final int EXPLOSION_SPRITESHEET_COLUMN_WIDTH = 4;
    private static final int EXPLOSION_SPRITESHEET_COLUMN_HEIGHT = 2;

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

    private Animation currentAnimation;
    private AnimationHandler animationHandler;

    //Used for handling asteroid-pieces, which spawns after explosion
    private AsteroidPieceHandler pieceHandler;

    public Asteroid(String[] path, int maxAsteroids){
        pieceHandler = new AsteroidPieceHandler();

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

        animationHandler = new AnimationHandler();
        currentAnimation = getCurrentAnimation();
    }

    public boolean isExploded(){
        return isExploded;
    }

    public void setIsExploded(boolean value){
        counter = 0;
        isExploded = value;
        if(value){
            pieceHandler.spawn(this);
        }else{
            pieceHandler.dispose();
        }
    }

    public void renderExplosion(float delta){
        if(isExploded()){

            counter += delta;

            //Every spaceship has a own asteroid-explosion =>
            if(!currentAnimation.isAnimationFinished(counter)){
                Batch.getBatch().draw((TextureRegion) currentAnimation.getKeyFrame(counter, false),
                        getX() + getWidth() / 2 - EXPLOSION_FILE_WIDTH / 2, getY() + getHeight() / 2 - EXPLOSION_FILE_HEIGHT / 2
                        , EXPLOSION_FILE_WIDTH / 2, EXPLOSION_FILE_HEIGHT / 2,
                        EXPLOSION_FILE_WIDTH, EXPLOSION_FILE_HEIGHT, EXPLOSION_FILE_SIZE_MULTIPLIER,
                        EXPLOSION_FILE_SIZE_MULTIPLIER, 0);
            }

            setHitbox(false);
            if(counter < EXPLOSION_DURATION){

                pieceHandler.renderAsteroidPieces(delta);

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

    public Animation getCurrentAnimation(){
        return animationHandler.generateNotSymmetricAnimation(Skin.getCurrentSkinDirectory() +
                EXPLOSION_FILE, EXPLOSION_FILE_WIDTH, EXPLOSION_FILE_HEIGHT, EXPLOSION_FILE_FRAME_DURATION,
                EXPLOSION_SPRITESHEET_COLUMN_WIDTH, EXPLOSION_SPRITESHEET_COLUMN_HEIGHT);
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
