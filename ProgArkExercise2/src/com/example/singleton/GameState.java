package com.example.singleton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.game.World;
import sheep.graphics.Image;
import sheep.input.TouchListener;
import sheep.math.Vector2;

/**
 * Created by tordly on 15.01.14.
 */
public class GameState extends State implements TouchListener {

    private DebugInfo debugInfo;
    private Ball ball;
    private Pad pad;
    private Pad pad2;
    private int height, width;

    public GameState (){

        debugInfo = DebugInfo.getInstance(this);

        //Get or create instance 1 of Pad
        pad = Pad.getInstance(1);
        pad.setPosition(300.0f, 900.0f);

        //Get or create instance 2 of Pad
        pad2 = Pad.getInstance(2);
        pad2.setPosition(300.0f, 50.0f);

        ball = Ball.getInstance();

        this.addTouchListener(this);
    }

    @Override
    public void draw (Canvas canvas){
        width = canvas.getWidth();
        height = canvas.getHeight();
        canvas.drawColor(Color.BLACK);
        debugInfo.draw(canvas);
        ball.draw(canvas);
        pad.draw(canvas);
        pad2.draw(canvas);
    }

    @Override
    public void update(float ft){
        debugInfo.update(ft);
        ball.update(ft);
        pad.update(ft);
        pad2.update(ft);

        //CHECK IF IT COLLIDES WITH WALL
        if(ball.getX()>(width-ball.getWidth()) || ball.getX()<0){
            ball.setSpeed(-ball.getSpeed().getX(), ball.getSpeed().getY());
        }
        if(ball.getY()>(height-ball.getHeight()) || ball.getY()<0){
            ball.setSpeed(ball.getSpeed().getX(), -ball.getSpeed().getY());
        }

        //CHECK IF IT COLLIDES WITH PAD
        if(ball.collides(pad)){
            Vector2 v = ball.getSpeed();
            float speedX = v.getX();
            float speedY = v.getY();
            speedX *= -1;
            speedY *= -1;
            ball.setSpeed(new Vector2(speedX, speedY));
        }

        if(ball.collides(pad2)){
            Vector2 v = ball.getSpeed();
            float speedX = v.getX();
            float speedY = v.getY();
            speedX *= -1;
            speedY *= -1;
            ball.setSpeed(new Vector2(speedX, speedY));
        }
    }

    @Override
    public boolean onTouchDown(MotionEvent me){
        return false;
    }

    @Override
    public boolean onTouchUp(MotionEvent me){
        return false;
    }

    @Override
    public boolean onTouchMove(MotionEvent me){

        //If the south half part of the screen was touched
        if(me.getY() > height/2){
            pad.setPosition(me.getX(), 900);
        }

        //If the north half part of the screen was touched
        if(me.getY() < height/2){
            pad2.setPosition(me.getX(), 50);
        }
        return false;
    }

    public Ball getBall(){
        return this.ball;
    }

    public Pad getPad(){
        return this.pad;
    }
}
