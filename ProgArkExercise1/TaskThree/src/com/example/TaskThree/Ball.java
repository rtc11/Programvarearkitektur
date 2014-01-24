package com.example.TaskThree;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;
import android.widget.Toast;
import sheep.collision.CollisionListener;
import sheep.collision.Interval;
import sheep.collision.Shape;
import sheep.game.Sprite;
import sheep.game.SpriteContainer;
import sheep.graphics.Image;
import sheep.graphics.SpriteView;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

/**
 * Created by tordly on 15.01.14.
 */
public class Ball extends Token{

    private static final float velocity = 300.0f;

    public Ball(Image imgs){
        super(imgs);
        setPosition(500.0f, 700.0f);
        setSpeed(5, 15);
        setGroup(5);
        setMask(2 | 3);
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

        float x = ball.getX();
        float y = ball.getY();

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

        ball.setSpeed(new Vector2(speedx, speedy));
        ball.setPosition(new Vector2(x, y));
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite2) {
        Log.i("COLLISION", "BALL");
    }
}
