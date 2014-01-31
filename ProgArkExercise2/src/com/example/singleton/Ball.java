package com.example.singleton;

import android.util.Log;
import sheep.game.SpriteContainer;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * Created by tordly on 15.01.14.
 */
public class Ball extends Token{

    private static final float velocity = 300.0f;
    private static Ball instance = null;
    private int pointNorth = 0;
    private int pointSouth = 0;

    private Ball(Image imgs){
        super(imgs);
        setPosition(500.0f, 700.0f);
        setSpeed(5, 15);
    }
    /**
     * Returns the Ball instance or creates a new one.
     * Uses Singleton pattern for making sure
     * there is only one instance of this object
     * instantiated.
     * @return Ball instance
     */
    public static Ball getInstance(){
        if(instance == null){
            synchronized (Ball.class){
                if(instance == null){
                    Image img = new Image(R.drawable.left1);
                    instance = new Ball(img);
                }
            }
        }
        return instance;
    }



    @Override
    public void setParent(SpriteContainer parent) {
        super.setParent(parent);
    }

    @Override
    public void update(float dt){
        move(this);
        super.update(dt);
    }

    public void move(Ball ball) {

        float x = ball.getX();
        float y = ball.getY();

        Vector2 v = getSpeed();

        float speedx = v.getX();
        float speedy = v.getY();

        if (x + speedx < 0 || x + speedx > MyActivity.WIDTH) {
            speedx *= -1;
        }
        if (y + speedy < 0) {
            speedy *= -1;
            Log.i("POENG", "NORTH");
            pointSouth++;

        }

        if(y + speedy >= 1000){
            speedy *= -1;
            Log.i("POENG", "SOUTH");
            pointNorth++;
        }

        x += speedx;
        y += speedy;

        ball.setSpeed(new Vector2(speedx, speedy));
        ball.setPosition(new Vector2(x, y));
    }

    public int getSouth(){
        return this.pointSouth;
    }

    public int getNorth(){
        return this.pointNorth;
    }
}
