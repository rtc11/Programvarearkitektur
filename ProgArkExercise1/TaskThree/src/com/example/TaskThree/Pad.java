package com.example.TaskThree;

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

    public Pad(Image imgs){
        super(imgs);
        setPosition(300.0f, 700.0f);

    }

    @Override
    public void setParent(SpriteContainer parent) {
        super.setParent(parent);
    }

    @Override
    public void update(float dt){
        super.update(dt);
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite2) {
        Log.i("COLLISION", "PAD");
    }
}
