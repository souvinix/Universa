package de.noahwantoch.galaxyproject;

import com.badlogic.gdx.Game;

import de.noahwantoch.galaxyproject.Game.GameHandler;
import de.noahwantoch.galaxyproject.Screens.MainGameScreen;

public class MainClass extends Game {

	private static final MainGameScreen MAIN_GAME_SCREEN = new MainGameScreen();

	@Override
	public void create () {
		setScreen(MAIN_GAME_SCREEN);
	}
	
	@Override
	public void dispose () {
		MAIN_GAME_SCREEN.dispose();
	}
}
