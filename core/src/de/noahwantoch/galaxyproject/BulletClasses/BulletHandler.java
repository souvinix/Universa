package de.noahwantoch.galaxyproject.BulletClasses;

import java.util.ArrayList;

import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;

public class BulletHandler {

    private static final String TAG = BulletHandler.class.getSimpleName();

    private ArrayList<Bullet> currentBullets;

    private static final float DISTANCE_BETWEEN = 0.3f; //In seconds
    private float counter;

    public BulletHandler(){
        currentBullets = new ArrayList<Bullet>();
    }

    public void renderBullets(float delta){

        counter += delta;

        if(!currentBullets.isEmpty()){
            for(Bullet bullet : currentBullets){
                if(!bullet.isDisposed()){
                    bullet.update(Batch.getBatch());
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

    public void drop(){
        if(counter > DISTANCE_BETWEEN){
            Bullet bullet = new Bullet();
            currentBullets.add(bullet);
            counter = 0;
        }
    }

    public ArrayList<Bullet> getCurrentBullets(){
        return currentBullets;
    }
}
