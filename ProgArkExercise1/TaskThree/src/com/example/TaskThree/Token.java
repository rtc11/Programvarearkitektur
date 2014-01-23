package com.example.TaskThree;

import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by tordly on 23.01.14.
 */
public class Token extends Sprite implements CollisionListener {

    private float width = 0;
    private float height = 0;

    public Token(Image image){
        super(image);

        this.width = image.getWidth();
        this.height = image.getHeight();

        super.setShape(width, height);
    }

    public float getHeight(){
        return this.height;
    }

    public float getWidth(){
        return this.width;
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite2) {

    }
}
