package de.noahwantoch.galaxyproject.BulletClasses;

import java.util.ArrayList;

import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;

public class BulletHandler {

    private static final String TAG = BulletHandler.class.getSimpleName();

    private ArrayList<Bullet> currentBullets;

    private static final float DISTANCE_BETWEEN = 0.3f; //In seconds
    private static final float VELOCITY = 30;

    private String bulletPath;
    private float bulletWidth;
    private float bulletHeight;
    private float startX;
    private float startY;

    private float counter;

    public BulletHandler(String bulletPath, float width, float height){
        currentBullets = new ArrayList<Bullet>();

        this.bulletPath = bulletPath;
        this.bulletWidth = width;
        this.bulletHeight = height;

    }

    public void renderBullets(float delta){

        counter += delta;

        if(!currentBullets.isEmpty()){
            for(Bullet bullet : currentBullets){
                if(!bullet.isDisposed()){
                    bullet.update(Batch.getBatch(), VELOCITY);
                }
            }
            for(Bullet bullet : currentBullets){
                if(bullet.isDisposed()){
                    currentBullets.remove(bullet);
                    break;
                }

                if(bullet.getBulletSprite().getY() > CurrentSystem.getScreenHeight() || bullet.getAsteroidCollision()){
                    bullet.dispose();
                }
            }
        }

    }

    public void drop(float x, float y){
        this.startX = x;
        this.startY = y;

        if(counter > DISTANCE_BETWEEN){
            Bullet bullet = new Bullet(bulletPath, bulletWidth, bulletHeight, startX, startY);
            currentBullets.add(bullet);
            counter = 0;
        }
    }

    public ArrayList<Bullet> getCurrentBullets(){
        return currentBullets;
    }
}
