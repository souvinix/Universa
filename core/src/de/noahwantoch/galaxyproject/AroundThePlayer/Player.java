package de.noahwantoch.galaxyproject.AroundThePlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import de.noahwantoch.galaxyproject.AsteroidClasses.Asteroid;
import de.noahwantoch.galaxyproject.AsteroidClasses.AsteroidManagement;
import de.noahwantoch.galaxyproject.BulletClasses.Bullet;
import de.noahwantoch.galaxyproject.BulletClasses.BulletHandler;
import de.noahwantoch.galaxyproject.ParticleClass.ParticleHandler;
import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.Collisiondetector;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;
import de.noahwantoch.galaxyproject.HUD.Progressbar;
import de.noahwantoch.galaxyproject.Game.GameHandler;
import de.noahwantoch.galaxyproject.ParticleClass.ParticleDirection;

public class Player {

    //be care -> maybe Array Index out of bounce ->
    private static Skin skin;
    private BulletHandler bulletHandler;

    private static final String TAG = Player.class.getSimpleName();

    private static final float HEIGHT_OF_SPACESHIP = 0.2f; //In percentage
    private static final int INTRO_VELOCITY = 4;

    private float skinWidth;
    private float skinHeight;

    private static final float EXPLOSION_SIZE = 2f;

    private static final float POSITION_Y = CurrentSystem.getScreenHeight() * HEIGHT_OF_SPACESHIP;

    private Sprite currentSprite;

    private float elapsedTime;
    private float elapsedExplosionTime;

    private int currentRotation;

    private Collisiondetector collisiondetector;
    private boolean isColided;

    private Progressbar progressbar;
    private float lifes;
    private float damage;

    private PlayerPieceHandler playerPieceHandler;
    private ParticleHandler particleHandler;
    private Rectangle rectangle;

    public Player(){
        skin = new Skin(0);
        skinWidth  = CurrentSystem.getScreenWidth() / skin.getWidth() * 25;
        skinHeight = CurrentSystem.getScreenHeight() / skin.getHeight() * 30;

        playerPieceHandler = new PlayerPieceHandler();

        collisiondetector = new Collisiondetector();
        progressbar = new Progressbar();

        currentSprite = skin.getSprite();
        currentSprite.setSize(skinWidth, skinHeight);
        currentSprite.setX(Gdx.graphics.getWidth() / 2 - currentSprite.getWidth() / 2);
        currentSprite.setY(-currentSprite.getHeight());

        damage = progressbar.getDeltaDamage();
        lifes = damage * progressbar.getMaxHits(); //Full Life >> after 40(= maxhits) hits --> dead

        rectangle = new Rectangle();
        rectangle.width = getSkin().getWidth() / 3;
        rectangle.height = getSkin().getHeight() * 2;

        particleHandler = new ParticleHandler("particle.png", rectangle, ParticleDirection.DOWN);
        particleHandler.GenerateParticles(5);
        particleHandler.setVelocity(80f);
        particleHandler.setDirection(ParticleDirection.DOWN);
        particleHandler.setParticleSize(1.5f);
        particleHandler.setColor(0.8f, 0.5f, 0.2f);
        particleHandler.setAlpha(0.5f);
        rectangle.x = getSkin().getSprite().getX() + getSkin().getWidth() / 2 - rectangle.width / 2;

        bulletHandler = new BulletHandler(getSkin().getCurrentSkinDirectory() + "bullets.png", getSkin().getSprite().getWidth(), getSkin().getSprite().getHeight() * 0.3f);
    }

    public void fire(){
        bulletHandler.drop(getSkin().getSprite().getX(), getSkin().getSprite().getY() + getSkin().getSprite().getHeight());
    }

    public Sprite getCurrentSkin(){
        return currentSprite;
    }

    public void renderPlayer(float delta){
        if(GameHandler.isIntroDone()){
            progressbar.renderProgressbar(delta);
        }
        bulletHandler.renderBullets(delta);

        particleHandler.render(Batch.getBatch(), delta);
        rectangle.x = getSkin().getSprite().getX() + getSkin().getWidth() / 2 - rectangle.width / 2;
        rectangle.y = getSkin().getSprite().getY() - rectangle.height;

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

            //If the spaceship hit an asteroid
            if(isColided){
                lifes -= damage;
                progressbar.set(lifes);
                if(lifes <= 0){
                    GameHandler.setGameOver(true);
                }
            }
        }

        Batch.getBatch().begin();

        if(GameHandler.isGameOver()){ //If the game is over

            particleHandler.setMoving(false);

            if(!GameHandler.isAnimationFinished()){ //if the explosion isn't finished

                if(!getSkin().getExplosionAnimation().isAnimationFinished(elapsedExplosionTime)){
                    //Explosion beginning =>
                    Batch.getBatch().draw((TextureRegion) getSkin().getExplosionAnimation().getKeyFrame(elapsedExplosionTime, false),
                            currentSprite.getX(), currentSprite.getY(), currentSprite.getWidth() / 2, currentSprite.getHeight() / 2,
                            currentSprite.getWidth(), currentSprite.getHeight(), EXPLOSION_SIZE * 1.5f, EXPLOSION_SIZE, 0);

                    elapsedExplosionTime += Gdx.graphics.getDeltaTime();

                    if(!playerPieceHandler.isSpawned()){
                        playerPieceHandler.spawn(getSkin().getSprite().getX() + getSkin().getSprite().getWidth() / 3, getSkin().getSprite().getY() + getSkin().getSprite().getHeight() / 3);
                    }
                }else if(playerPieceHandler.isAnimationFinished()){
                    //If the WHOLE explosion is done =>
                    GameHandler.setIsExplosionDone(true);
                }

            }
            playerPieceHandler.renderPlayerPieces(delta);

        }else{
            Batch.getBatch().draw((TextureRegion) getSkin().getAnimation().getKeyFrame(elapsedTime, true),
                    currentSprite.getX(), currentSprite.getY(), currentSprite.getWidth() / 2, currentSprite.getHeight() / 2,
                    currentSprite.getWidth(), currentSprite.getHeight(), 1, 1, getCurrentRotation());
        }

        Batch.getBatch().end();
    }

    //The Intro-Animation where the spaceship goes forward
    public void renderIntroAnimation(float delta){
        if(getCurrentSkin().getY() < POSITION_Y){
            rectangle.x = getSkin().getSprite().getX() + getSkin().getWidth() / 2 - rectangle.width / 2;
            rectangle.y = getSkin().getSprite().getY() - rectangle.height;
            getCurrentSkin().setY(getCurrentSkin().getY() + INTRO_VELOCITY);
            renderPlayer(delta);
        }else if(getCurrentSkin().getY() >= POSITION_Y){
            getCurrentSkin().setY(POSITION_Y);
            GameHandler.setIsIntroDone(true);

        }
    }

    public Rectangle getParticleRectangle(){
        return rectangle;
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
        currentSprite.getTexture().dispose();
        playerPieceHandler.dispose();
        particleHandler.dispose();
        skin.dispose();
        //don't need bulletHandler to dispose because the bullets disposes themselves
    }

}