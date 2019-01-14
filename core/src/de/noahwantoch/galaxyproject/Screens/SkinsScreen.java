package de.noahwantoch.galaxyproject.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class SkinsScreen extends AbstractScreen implements Screen {

    private static final String TAG = SkinsScreen.class.getSimpleName();


    public SkinsScreen(){
        super();
    }

    @Override
    public ScreenEnum getScreenEnum() {
        return ScreenEnum.SKINS_SCREEN;
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
