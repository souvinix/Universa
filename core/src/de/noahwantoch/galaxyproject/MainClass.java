package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.Game;

import de.noahwantoch.galaxyproject.Game.GameHandler;
import de.noahwantoch.galaxyproject.Screens.MainGameScreen;
import de.noahwantoch.galaxyproject.Screens.ScreenEnum;
import de.noahwantoch.galaxyproject.Screens.ScreenHandler;

public class MainClass extends Game {

	private static final MainGameScreen MAIN_GAME_SCREEN = new MainGameScreen();



	@Override
	public void create () {
		ScreenHandler.getInstance().initialize(this);
		ScreenHandler.getInstance().showScreen(ScreenEnum.MAIN_GAME_SCREEN);
	}
	
	@Override
	public void dispose () {
		ScreenHandler.getInstance().getCurrentScreen().dispose();
	}
}
