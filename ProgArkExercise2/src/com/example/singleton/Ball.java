package com.example.singleton;

import sheep.game.SpriteContainer;
import sheep.graphics.Image;
import sheep.math.Vector2;
import java.util.ArrayList;


/**
 * Created by tordly on 15.01.14.
 */
public class Ball extends Token implements Observable{

    private static Ball instance = null;
    private int pointNorth = 0;
    private int pointSouth = 0;
    private ArrayList<Observer> observers;

    private Ball(Image imgs){
        super(imgs);
        setPosition(500.0f, 700.0f);
        setSpeed(5, 15);
        observers = new ArrayList<Observer>();
    }
    /**
     * Returns the Ball instance or creates a new one.
     * Uses SINGLETON pattern for making sure
     * there is only one instance of this object
     * instantiated.
     * @return Ball instance
     */
    public static Ball getInstance(){
        if(instance == null){
            synchronized (Ball.class){
                if(instance == null){
                    Image img = new Image(R.drawable.ball);
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
        if (yPos + ySpeed < 0 + (getHeight()/2)) {
            ySpeed *= -1;
            pointSouth++;
            notifyObservers();
        }

        //if the ball hits the south wall
        if(yPos + ySpeed >= MyActivity.HEIGHT - getHeight()){
            ySpeed *= -1;
            pointNorth++;
            notifyObservers();
        }

        xPos += xSpeed;
        yPos += ySpeed;
        ball.setSpeed(new Vector2(xSpeed, ySpeed));
        ball.setPosition(new Vector2(xPos, yPos));
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer ob : observers){
            ob.update(pointNorth, pointSouth);
        }
    }
}
