package de.noahwantoch.galaxyproject.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import de.noahwantoch.galaxyproject.Helper.CurrentSystem;

public class ParticleHandler {

    private static final String TAG = ParticleHandler.class.getSimpleName();
    private static final String PARTICLE_DIRECTORY = "Particles/";

    private ParticleEffectPool particleEffectPool;
    private Array<ParticleEffectPool.PooledEffect> effects = new Array();

    private TextureAtlas particleAtlas;
    private ParticleEffect particleEffect;

    private Vector3 position;

    public ParticleHandler(String fileName){

        position = new Vector3();

        particleAtlas = new TextureAtlas();
        particleEffect = new ParticleEffect();

        particleEffect.load(Gdx.files.internal(PARTICLE_DIRECTORY + fileName), particleAtlas);
        particleEffect.setEmittersCleanUpBlendFunction(false);

        particleEffectPool = new ParticleEffectPool(particleEffect, 1, 2);

        // Create effect:
        ParticleEffectPool.PooledEffect effect = particleEffectPool.obtain();
        effect.setPosition(getX(), getY());
        effects.add(effect);

        setPosition(CurrentSystem.getScreenWidth() / 2, CurrentSystem.getScreenHeight() / 2);

    }

    public void draw(SpriteBatch batch, float delta){
        for (int i = effects.size - 1; i >= 0; i--) {
            ParticleEffectPool.PooledEffect effect = effects.get(i);
            effect.draw(batch, delta);
            if (effect.isComplete()) {
                effect.free();
                effects.removeIndex(i);
            }
        }
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public void setX(float value){
        position.set(value, position.y, 0);
    }

    public void setY(float value){
        position.set(position.x, value, 0);
    }

    public void setPosition(float x, float y){
        position.set(x, y, 0);
    }

    public void dispose(){
        for (int i = effects.size - 1; i >= 0; i--)
            effects.get(i).free(); //free all the effects back to the pool
        effects.clear();
    }


}
