package de.noahwantoch.galaxyproject.BulletClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet{

    private static final String TAG = Bullet.class.getSimpleName();

    private Sprite bulletSprite;
    private boolean isDisposed = false;

    private boolean asteroidCollision = false;

    private static String currentBulletPath;

    public Bullet(String path, float width, float height, float startX, float startY){

        currentBulletPath = path;

        bulletSprite = new Sprite(new Texture(path));
        bulletSprite.setSize(width, height);
        bulletSprite.setPosition(startX, startY);

    }

    public void update(SpriteBatch batch, float velocity){
        batch.begin();
        bulletSprite.draw(batch);
        batch.end();
        bulletSprite.setY(bulletSprite.getY() + velocity);
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
