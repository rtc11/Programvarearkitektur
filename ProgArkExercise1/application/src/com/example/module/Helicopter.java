package com.example.module;

import android.util.Log;

import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

/**
 * Created by tordly on 15.01.14.
 */
public class Helicopter extends Sprite {

    private boolean moveLeft = false;
    private boolean moveUp = false;
    private boolean moveRight = false;
    private boolean moveDown = false;

    private static final float velocity = 300.0f;

    public Helicopter(Image img){
        super(img);
        setPosition(500.0f, 700.0f);
//        setScale(0.2f, 0.2f);
    }

    @Override
    public void update(float dt){
        super.update(dt);

        float x = getX();
        float y = getY();

        if(moveLeft){
            float dx = dt * velocity;
            if (x <= 0) {
                stopHelicopterMovement();
                moveRight(true);
            }
            else{
                setPosition(x - dx, y);
            }
        }
        if(moveRight){
            float dx = dt * velocity;
            if (x >= MyActivity.WIDHT) {
                stopHelicopterMovement();
                moveLeft(true);
            }
            else {
                setPosition(x + dx, y);
            }
        }
        if(moveUp){
            float dy = dt * velocity;
            if (y <= 0) {
                stopHelicopterMovement();
                moveDown(true);
            }
            else {
                setPosition(x, y - dy);
            }
        }
        if(moveDown){
            float dy = dt * velocity;
            if (y >= MyActivity.HEIGHT) {
                stopHelicopterMovement();
                moveUp(true);
            }
            else {
                setPosition(x, y + dy);
            }
        }

    }

    public void moveLeft(boolean move){
        moveLeft = move;
    }

    public void moveRight(boolean move){
        moveRight = move;
    }

    public void moveUp(boolean move){
        moveUp = move;
    }

    public void moveDown(boolean move){
        moveDown = move;
    }

    public void stopHelicopterMovement(){
        moveLeft(false);
        moveRight(false);
        moveUp(false);
        moveDown(false);
        Log.i("DIRECTION", "DOWN");
    }
}
