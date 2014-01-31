package com.example.singleton;

import android.util.Log;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.game.SpriteContainer;
import sheep.graphics.Image;

/**
 * Created by tordly on 15.01.14.
 */
public class Pad extends Token{

    private static final float velocity = 300.0f;

    public Pad(Image imgs, float y){
        super(imgs);
        setPosition(300.0f, y);
    }

    @Override
    public void setParent(SpriteContainer parent) {
        super.setParent(parent);
    }

    @Override
    public void update(float dt){
        super.update(dt);
    }
}
