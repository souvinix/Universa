package de.noahwantoch.galaxyproject;

import android.os.Bundle;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import de.noahwantoch.galaxyproject.MAIN.MainClass;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		initialize(new MainClass(), config);

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}
