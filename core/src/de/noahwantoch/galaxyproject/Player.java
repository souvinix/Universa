package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.noahwantoch.galaxyproject.AsteroidClasses.Asteroid;
import de.noahwantoch.galaxyproject.AsteroidClasses.AsteroidManagement;
import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.Collisiondetector;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;

public class Player {

    //be care -> maybe Array Index out of bounce ->
    private static Skin skin = new Skin(0);
    private BulletHandler bulletHandler;

    private static final String TAG = Player.class.getSimpleName();

    private static final float HEIGHT_OF_SPACESHIP = 0.2f; //In percentage

    private static final float SKIN_WIDTH = CurrentSystem.getScreenWidth() / skin.getSprite().getWidth() * 25;
    private static final float SKIN_HEIGHT = CurrentSystem.getScreenHeight() / skin.getSprite().getHeight() * 25;

    private static final float POSITION_Y = CurrentSystem.getScreenHeight() * HEIGHT_OF_SPACESHIP;

    private Sprite currentSprite;

    private float elapsedTime;

    private int currentRotation;

    private Collisiondetector collisiondetector;
    private boolean isColided = false;

    private Progressbar progressbar;
    private float lifes;
    private float damage;

    public Player(int skinID){
        bulletHandler = new BulletHandler();
        collisiondetector = new Collisiondetector();
        progressbar = new Progressbar();

        currentSprite = skin.getSprite();
        currentSprite.setSize(SKIN_WIDTH, SKIN_HEIGHT);
        currentSprite.setX(Gdx.graphics.getWidth() / 2 - currentSprite.getWidth() / 2);
        currentSprite.setY(POSITION_Y);

        damage = progressbar.getDeltaDamage();
        lifes = damage * progressbar.getMaxHits(); //Full Life >> after 40(= maxhits) hits --> dead
    }

    public void fire(){
        bulletHandler.drop();
    }

    public Sprite getCurrentSkin(){
        return currentSprite;
    }

    public void renderPlayer(float delta){
        progressbar.renderProgressbar(delta);
        bulletHandler.renderBullets(delta);

        this.elapsedTime += Gdx.graphics.getDeltaTime();

        for(Asteroid asteroid : AsteroidManagement.getAsteroids()){

            for(Bullet bullet : bulletHandler.getCurrentBullets()){
                if(collisiondetector.checkBulletCollision(bullet, asteroid.getSprite())){
                    //Wenn eine Bullet ein Asteroid trifft
                    asteroid.generateNewPosition();
                    bullet.setAsteroidCollision(true);
                }
            }

            isColided = collisiondetector.checkCollision(getCurrentSkin(), asteroid.getSprite());
            if(isColided){
                lifes -= damage;
                progressbar.set(lifes);
            }
        }

        Batch.getBatch().begin();

        Batch.getBatch().draw((TextureRegion) this.getSkin().getAnimation().getKeyFrame(elapsedTime, true),
                currentSprite.getX(), currentSprite.getY(), currentSprite.getWidth() / 2, currentSprite.getHeight() / 2,
                currentSprite.getWidth(), currentSprite.getHeight(), 1, 1, getCurrentRotation());

        Batch.getBatch().end();
    }

    public void setCurrentRotation(int rotation){
        currentRotation = rotation;
    }

    private int getCurrentRotation(){
        return currentRotation;
    }

    public static Skin getSkin(){
        return skin;
    }

    public void dispose(){
        skin.dispose();
    }

}