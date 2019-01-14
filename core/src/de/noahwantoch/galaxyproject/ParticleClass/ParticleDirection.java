package de.noahwantoch.galaxyproject.ParticleClass;

import com.badlogic.gdx.Gdx;

public enum ParticleDirection {

    LEFT{
        @Override
        public float getDeltaX() {
            return -Gdx.graphics.getDeltaTime();
        }

        @Override
        public float getDeltaY() {
            return 0;
        }
    },

    RIGHT{
        @Override
        public float getDeltaX() {
            return Gdx.graphics.getDeltaTime();
        }

        @Override
        public float getDeltaY() {
            return 0;
        }
    },

    UP{
        @Override
        public float getDeltaX() {
            return 0;
        }

        @Override
        public float getDeltaY() {
            return Gdx.graphics.getDeltaTime();
        }
    },

    DOWN{
        @Override
        public float getDeltaX() {
            return 0;
        }

        @Override
        public float getDeltaY() {
            return -Gdx.graphics.getDeltaTime();
        }
    },

    LEFT_UP{
        @Override
        public float getDeltaX() {
            return -Gdx.graphics.getDeltaTime();
        }

        @Override
        public float getDeltaY() {
            return Gdx.graphics.getDeltaTime();
        }
    },

    RIGHT_UP{
        @Override
        public float getDeltaX() {
            return Gdx.graphics.getDeltaTime();
        }

        @Override
        public float getDeltaY() {
            return Gdx.graphics.getDeltaTime();
        }
    },

    LEFT_DOWN{
        @Override
        public float getDeltaX() {
            return -Gdx.graphics.getDeltaTime();
        }

        @Override
        public float getDeltaY() {
            return -Gdx.graphics.getDeltaTime();
        }
    },

    RIGHT_DOWN{
        @Override
        public float getDeltaX() {
            return Gdx.graphics.getDeltaTime();
        }

        @Override
        public float getDeltaY() {
            return -Gdx.graphics.getDeltaTime();
        }
    };


    public abstract float getDeltaX();
    public abstract float getDeltaY();

}
