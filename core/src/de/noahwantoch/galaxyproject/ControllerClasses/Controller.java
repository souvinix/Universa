package de.noahwantoch.galaxyproject.ControllerClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.CurrentSystem;
import de.noahwantoch.galaxyproject.Helper.Touchdetector;
import de.noahwantoch.galaxyproject.AroundThePlayer.Player;

public class Controller {

    private static final String TAG = Controller.class.getSimpleName(); //For debugging

    private static final float HEIGHT_OF_CONTROLLER = CurrentSystem.getScreenHeight() * 0.1f; //Y-Position
    private static final float SIZE_MULTIPLIER = 0.05f; //Relation //0.05f is default
    private static final float PRESSED_ADDITION = 1.2f; //If you press the Button, size addition
    private static final int CONTROLLER_HITBOX = 0; //0 is default //The Button (invisible) gets bigger //doesn't work yet(!)
    private static final float VELOCITY = 15f; //How much units, should the spaceship slide to right/left
    private static final int ROTATION_VALUE = 15; //How many degrees should the spaceship rotate

    // Path's of the Buttons ->
    private static final String LEFT_BUTTON_PATH = "links_button.png";
    private static final String RIGHT_BUTTON_PATH = "rechts_button.png";
    private static final String FIREBUTTON_PATH = "shooting_button.png";

    //The Button's object's ->
    private Sprite leftSprite;
    private Sprite rightSprite;
    private static Sprite firebutton;

    private Player player; //the player object

    //Constants which are connected with other constants. Don't change those. >>>
    private static final float BUTTON_WIDTH = (CurrentSystem.getScreenWidth() / Gdx.graphics.getDensity()) * SIZE_MULTIPLIER * Gdx.graphics.getDensity() * 2;
    private static final float BUTTON_HEIGHT = (CurrentSystem.getScreenHeight() / Gdx.graphics.getDensity()) * SIZE_MULTIPLIER * Gdx.graphics.getDensity();

    //Touching-Detector =>
    private Touchdetector touchdetector;

    public Controller(){

        leftSprite = new Sprite(new Texture(LEFT_BUTTON_PATH));
        rightSprite = new Sprite(new Texture(RIGHT_BUTTON_PATH));
        firebutton = new Sprite(new Texture(FIREBUTTON_PATH));

        leftSprite.setOrigin(leftSprite.getWidth() / 2f, leftSprite.getHeight() / 2f);
        rightSprite.setOrigin(rightSprite.getWidth() / 2f, rightSprite.getHeight() / 2f);
        firebutton.setOrigin(firebutton.getWidth() / 2f, firebutton.getHeight() / 2f);

        player = new Player();

        leftSprite.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        rightSprite.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        firebutton.setSize(BUTTON_WIDTH + BUTTON_WIDTH * 0.2f, BUTTON_HEIGHT + BUTTON_HEIGHT * 0.2f);

        leftSprite.setPosition(CurrentSystem.getScreenWidth()*0.2f, HEIGHT_OF_CONTROLLER);
        rightSprite.setPosition(CurrentSystem.getScreenWidth() - (CurrentSystem.getScreenWidth() * 0.2f) - rightSprite.getWidth(), HEIGHT_OF_CONTROLLER);
        firebutton.setPosition(CurrentSystem.getScreenWidth() / 2f - firebutton.getWidth() / 2f, HEIGHT_OF_CONTROLLER * 0.5f);

        touchdetector = new Touchdetector();

    }

    //Will be called every time when a new frame begins //every frame
    public void renderController(float delta){
        player.renderPlayer(delta);

        Batch.getBatch().begin();

        if(!touchdetector.isTouching(firebutton, CONTROLLER_HITBOX)){
            firebutton.setScale(1, 1);
        }else{
            firebutton.setScale(PRESSED_ADDITION, PRESSED_ADDITION);
            player.fire();
        }
        firebutton.draw(Batch.getBatch());

        if(!touchdetector.isTouching(leftSprite, CONTROLLER_HITBOX)){
            leftSprite.setScale(1, 1);
            player.setCurrentRotation(0);
        }

        if(!touchdetector.isTouching(rightSprite, CONTROLLER_HITBOX)){
            rightSprite.setScale(1, 1);
            player.setCurrentRotation(0);
        }

        if(touchdetector.isTouching(leftSprite, CONTROLLER_HITBOX)){
            leftSprite.setScale(PRESSED_ADDITION, PRESSED_ADDITION);
            //Actually function of the left Button =>
            this.toLeft();
            player.setCurrentRotation(ROTATION_VALUE);
        }
        leftSprite.draw(Batch.getBatch());

        if(touchdetector.isTouching(rightSprite, CONTROLLER_HITBOX)){
            rightSprite.setScale(PRESSED_ADDITION, PRESSED_ADDITION);
            //Actually function of the right Button =>
            this.toRight();
            player.setCurrentRotation(-ROTATION_VALUE);
        }
        rightSprite.draw(Batch.getBatch());

        if(touchdetector.isTouching(leftSprite, CONTROLLER_HITBOX) && touchdetector.isTouching(rightSprite, CONTROLLER_HITBOX)){
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

    public Player getPlayer(){
        return player;
    }

    public static Sprite getFirebutton(){
        return firebutton;
    }

    public static float getHitbox(){
        return CONTROLLER_HITBOX;
    }

    public void dispose(){
        player.dispose();
        leftSprite.getTexture().dispose();
        rightSprite.getTexture().dispose();
        firebutton.getTexture().dispose();
    }
}
