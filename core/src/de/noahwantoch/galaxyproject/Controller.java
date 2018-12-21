package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;
import de.noahwantoch.galaxyproject.Helper.Touchdetector;

public class Controller {

    private static final String TAG = Controller.class.getSimpleName(); //For debugging

    private static final float heightOfController = CurrentSystem.getScreenHeight() * 0.1f; //Y-Position
    private static final float SIZE_MULTIPLIER = 0.05f; //Relation //0.05f is default
    private static final float PRESSED_ADDITION = 25; //If you press the Button, size addition
    private static final int CONTROLLER_HITBOX = 10; //0 is default //The Button (invisible) gets bigger
    private static final float VELOCITY = 15f; //How much units, should the spaceship slide to right/left
    private static final int ROTATION_VALUE = 15;

    // Path's of the Buttons ->
    private static final String LEFT_BUTTON_PATH = "links_button.png";
    private static final String RIGHT_BUTTON_PATH = "rechts_button.png";

    //The Button's object's ->
    private Sprite leftSprite;
    private Sprite rightSprite;

    private Vector3 position; //The touching-Position => touch-detection (!)

    private Player player; //the player object

    //Constants which are connected with other constants. Don't change these. >>>
    private static final float BUTTON_WIDTH = (CurrentSystem.getScreenWidth() / Gdx.graphics.getDensity()) * SIZE_MULTIPLIER * Gdx.graphics.getDensity() * 2;
    private static final float BUTTON_HEIGHT = (CurrentSystem.getScreenHeight() / Gdx.graphics.getDensity()) * SIZE_MULTIPLIER * Gdx.graphics.getDensity();

    //Tests ->
    private Touchdetector touchdetector;

    public Controller(){

        position = new Vector3();

        leftSprite = new Sprite(new Texture(LEFT_BUTTON_PATH));
        rightSprite = new Sprite(new Texture(RIGHT_BUTTON_PATH));

        player = new Player(1);

        leftSprite.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        rightSprite.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        leftSprite.setPosition(CurrentSystem.getScreenWidth()*0.2f, heightOfController);
        rightSprite.setPosition(CurrentSystem.getScreenWidth() - (CurrentSystem.getScreenWidth() * 0.2f) - rightSprite.getWidth(), heightOfController);

        //Tests -->
        touchdetector = new Touchdetector();

    }

    //Will be called every time when a new frame begins //every frame
    public void renderController(float delta){
        player.renderPlayer(delta);

        Batch.getBatch().begin();

        touchdetector.renderTouches();

        if(!touchdetector.isTouching(leftSprite, CONTROLLER_HITBOX)){
            Batch.getBatch().draw(leftSprite, leftSprite.getX(), heightOfController, leftSprite.getWidth(), leftSprite.getHeight());
        }

        if(!touchdetector.isTouching(rightSprite, CONTROLLER_HITBOX)){
            Batch.getBatch().draw(rightSprite, rightSprite.getX(), heightOfController, rightSprite.getWidth(), rightSprite.getHeight());
        }

        if(touchdetector.isTouching(leftSprite, CONTROLLER_HITBOX)){
            Batch.getBatch().draw(leftSprite, leftSprite.getX(), heightOfController, leftSprite.getWidth() + PRESSED_ADDITION, leftSprite.getHeight() + PRESSED_ADDITION);
            //Actually function of the left Button =>
            this.toLeft();
            player.setCurrentRotation(ROTATION_VALUE);

        }
        if(touchdetector.isTouching(rightSprite, CONTROLLER_HITBOX)){
            Batch.getBatch().draw(rightSprite, rightSprite.getX(), heightOfController, rightSprite.getWidth() + PRESSED_ADDITION, rightSprite.getHeight() + PRESSED_ADDITION);
            //Actually function of the right Button =>
            this.toRight();
            player.setCurrentRotation(-ROTATION_VALUE);
        }

        if(!Gdx.input.isTouched()){
            player.setCurrentRotation(0);
        }

        Batch.getBatch().end();
    }

    public void toRight(){
        player.getCurrentSkin().setX(player.getCurrentSkin().getX() + VELOCITY);

        if(player.getCurrentSkin().getX() > Gdx.graphics.getWidth() - player.getCurrentSkin().getWidth()){
            player.getCurrentSkin().setX(Gdx.graphics.getWidth() - player.getCurrentSkin().getWidth());

        }

    }

    public void toLeft(){
        player.getCurrentSkin().setX(player.getCurrentSkin().getX() - VELOCITY);

        if(player.getCurrentSkin().getX() < 0){
            player.getCurrentSkin().setX(0);
        }

    }

    public void dispose(){
        player.dispose();
        leftSprite.getTexture().dispose();
        rightSprite.getTexture().dispose();
    }
}
