package com.example.module;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.game.World;
import sheep.input.TouchListener;

/**
 * Created by tordly on 15.01.14.
 */
public class GameState extends State implements TouchListener {

    private World world;
    private GameLayer layer;
    private DebugInfo debugInfo;
    private float startX, startY, endX, endY;

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
        canvas.drawColor(Color.BLACK);
        world.draw(canvas);
        debugInfo.draw(canvas);
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
//                layer.getHelicopter().setDirection(1);
            }
            else{
                stopHelicopterMovement();
                layer.getHelicopter().moveRight(true);
//                layer.getHelicopter().setDirection(0);
            }
        }
        else if (Math.abs(diffX) < Math.abs(diffY)){
            if(diffY < 0){
                stopHelicopterMovement();
                layer.getHelicopter().moveUp(true);
//                layer.getHelicopter().setDirection(2);
            }
            else{
                stopHelicopterMovement();
                layer.getHelicopter().moveDown(true);
//                layer.getHelicopter().setDirection(3);
            }
        }
        else{
            stopHelicopterMovement();
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
    }

    @Override
    public boolean onTouchMove(MotionEvent me){
        return false;
    }
}
