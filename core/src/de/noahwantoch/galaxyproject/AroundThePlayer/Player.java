package de.noahwantoch.galaxyproject.AroundThePlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.noahwantoch.galaxyproject.AsteroidClasses.Asteroid;
import de.noahwantoch.galaxyproject.AsteroidClasses.AsteroidManagement;
import de.noahwantoch.galaxyproject.BulletClasses.Bullet;
import de.noahwantoch.galaxyproject.BulletClasses.BulletHandler;
import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.Collisiondetector;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;
import de.noahwantoch.galaxyproject.HUD.Progressbar;

public class Player {

    //be care -> maybe Array Index out of bounce ->
    private static Skin skin = new Skin(0);
    private BulletHandler bulletHandler;

    private static final String TAG = Player.class.getSimpleName();

    private static final float HEIGHT_OF_SPACESHIP = 0.2f; //In percentage
    private static final int INTRO_VELOCITY = 4;

    private static final float SKIN_WIDTH = CurrentSystem.getScreenWidth() / skin.getSprite().getWidth() * 25;
    private static final float SKIN_HEIGHT = CurrentSystem.getScreenHeight() / skin.getSprite().getHeight() * 25;

    private static final float POSITION_Y = CurrentSystem.getScreenHeight() * HEIGHT_OF_SPACESHIP;

    private Sprite currentSprite;

    private float elapsedTime;

    private int currentRotation;

    private Collisiondetector collisiondetector;
    private boolean isColided = false;
    private boolean isIntroDone = false;

    private Progressbar progressbar;
    private float lifes;
    private float damage;

    public Player(){
        bulletHandler = new BulletHandler();
        collisiondetector = new Collisiondetector();
        progressbar = new Progressbar();

        currentSprite = skin.getSprite();
        currentSprite.setSize(SKIN_WIDTH, SKIN_HEIGHT);
        currentSprite.setX(Gdx.graphics.getWidth() / 2 - currentSprite.getWidth() / 2);
        currentSprite.setY(-currentSprite.getHeight());

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
        if(isIntroDone){
            progressbar.renderProgressbar(delta);
        }
        bulletHandler.renderBullets(delta);

        this.elapsedTime += Gdx.graphics.getDeltaTime();

        for(Asteroid asteroid : AsteroidManagement.getAsteroids()){

            for(Bullet bullet : bulletHandler.getCurrentBullets()){
                if(collisiondetector.checkBulletCollision(bullet, asteroid.getSprite())){
                    //Wenn eine Bullet ein Asteroid trifft
                    if(asteroid.getHitbox()){
                        asteroid.setIsExploded(true);
                        bullet.setAsteroidCollision(true);
                    }
                }
            }

            if(asteroid.getHitbox()){
                isColided = collisiondetector.checkCollision(getCurrentSkin(), asteroid.getSprite());
            }
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

    //The Animation where the spaceship goes forward
    public void renderIntroAnimation(float delta){
        if(this.getCurrentSkin().getY() < POSITION_Y){
            this.getCurrentSkin().setY(this.getCurrentSkin().getY() + INTRO_VELOCITY);
            renderPlayer(delta);
        }else if(this.getCurrentSkin().getY() >= POSITION_Y){
            this.getCurrentSkin().setY(POSITION_Y);
            isIntroDone = true;

        }
    }

    public boolean isIntroDone(){
        return isIntroDone;
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