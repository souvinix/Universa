package de.noahwantoch.galaxyproject.Screens;

import com.badlogic.gdx.Screen;

public enum ScreenEnum {

    MAIN_GAME_SCREEN {
        public AbstractScreen getScreen() {
            return new MainGameScreen();
        }

        public String getName() {
            return "MainGameScreen";
        }
    },
    SKINS_SCREEN {
        public AbstractScreen getScreen() {
            return new SkinsScreen();
        }

        public String getName() {
            return "SkinsScreen";
        }
    },
    STATS_SCREEN {
        public AbstractScreen getScreen() {
            return new StatsScreen();
        }
        public String getName() {
            return "StatsScreen";
        }
    },

    OPTIONS_SCREEN {
        public AbstractScreen getScreen() {
            return new Optionsscreen();
        }
        public String getName() {
            return "OptionsScreen";
        }
    };

    public abstract String getName();
    public abstract AbstractScreen getScreen();

    public static ScreenEnum getScreenEnumByName(String name) {
        for(ScreenEnum screenEnum : ScreenEnum.values()){
            if(name.equals(screenEnum.getName())){
                return screenEnum;
            }
        }
        return null;
    }
}