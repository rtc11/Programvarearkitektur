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
    private int pointNorth = 0;
    private int pointSourth = 0;

    public Ball(Image imgs){
        super(imgs);
        setPosition(500.0f, 700.0f);
        setSpeed(5, 15);
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
        if (y + speedy < 0) {
            speedy *= -1;
            Log.i("POENG", "NORTH");
            pointSourth++;

        }

        if(y + speedy >= 1000){
            speedy *= -1;
            Log.i("POENG", "SOUTH");
            pointNorth++;
        }

        x += speedx;
        y += speedy;

        ball.setSpeed(new Vector2(speedx, speedy));
        ball.setPosition(new Vector2(x, y));
    }

    public int getSouth(){
        return this.pointSourth;
    }

    public int getNorth(){
        return this.pointNorth;
    }
}
