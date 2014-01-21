package com.example.module;

import sheep.game.Sprite;
import sheep.game.SpriteContainer;
import sheep.graphics.Image;
import sheep.graphics.SpriteView;

/**
 * Created by tordly on 15.01.14.
 */
public class Helicopter extends Sprite {

    private boolean moveLeft = false;
    private boolean moveUp = false;
    private boolean moveRight = false;
    private boolean moveDown = false;

    private static final float velocity = 300.0f;
    private Image[] imgs;   //TODO:might remove this

    //when not using animation////////
    private int current = 0;        //
    //////////////////////////////////

    //when using animation/////////////////////////
    private float tick = 0f;                     //
    private float timeLeft = 0f;                 //
    private int frameCount = 4, currentFrame = 0;//
    ///////////////////////////////////////////////

    public Helicopter(Image[] imgs){
        super(imgs[0]);
        this.imgs = imgs;
        setPosition(500.0f, 700.0f);
    }

    /**
     * 0 - right
     * 1 - left
     * 2 - up
     * 3 - down
     */
    public void setDirection(int dir){
        current = dir;
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

        super.update(dt);
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
