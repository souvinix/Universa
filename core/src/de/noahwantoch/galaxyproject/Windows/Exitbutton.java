package de.noahwantoch.galaxyproject.Windows;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.noahwantoch.galaxyproject.Game.Button;
import de.noahwantoch.galaxyproject.Screens.ScreenEnum;
import de.noahwantoch.galaxyproject.Screens.ScreenHandler;

public class Exitbutton extends Button{

    private static final String TAG = Exitbutton.class.getSimpleName();

    private static final String EXITBUTTON_SET_NAME = "exitbutton.png";
    private static final int EXITBUTTON_SET_WIDTH = 48;
    private static final int EXITBUTTON_SET_HEIGHT = 26;


    public Exitbutton(float x, float y, float size) {
        super(" ", x, y, size);
        super.changeTileset(EXITBUTTON_SET_NAME, EXITBUTTON_SET_WIDTH, EXITBUTTON_SET_HEIGHT);
    }

    @Override
    public void runFunction() {
        ScreenHandler.getInstance().showScreen(ScreenHandler.getInstance().getLastScreen());
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {
        super.draw(batch, delta);
    }
}
