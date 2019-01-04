package de.noahwantoch.galaxyproject.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationHandler {

    private static final String TAG = AnimationHandler.class.getSimpleName();

    private TextureRegion[] animationFrames;
    private Animation animation;

    public AnimationHandler(){}

    public Animation generateAnimation(Texture spritesheet, int frames, int tileWidth, int tileHeight, float frameDuration){ //symmetric
        TextureRegion[][] tempFrames = TextureRegion.split(spritesheet, tileWidth, tileHeight);
        animationFrames = new TextureRegion[frames];

        int index = 0;
        for(int i = 0; i < Math.sqrt(frames); i++){
            for(int j = 0; j < Math.sqrt(frames); j++){
                animationFrames[index++] = tempFrames[i][j];
            }
        }

        animation = new Animation(frameDuration, animationFrames);

        return animation;
    }

    public Animation generateAnimation(String spritesheetPath, int frames, int tileWidth, int tileHeight, float frameDuration){
        return generateAnimation(new Texture(spritesheetPath), frames, tileWidth, tileHeight, frameDuration);
    }

    /**
     * Generates a Animation where the columns and width (of the Spritesheet) aren't equal
     * */
    public Animation generateNotSymmetricAnimation(Texture spritesheet, int tileWidth, int tileHeight, float frameDuration, int columnWidth, int columnHeight){
        TextureRegion[][] tempFrames = TextureRegion.split(spritesheet, tileWidth, tileHeight);
        int frames = columnWidth * columnHeight; //All sprites
        animationFrames = new TextureRegion[frames];

        int index = 0;
        for(int i = 0; i < columnHeight; i++){
            for(int j = 0; j < columnWidth; j++){
                animationFrames[index++] = tempFrames[i][j];
            }
        }

        animation = new Animation(frameDuration, animationFrames);

        return animation;
    }

    public Animation generateNotSymmetricAnimation(String spritesheetPath, int tileWidth, int tileHeight, float frameDuration, int columnWidth, int columnHeight){
        return generateNotSymmetricAnimation(new Texture(spritesheetPath), tileWidth, tileHeight, frameDuration, columnWidth, columnHeight);
    }

    public void dispose(){
        for(TextureRegion frame : animationFrames){
            frame.getTexture().dispose();
        }

    }
}
