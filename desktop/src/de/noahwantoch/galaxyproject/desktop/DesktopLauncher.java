package de.noahwantoch.galaxyproject.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.noahwantoch.galaxyproject.MainClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Universia";
		config.useGL30 = false;
		config.width = 550;
		config.height = 900;

		Application app = new LwjglApplication(new MainClass(), config);

		Gdx.app = app;
		Gdx.app.setLogLevel(Application.LOG_DEBUG);


	}
}
