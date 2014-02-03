package com.example.singleton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.input.TouchListener;
import sheep.math.Vector2;

/**
 * Created by tordly on 15.01.14.
 */
public class GameState extends State implements TouchListener {

    private Image padImg = new Image(R.drawable.pad);
    private DebugInfo debugInfo;
    private int height, width;
    private Ball ball;
    private Pad padSouth;
    private Pad padNorth;

    public GameState (){

        //Get or create the singleton instance of DebugInfo
        debugInfo = DebugInfo.getInstance(this);

        //Get or create the singleton instance of DebugInfo
        ball = Ball.getInstance();
        padSouth = new Pad(padImg, MyActivity.WIDTH/2, MyActivity.HEIGHT - ball.getHeight());
        padNorth = new Pad(padImg, MyActivity.WIDTH/2, padNorth.getHeight());

        this.addTouchListener(this);
    }

    @Override
    public void draw (Canvas canvas){
        width = canvas.getWidth();
        height = canvas.getHeight();
        canvas.drawColor(Color.BLACK);

        debugInfo.draw(canvas);
        ball.draw(canvas);
        padNorth.draw(canvas);
        padSouth.draw(canvas);
    }

    @Override
    public void update(float ft){
        debugInfo.update(ft);
        ball.update(ft);
        padSouth.update(ft);
        padNorth.update(ft);

        //CHECK IF IT COLLIDES WITH WALL
        if(ball.getX()>(width-(ball.getWidth()/2)) || ball.getX()<0 + (ball.getWidth()/2)){
            ball.setSpeed(-ball.getSpeed().getX(), ball.getSpeed().getY());
        }
        if(ball.getY()>(height-(ball.getHeight()/2)) || ball.getY()<0 + (ball.getHeight()/2)){
            ball.setSpeed(ball.getSpeed().getX(), -ball.getSpeed().getY());
        }

        //CHECK IF IT COLLIDES WITH PAD
        if(ball.collides(padSouth)){
            Vector2 v = ball.getSpeed();
            float speedX = v.getX();
            float speedY = v.getY();
            speedX *= -1;
            speedY *= -1;
            ball.setSpeed(new Vector2(speedX, speedY));
        }

        if(ball.collides(padNorth)){
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
            padSouth.setPosition(me.getX(), MyActivity.HEIGHT - ball.getHeight());
        }

        //If the north half part of the screen was touched
        if(me.getY() < height/2){
            padNorth.setPosition(me.getX(), padNorth.getHeight());
        }
        return false;
    }

    public Ball getBall(){
        return this.ball;
    }
}
