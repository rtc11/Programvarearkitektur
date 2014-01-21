package com.example.module;

import android.graphics.Point;

import sheep.game.Sprite;
import sheep.game.SpriteContainer;
import sheep.graphics.Image;
import sheep.graphics.SpriteView;
import sheep.math.Vector2;

/**
 * Created by tordly on 15.01.14.
 */
public class Helicopter extends Sprite {

    private boolean moveLeft = false;
    private boolean moveUp = false;
    private boolean moveRight = false;
    private boolean moveDown = false;

    private static final float velocity = 300.0f;
    private Image[] imgs;

    //when using animation/////////////////////////
    private float tick = 0f;                     //
    private float timeLeft = 0f;                 //
    private int frameCount = 4, currentFrame = 0;//
    ///////////////////////////////////////////////

    public Helicopter(Image[] imgs){
        super(imgs[0]);
        this.imgs = imgs;
        setPosition(500.0f, 700.0f);
        setSpeed(1, 3);
    }

    @Override
    public void setParent(SpriteContainer parent) {
        super.setParent(parent);
    }

    @Override
    public void update(float dt){

        timeLeft += dt;
        if(timeLeft >= tick){
            currentFrame = (currentFrame + 1) % frameCount;
            setView((SpriteView)imgs[currentFrame]);
            timeLeft -= tick;
        }
        move(this);
//
//        float x = getX();
//        float y = getY();
//
//        if(moveLeft){
//            float dx = dt * velocity;
//            if (x <= 0) {
//                stopHelicopterMovement();
//                moveRight(true);
//            }
//            else{
//                setPosition(x - dx, y);
//            }
//        }
//        if(moveRight){
//            float dx = dt * velocity;
//            if (x >= MyActivity.WIDHT) {
//                stopHelicopterMovement();
//                moveLeft(true);
//            }
//            else {
//                setPosition(x + dx, y);
//            }
//        }
//        if(moveUp){
//            float dy = dt * velocity;
//            if (y <= 0) {
//                stopHelicopterMovement();
//                moveDown(true);
//            }
//            else {
//                setPosition(x, y - dy);
//            }
//        }
//        if(moveDown){
//            float dy = dt * velocity;
//            if (y >= MyActivity.HEIGHT) {
//                stopHelicopterMovement();
//                moveUp(true);
//            }
//            else {
//                setPosition(x, y + dy);
//            }
//        }

        super.update(dt);
    }

    public void move(Helicopter helicopter) {

        float x = helicopter.getX();
        float y = helicopter.getY();

        Vector2 v = getSpeed();

        float speedx = v.getX();
        float speedy = v.getY();

        if (x + speedx < 0 || x + speedx > MyActivity.WIDHT) {
            speedx *= -1;
        }
        if (y + speedy < 0 || y + speedy > MyActivity.HEIGHT) {
            speedy *= -1;
        }

        x += speedx;
        y += speedy;

        helicopter.setSpeed(new Vector2(speedx, speedy));
        helicopter.setPosition(new Vector2(x, y));
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
    }
}
