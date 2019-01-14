package de.noahwantoch.galaxyproject.ParticleClass;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import de.noahwantoch.galaxyproject.Helper.RandomHelper;

public class Particle extends Sprite{

    private float velocity;
    private float rotation;

    public Particle(Texture texture){
        super(texture);
        rotation = RandomHelper.getRandomOperator(RandomHelper.getRandomAxisValue(360, 0));
    }

    public void setVelocity(float value){
        float x = RandomHelper.getRandomAxisValue(value * value / 2f, value / 2f);
        velocity = x;
    }

    public void rotate(float value){
        setRotation(rotation);
        if(rotation >= 360){
            rotation = 0;
        }
        rotation += value;
    }

    public void setColor(float r, float g, float b){
        super.setColor(r, g, b, super.getColor().a);
    }

    public float getVelocity(){
        return velocity;
    }





}
