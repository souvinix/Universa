package de.noahwantoch.galaxyproject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class StatsScreen extends AbstractScreen implements Screen{

    private static final String TAG = StatsScreen.class.getSimpleName();

    public StatsScreen(){
        super();
    }

    @Override
    public ScreenEnum getScreenEnum() {
        return ScreenEnum.STATS_SCREEN;
    }

    @Override
    public void show() {
        super.show();
        Gdx.app.debug(TAG, "Geöffnet");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.app.debug(TAG, "Geschlossen");
    }
}
