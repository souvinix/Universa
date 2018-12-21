package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;

public class ControllerBackup {

    private static final String TAG = ControllerBackup.class.getSimpleName(); //For debugging

    private static final float heightOfController = CurrentSystem.getScreenHeight() * 0.1f; //Y-Position
    private static final float SIZE_MULTIPLIER = 0.1f; //Relation
    private static final float PRESSED_ADDITION = 25; //If you press the Button, size addition
    private static final int CONTROLLER_HITBOX = 10; //0 is default //The Button (invisible) gets bigger
    private static final int SLIDE_VALUE = 15; //How much units, should the spaceship slide to right/left

    // Path's of the Buttons ->
    private static final String LEFT_BUTTON_PATH = "links_button.png";
    private static final String RIGHT_BUTTON_PATH = "rechts_button.png";

    //The Button's object's ->
    private Sprite leftSprite;
    private Sprite rightSprite;

    private Vector3 position; //The touching-Position => touch-detection (!)

    //Booleans for checking if a button was touched
    private boolean touchedRightSprite = false;
    private boolean touchedLeftSprite = false;

    private Player player; //the player object

    //Constants which are connected with other constants. Don't change these. >>>
    private static final float BUTTON_WIDTH = CurrentSystem.getScreenWidth() * SIZE_MULTIPLIER * 1.5f;
    private static final float BUTTON_HEIGHT = CurrentSystem.getScreenHeight() * SIZE_MULTIPLIER;

    public ControllerBackup(){

        position = new Vector3();

        leftSprite = new Sprite(new Texture(LEFT_BUTTON_PATH));
        rightSprite = new Sprite(new Texture(RIGHT_BUTTON_PATH));

        player = new Player(1);

        leftSprite.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        rightSprite.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        leftSprite.setPosition(CurrentSystem.getScreenWidth()*0.2f, heightOfController);
        rightSprite.setPosition(CurrentSystem.getScreenWidth() - (CurrentSystem.getScreenWidth() * 0.2f) - rightSprite.getWidth(), heightOfController);

    }

    //Will be called every time when a new frame begins //every frame
    public void renderController(float delta){
        player.renderPlayer();

        Batch.getBatch().begin();

        this.renderTouches();

        if(!touchedLeftSprite){
            Batch.getBatch().draw(leftSprite, leftSprite.getX(), heightOfController, leftSprite.getWidth(), leftSprite.getHeight());
        }

        if(!touchedRightSprite){
            Batch.getBatch().draw(rightSprite, rightSprite.getX(), heightOfController, rightSprite.getWidth(), rightSprite.getHeight());
        }

        if(touchedLeftSprite == true){
            Batch.getBatch().draw(leftSprite, leftSprite.getX(), heightOfController, leftSprite.getWidth() + PRESSED_ADDITION, leftSprite.getHeight() + PRESSED_ADDITION);
            //Actually function of the left Button =>
            this.toLeft();

        }
        if(touchedRightSprite == true){
            Batch.getBatch().draw(rightSprite, rightSprite.getX(), heightOfController, rightSprite.getWidth() + PRESSED_ADDITION, rightSprite.getHeight() + PRESSED_ADDITION);
            //Actually function of the right Button =>
            this.toRight();
        }



        Batch.getBatch().end();
    }

    public void toRight(){
        player.getCurrentSkin().setX(player.getCurrentSkin().getX() + SLIDE_VALUE);

        if(player.getCurrentSkin().getX() > Gdx.graphics.getWidth() - player.getCurrentSkin().getWidth()){
            player.getCurrentSkin().setX(Gdx.graphics.getWidth() - player.getCurrentSkin().getWidth());

        }

    }

    public void toLeft(){
        player.getCurrentSkin().setX(player.getCurrentSkin().getX() - SLIDE_VALUE);

        if(player.getCurrentSkin().getX() < 0){
            player.getCurrentSkin().setX(0);
        }

    }

    public void renderTouches(){
        if (Gdx.input.isTouched()) {
            position.set(Gdx.input.getX(), CurrentSystem.getScreenHeight() - Gdx.input.getY(), 0);


            if(position.x > leftSprite.getX() + CONTROLLER_HITBOX && position.x < leftSprite.getX() + leftSprite.getWidth() - CONTROLLER_HITBOX){
                if(position.y > leftSprite.getY() + CONTROLLER_HITBOX && position.y < leftSprite.getY() + leftSprite.getHeight() - CONTROLLER_HITBOX){
                    //The left Sprite was touched ->
                    touchedLeftSprite = true;
                }
            }else{
                touchedLeftSprite = false;
            }

            if(position.x > rightSprite.getX() && position.x < rightSprite.getX() + rightSprite.getWidth()){
                if(position.y > rightSprite.getY() && position.y < rightSprite.getY() + rightSprite.getHeight()){
                    //The right Sprite was touched ->
                    touchedRightSprite = true;
                }
            }else{
                touchedRightSprite = false;
            }


        }else {
            touchedRightSprite = false;
            touchedLeftSprite = false;
        }

        if(Gdx.input.isTouched(1)){
            touchedRightSprite = false;
            touchedLeftSprite = false;
        }

    }

    public Player getPlayer(){
        return this.player;
    }

    public void dispose(){
        player.dispose();
        leftSprite.getTexture().dispose();
        rightSprite.getTexture().dispose();
    }
}
