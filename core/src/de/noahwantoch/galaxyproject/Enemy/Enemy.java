package de.noahwantoch.galaxyproject.Enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.noahwantoch.galaxyproject.Helper.AnimationHandler;

public class Enemy{
    private static final String TAG = Enemy.class.getSimpleName();

    private static final String ENEMY_DATA_NAME = "enemy.png";
    private static final String ENEMY_SPRITESHEET_PATH = "enemy_spritesheet.png";
    private static final String ENEMY_BULLET_PATH = "enemy_bullet.png";

    private Texture spritesheet;
    private AnimationHandler animationHandler;
    private Animation animation;

    public Enemy(){
        spritesheet = new Texture(ENEMY_SPRITESHEET_PATH);

        animationHandler = new AnimationHandler();
        animation = animationHandler.generateAnimation(spritesheet, 4, 64, 64, 1f/8f);
    }

    private void changeKeyFrame(){



    }

    public void render(SpriteBatch batch, float delta){
//        super.draw(batch);
    }

    public void dispose(){

    }
}
