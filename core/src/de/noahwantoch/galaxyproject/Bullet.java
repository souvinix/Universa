package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.noahwantoch.galaxyproject.Helper.CurrentSystem;

public class Bullet{

    private static final String TAG = Bullet.class.getSimpleName();

    private static final String NORMAL_BULLET_PATH = "bullets.png";
    private static final float BULLET_WIDTH = Player.getSkin().getSprite().getWidth();
    private static final float BULLET_HEIGHT = CurrentSystem.getScreenHeight() * 0.05f;
    private static final float VELOCITY = 30;

    private Sprite bulletSprite;
    private boolean isDisposed = false;

    private boolean asteroidCollision = false;


    public Bullet(){

        bulletSprite = new Sprite(new Texture(NORMAL_BULLET_PATH));
        bulletSprite.setSize(BULLET_WIDTH, BULLET_HEIGHT);
        bulletSprite.setPosition(Player.getSkin().getSprite().getX(), Player.getSkin().getSprite().getY() + Player.getSkin().getSprite().getHeight());

    }

    public void update(SpriteBatch batch){
        batch.begin();
        bulletSprite.draw(batch);
        batch.end();
        bulletSprite.setY(bulletSprite.getY() + VELOCITY);
    }

    public Sprite getBulletSprite(){
        return bulletSprite;
    }

    //Very important for saving memory -->
    public void dispose(){
        bulletSprite.getTexture().dispose();
        isDisposed = true;

    }

    public void setAsteroidCollision(boolean value){
        asteroidCollision = value;
    }

    public boolean getAsteroidCollision(){
        return asteroidCollision;
    }

    public boolean isDisposed(){
        return isDisposed;
    }

}
