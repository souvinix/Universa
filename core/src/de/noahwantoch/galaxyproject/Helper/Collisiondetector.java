package de.noahwantoch.galaxyproject.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Collisiondetector {

    private static final String TAG = Collisiondetector.class.getSimpleName();

    private static final boolean SHOW_COLLISION_DETECTION = false;

    private Circle shipCircle;
    private Circle objectCircle;

    private ShapeRenderer shapeRenderer;

    public Collisiondetector(){
        shipCircle = new Circle();
        objectCircle = new Circle();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    public boolean checkCollision(Sprite ship, Sprite object){

        shipCircle.setRadius(ship.getHeight() / 2);
        shipCircle.setPosition(ship.getX() + ship.getWidth() / 2, ship.getY() + ship.getHeight() / 2);
        objectCircle.setRadius(object.getWidth() / 2);
        objectCircle.setPosition(object.getX() + object.getWidth() / 2, object.getY() + object.getHeight() / 2);

        if(SHOW_COLLISION_DETECTION){
            shapeRenderer.begin();
            shapeRenderer.circle(shipCircle.x, shipCircle.y, shipCircle.radius);
            shapeRenderer.circle(objectCircle.x, objectCircle.y, objectCircle.radius);
            shapeRenderer.end();
        }

        if(shipCircle.overlaps(objectCircle)){
            return true;
        }
        return false;
    }


}
