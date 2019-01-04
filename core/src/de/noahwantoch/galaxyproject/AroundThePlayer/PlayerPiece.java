package de.noahwantoch.galaxyproject.AroundThePlayer;

import de.noahwantoch.galaxyproject.Game.Piece;

public class PlayerPiece extends Piece {

    private static final String TAG = PlayerPiece.class.getSimpleName();

    private static final String[] PLAYER_PIECE_DATA = {Skin.getCurrentSkinDirectory() + "piece.png", Skin.getCurrentSkinDirectory() + "piece2.png", Skin.getCurrentSkinDirectory() + "piece3.png", Skin.getCurrentSkinDirectory() + "piece4.png"};

    private String[] paths;

    public PlayerPiece(){
        super(PLAYER_PIECE_DATA);
    }

}
