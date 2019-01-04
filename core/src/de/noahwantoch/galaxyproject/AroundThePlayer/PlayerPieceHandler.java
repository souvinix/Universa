package de.noahwantoch.galaxyproject.AroundThePlayer;

import java.util.ArrayList;

import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.RandomHelper;

public class PlayerPieceHandler {

    private static final String TAG = PlayerPieceHandler.class.getSimpleName();

    private static final int MAX_PIECES = 6;
    private static final float MAX_LIFETIME = 5;

    private float currentLifeTime;

    private ArrayList<PlayerPiece> currentPlayerPieces;

    private boolean isSpawned = false;

    private boolean isAnimationFinished = false;

    public PlayerPieceHandler(){

        currentPlayerPieces = new ArrayList<PlayerPiece>();
        generateNewOnes();

    }

    public void generateNewOnes(){
        currentPlayerPieces.clear();

        for(int i = 0; i < MAX_PIECES; i++){
            currentPlayerPieces.add(new PlayerPiece());
        }

    }
    public void spawn(float x, float y){
        float width = Player.getSkin().getSprite().getWidth();
        float height = Player.getSkin().getSprite().getHeight();

        for(PlayerPiece playerPiece : currentPlayerPieces){
            playerPiece.getSprite().setOrigin(playerPiece.getSprite().getWidth() / 2, playerPiece.getSprite().getHeight() / 2);
            playerPiece.getSprite().setPosition(x + RandomHelper.getRandomAxisValue(width, 0), y);
            playerPiece.getSprite().setSize(width / 3, height / 3);
            playerPiece.getSprite().setRotation(RandomHelper.getRandomAxisValue(360, 0));

            playerPiece.generateRandomDirection();
        }

        setSpawned(true);
    }

    public void renderPlayerPieces(float delta){
        currentLifeTime += delta;
        if(currentLifeTime <= MAX_LIFETIME){
            for(PlayerPiece playerPiece : currentPlayerPieces){

                playerPiece.getSprite().setOrigin( playerPiece.getSprite().getWidth() / 2,  playerPiece.getSprite().getHeight() / 2);
                playerPiece.getSprite().draw(Batch.getBatch());
                playerPiece.getSprite().setX( playerPiece.getSprite().getX() + playerPiece.getDirection().x);
                playerPiece.getSprite().setY( playerPiece.getSprite().getY() + playerPiece.getDirection().y);
                playerPiece.getSprite().setRotation(playerPiece.getSprite().getRotation() + 1);
            }
        }else{
            isAnimationFinished = true;
            dispose();
        }
    }

    public boolean isAnimationFinished(){
        return isAnimationFinished;
    }

    public boolean isSpawned(){
        return isSpawned;
    }

    private void setSpawned(boolean value){
        isSpawned = value;
    }

    public void dispose(){
        for(PlayerPiece playerPiece : currentPlayerPieces){
            playerPiece.dispose();
        }
        currentPlayerPieces.clear();
    }
}
