package com.example.module;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;
import sheep.game.Camera;
import sheep.game.State;
import sheep.game.World;
import sheep.input.TouchListener;
import sheep.math.Vector2;

/**
 * Created by tordly on 15.01.14.
 */
public class GameState extends State implements TouchListener {

    private World world;
    private GameLayer layer;
    private DebugInfo debugInfo;

    private float startX, startY, endX, endY;
    private boolean pressing = false;

    public GameState (){

        //Create the game world
        world = new World();

        //Create the layer
        layer = new GameLayer();

        //Add the layer to the world
        world.addLayer(layer);

        debugInfo = new DebugInfo(layer);

        //Add keyboard listener
        this.addTouchListener(this);
    }

    @Override
    public void draw (Canvas canvas){
//        moveCamera(canvas);
        canvas.drawColor(Color.WHITE);
        world.draw(canvas);
        debugInfo.draw(canvas);
    }

    public void moveCamera(Canvas canvas) {
        int screenWidth = canvas.getWidth();
        int screenHeight = canvas.getHeight();
        int captainPositionX = (int)(layer.getHelicopter().getPosition().getX());
        int captainPositionY = (int)(layer.getHelicopter().getPosition().getY());
        int screenPositionX = captainPositionX - (screenWidth / 2);
        int screenPositionY = captainPositionY - (screenHeight / 2);
        int levelWidth = layer.getLevel().getWidth();
        int levelHeight = layer.getLevel().getHeight();

        screenPositionX = (screenPositionX >= 0) ? screenPositionX : 0;
        screenPositionY = (screenPositionY >= 0) ? screenPositionY : 0;
        screenPositionX = (screenPositionX + screenWidth >= levelWidth) ? levelWidth - screenWidth : screenPositionX;
        screenPositionY = (screenPositionY + screenHeight >= levelHeight) ? levelHeight - screenHeight : screenPositionY;

        Camera camera = world.getCamera();
        camera.setSize(new Vector2(screenWidth, screenHeight));
        camera.setPosition(new Vector2(screenPositionX, screenPositionY));
    }

    @Override
    public void update(float ft){
        world.update(ft);
        debugInfo.update(ft);
    }

    @Override
    public boolean onTouchDown(MotionEvent me){
        startX = me.getX();
        startY = me.getY();
        return false;
    }

    @Override
    public boolean onTouchUp(MotionEvent me){

        endX = me.getX();
        endY = me.getY();
        float diffX = endX - startX;
        float diffY = endY - startY;

        //Check if it is X axis or Y axis movement
        if(Math.abs(diffX) > Math.abs(diffY)){
            if(diffX < 0){
                stopHelicopterMovement();
                layer.getHelicopter().moveLeft(true);
                Log.i("DIRECTION", "LEFT");
            }
            else{
                stopHelicopterMovement();
                layer.getHelicopter().moveRight(true);
                Log.i("DIRECTION", "RIGHT");
            }
        }
        else if (Math.abs(diffX) < Math.abs(diffY)){
            if(diffY < 0){
                stopHelicopterMovement();
                layer.getHelicopter().moveUp(true);
                Log.i("DIRECTION", "UP");
            }
            else{
                stopHelicopterMovement();
                layer.getHelicopter().moveDown(true);
                Log.i("DIRECTION", "DOWN");
            }
        }
        else{
            stopHelicopterMovement();
            Log.i("DIRECTION", "STOP");
        }
        startX = 0;
        endX = 0;
        startY = 0;
        endY = 0;

        return false;
    }

    private void stopHelicopterMovement(){
        layer.getHelicopter().moveLeft(false);
        layer.getHelicopter().moveRight(false);
        layer.getHelicopter().moveUp(false);
        layer.getHelicopter().moveDown(false);
        Log.i("DIRECTION", "DOWN");
    }

    @Override
    public boolean onTouchMove(MotionEvent me){
        return false;
    }
}
