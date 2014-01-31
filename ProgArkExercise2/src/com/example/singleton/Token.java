package com.example.singleton;

import android.util.Log;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by tordly on 23.01.14.
 */
public class Token extends Sprite {

    private float width = 0;
    private float height = 0;

    public Token(Image image){
        super(image);

        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public float getHeight(){
        return this.height;
    }

    public float getWidth(){
        return this.width;
    }

}
