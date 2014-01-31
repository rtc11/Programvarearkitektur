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

        float xPos = ball.getX();
        float yPos = ball.getY();

        Vector2 velocity = getSpeed();
        float xSpeed = velocity.getX();
        float ySpeed = velocity.getY();

        if (xPos + xSpeed < 0 || xPos + xSpeed > MyActivity.WIDTH) {
            xSpeed *= -1;
        }

        //If the ball hits the north wall
        if (yPos + ySpeed < 0) {
            ySpeed *= -1;
            pointSouth++;
        }

        //if the ball hits the south wall
        if(yPos + ySpeed >= 1000){ //TODO: this number should be relative to the screen size
            ySpeed *= -1;
            pointNorth++;
        }

        xPos += xSpeed;
        yPos += ySpeed;
        ball.setSpeed(new Vector2(xSpeed, ySpeed));
        ball.setPosition(new Vector2(xPos, yPos));
    }

    public int getSouth(){
        return this.pointSouth;
    }

    public int getNorth(){
        return this.pointNorth;
    }
}
