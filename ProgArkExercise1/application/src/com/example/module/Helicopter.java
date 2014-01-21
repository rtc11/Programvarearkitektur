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

    private static final float velocity = 150.0f;
    private Image[] imgs;
    private int current = 0;

    public Helicopter(Image[] imgs){
        super(imgs[0]);
        this.imgs = imgs;
        setPosition(500.0f, 700.0f);
        setScale(0.2f, 0.2f);
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
        super.update(dt);

        setView((SpriteView)imgs[current]);

        float x = getX();
        float y = getY();

        if(moveLeft){
            float dx = dt * velocity;
            setPosition(x - dx, y);
        }
        if(moveRight){
            float dx = dt * velocity;
            setPosition(x + dx, y);
        }
        if(moveUp){
            float dy = dt * velocity;
            setPosition(x, y - dy);
        }
        if(moveDown){
            float dy = dt * velocity;
            setPosition(x, y + dy);
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
}
