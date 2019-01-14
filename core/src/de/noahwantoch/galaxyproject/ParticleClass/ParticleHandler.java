package de.noahwantoch.galaxyproject.ParticleClass;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

import de.noahwantoch.galaxyproject.Helper.RandomHelper;

public class ParticleHandler {

    private static final String TAG = ParticleHandler.class.getSimpleName();

    private ArrayList<Particle> allParticles;
    private Rectangle rectangle;

    private String path;

    private int maxParticles;

    private ParticleDirection particleDirection;

    private ShapeRenderer shapeRenderer;

    private boolean isDisposed;
    private boolean moving;

    public ParticleHandler(String path, Rectangle rectangle, ParticleDirection particleDirection){
        this.rectangle = rectangle;
        this.path = path;
        this.particleDirection = particleDirection;

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        allParticles = new ArrayList<Particle>();

        for(Sprite sprite : allParticles){
            sprite.setPosition(getStartX(), getStartY());
        }

        isDisposed = false;
        moving = true;

    }

    private void update(){

        for (Particle particle: allParticles) {

            particle.setPosition(particle.getX() + particleDirection.getDeltaX() * particle.getVelocity(), particle.getY() + particleDirection.getDeltaY() * particle.getVelocity());
            if(particleDirection == ParticleDirection.RIGHT && particle.getX() > rectangle.x + rectangle.width){
                particle.setPosition(rectangle.x, getStartY());
            }
            if(particleDirection == ParticleDirection.LEFT && particle.getX() < rectangle.x){
                particle.setPosition(rectangle.x + rectangle.width, getStartY());
            }
            if(particleDirection == ParticleDirection.UP && particle.getY() > rectangle.y + rectangle.height){
                particle.setPosition(getStartX(), rectangle.y);
            }
            if(particleDirection == ParticleDirection.DOWN && particle.getY() < rectangle.y){
                particle.setPosition(getStartX(), rectangle.y + rectangle.height);
            }

            particle.rotate(1f);
        }

    }

    public void render(SpriteBatch batch, float delta){

        if(!isDisposed && moving){
            for (Sprite sprite : allParticles){

                batch.begin();
                sprite.draw(batch);
                batch.end();

            }

            update();
        }

    }

    public void showRectangle(){
        shapeRenderer.begin();
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        shapeRenderer.end();
    }

    public void GenerateParticles(int value){
        maxParticles = value;
        if(allParticles != null){
            for(Sprite sprite : allParticles){
                sprite.getTexture().dispose();
            }
            allParticles.clear();
        }
        allParticles = new ArrayList<Particle>(maxParticles);

        for(int i = 0; i < maxParticles; i++){
            allParticles.add(new Particle(new Texture(path)));
        }

        for(Sprite sprite : allParticles){
            sprite.setPosition(getStartX(), getStartY());
        }

    }

    public float getStartX(){
        if(particleDirection == ParticleDirection.LEFT || particleDirection == ParticleDirection.LEFT_DOWN || particleDirection == ParticleDirection.LEFT_UP){
            return rectangle.x;
        }
        if(particleDirection == ParticleDirection.RIGHT || particleDirection == ParticleDirection.RIGHT_DOWN || particleDirection == ParticleDirection.RIGHT_UP){
            return rectangle.x + rectangle.width;
        }

        if(particleDirection == ParticleDirection.UP || particleDirection == ParticleDirection.DOWN){
            float randomX = RandomHelper.getRandomAxisValue(rectangle.x, rectangle.x + rectangle.width);
            return randomX;
        }
        return 0;
    }

    public float getStartY(){
        if(particleDirection == ParticleDirection.LEFT || particleDirection == ParticleDirection.RIGHT){
            float randomY = RandomHelper.getRandomAxisValue(rectangle.y, rectangle.y + rectangle.height);
            return randomY;
        }
        if(particleDirection == ParticleDirection.UP || particleDirection == ParticleDirection.LEFT_UP || particleDirection == ParticleDirection.RIGHT_UP){
            return rectangle.y + rectangle.height;
        }

        if(particleDirection == ParticleDirection.DOWN || particleDirection == ParticleDirection.LEFT_DOWN || particleDirection == ParticleDirection.RIGHT_DOWN){
            return rectangle.y;
        }

        return 0;
    }

    public void setMoving(boolean value){
        moving = value;
    }

    public boolean isMoving(){
        return moving;
    }

    public void setColor(float r, float g, float b){
        for(Particle particle : allParticles){
            particle.setColor(r, g, b);
        }
    }

    public void setAlpha(float x){
        for(Particle particle : allParticles){
            particle.setAlpha(x);
        }
    }

    public void setDirection(ParticleDirection particleDirection){
        this.particleDirection = particleDirection;
    }

    public String getPath(){
        return path;
    }

    public void setPath(String path){
        this.path = path;
    }

    public void setVelocity(float x){
        for(Particle particle : allParticles){
            particle.setVelocity(x);
        }
    }

    public void setParticleSize(float size){
        for(Particle particle : allParticles){
            particle.setScale(RandomHelper.getRandomAxisValue(size, size / 3));
        }
    }

    public void dispose(){
        for(Particle particle : allParticles){
            particle.getTexture().dispose();
        }
        allParticles.clear();

        isDisposed = true;
    }
}
