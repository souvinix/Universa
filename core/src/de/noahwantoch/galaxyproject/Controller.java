package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class Controller {

    private Player player;

    private static final int Y_POS = 200;

    private static final String TAG = Controller.class.getSimpleName();

    private static final int heightOfController = 200;

    private Texture left_button;
    private Texture right_button;
    private Texture left_button_pressed;
    private Texture right_button_pressed;

    private Sprite leftSprite;
    private Sprite rightSprite;

    private ParticleEffect leftParticleEffect;
    private ParticleEffect rightParticleEffect;

    private float displayWidth = Gdx.app.getGraphics().getWidth();

    private final static int SLIDE_VALUE = 15;

    private final static String PATH_OF_PARTICLES = "main_particles.particle";

    private final static float MAX_DISPLAY_VALUE = Gdx.graphics.getHeight();
    private Vector3 position;
    private final static boolean SHOW_POSITIONS = true;

    private boolean touchedRightSprite = false;
    private boolean touchedLeftSprite = false;

    private static final int CONTROLLER_HITBOX = 10; //0 is default

    public Controller(){

        position = new Vector3();

        left_button = new Texture("links_button.png");
        right_button = new Texture("rechts_button.png");
        left_button_pressed = new Texture("links_button_pressed.png");
        right_button_pressed = new Texture("rechts_button_pressed.png");

        leftSprite = new Sprite(left_button);
        rightSprite = new Sprite(right_button);
        leftSprite.setX(displayWidth*0.20f);
        leftSprite.setY(Y_POS);
        rightSprite.setX((displayWidth - displayWidth*0.20f) - right_button.getWidth());
        rightSprite.setY(Y_POS);

        leftParticleEffect = new ParticleEffect();
        leftParticleEffect.load(Gdx.files.internal(PATH_OF_PARTICLES), Gdx.files.internal(""));
        leftParticleEffect.getEmitters().first().setPosition(leftSprite.getX() + leftSprite.getWidth() / 2, leftSprite.getY() + leftSprite.getHeight() / 2);
        rightParticleEffect = new ParticleEffect();
        rightParticleEffect.load(Gdx.files.internal(PATH_OF_PARTICLES), Gdx.files.internal(""));
        rightParticleEffect.getEmitters().first().setPosition(rightSprite.getX() + rightSprite.getWidth() / 2, rightSprite.getY() + rightSprite.getHeight() / 2);

        player = new Player(1);

    }

    //Will every time being called in the render method
    public void renderController(float delta){
        player.renderPlayer();

        leftParticleEffect.update(Gdx.graphics.getDeltaTime());
        rightParticleEffect.update(Gdx.graphics.getDeltaTime());

        Batch.getBatch().begin();

        this.renderTouches();

        if(!touchedLeftSprite){
            Batch.getBatch().draw(leftSprite, leftSprite.getX(), heightOfController);
        }

        if(!touchedRightSprite){
            Batch.getBatch().draw(rightSprite, rightSprite.getX(), heightOfController);
        }

        if(touchedLeftSprite == true){
            Batch.getBatch().draw(left_button_pressed, leftSprite.getX(), heightOfController);
            //Actually function of the left Button =>
            leftParticleEffect.getEmitters().first().setPosition(leftSprite.getX() + leftSprite.getWidth() / 2, leftSprite.getY() + leftSprite.getHeight() / 2);
            leftParticleEffect.start();
            leftParticleEffect.draw(Batch.getBatch());
            this.toLeft();

        }
        if(touchedRightSprite == true){
            Batch.getBatch().draw(right_button_pressed, rightSprite.getX(), heightOfController);
            //Actually function of the right Button =>
            rightParticleEffect.getEmitters().first().setPosition(rightSprite.getX() + rightSprite.getWidth() / 2, rightSprite.getY() + rightSprite.getHeight() / 2);
            rightParticleEffect.start();
            rightParticleEffect.draw(Batch.getBatch());
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
            position.set(Gdx.input.getX(), MAX_DISPLAY_VALUE - Gdx.input.getY(), 0);

            //Just show x and y of the touch/mouse-click in the log
            if(SHOW_POSITIONS){
                Gdx.app.debug(TAG, "-----------------------------------------");
                Gdx.app.debug(TAG, "Touched at x: " + Float.toString(position.x));
                Gdx.app.debug(TAG, "Touched at y: " + Float.toString(position.y));
                Gdx.app.debug(TAG, "-----------------------------------------");
            }


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
        left_button.dispose();
        right_button.dispose();
        right_button_pressed.dispose();
        left_button_pressed.dispose();
        leftParticleEffect.dispose();
        rightParticleEffect.dispose();
    }
}
