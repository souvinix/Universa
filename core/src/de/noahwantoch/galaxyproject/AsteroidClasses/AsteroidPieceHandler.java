package de.noahwantoch.galaxyproject.AsteroidClasses;

import java.util.ArrayList;

import de.noahwantoch.galaxyproject.Helper.Batch;
import de.noahwantoch.galaxyproject.Helper.RandomHelper;

public class AsteroidPieceHandler {

    private static final String TAG = AsteroidPieceHandler.class.getSimpleName();

    private static final int MAX_PIECES = 6;
    private ArrayList<AsteroidPiece> currentAsteroidPieces;

    public AsteroidPieceHandler(){
        currentAsteroidPieces = new ArrayList<AsteroidPiece>();
        generateNewOnes();
    }

    public void generateNewOnes(){
        currentAsteroidPieces.clear();

        for(int i = 0; i < MAX_PIECES; i++){
            currentAsteroidPieces.add(new AsteroidPiece());
        }

    }

    public void spawn(Asteroid asteroid){
        for(AsteroidPiece asteroidPiece : currentAsteroidPieces){
            asteroidPiece.getSprite().setOrigin(asteroidPiece.getSprite().getWidth() / 2, asteroidPiece.getSprite().getHeight() / 2);
            asteroidPiece.getSprite().setPosition(asteroid.getX() + RandomHelper.getRandomAxisValue(asteroid.getWidth(), 0), asteroid.getY());
            asteroidPiece.getSprite().setSize(asteroid.getWidth() / 3, asteroid.getHeight() / 3);
            asteroidPiece.getSprite().setRotation(RandomHelper.getRandomAxisValue(360, 0));

            asteroidPiece.generateRandomDirection();
        }
    }

    public void renderAsteroidPieces(float delta){
        for(AsteroidPiece asteroidPiece : currentAsteroidPieces){

            asteroidPiece.getSprite().setOrigin( asteroidPiece.getSprite().getWidth() / 2,  asteroidPiece.getSprite().getHeight() / 2);
            asteroidPiece.getSprite().draw(Batch.getBatch());
            asteroidPiece.getSprite().setX( asteroidPiece.getSprite().getX() + asteroidPiece.getDirection().x);
            asteroidPiece.getSprite().setY( asteroidPiece.getSprite().getY() + asteroidPiece.getDirection().y);
            asteroidPiece.getSprite().setRotation( asteroidPiece.getSprite().getRotation() + 1);
        }
    }

    public void dispose(){
        for(AsteroidPiece asteroidPiece : currentAsteroidPieces){
            asteroidPiece.dispose();
        }
    }

    public int getMaxPieces(){
        return MAX_PIECES;
    }
}