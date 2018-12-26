package de.noahwantoch.galaxyproject.Helper;

import com.badlogic.gdx.Gdx;

public class RandomHelper {

    private static final String TAG = RandomHelper.class.getSimpleName();

    private static java.util.Random generator = new java.util.Random();

    private RandomHelper(){}

    public static int getRandomAxisValue(int max, int min){
        return generator.nextInt(max - min) - min;
    }

    public static float getRandomAxisValue(float max, float min){
        return generator.nextFloat() * (max - min) + min;
    }

    public static int getRandomOperator(int value){
        float x = generator.nextFloat();

        if(x > 0.5f){
            return value;
        }else{
            return -value;
        }
    }
}
