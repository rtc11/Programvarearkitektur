package com.example.module;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import sheep.game.Sprite;
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


    public GameState (){

        //Create the game world
        world = new World();

        //Create the layer
        layer = new GameLayer();

        //Add the layer to the world
        world.addLayer(layer);

        debugInfo = new DebugInfo(layer);

        this.addTouchListener(this);
    }

    @Override
    public void draw (Canvas canvas){
        canvas.drawColor(Color.BLACK);
        world.draw(canvas);
        debugInfo.draw(canvas);

        //Get the dimensions of the screen
        Context context = getGame().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
//        Log.i("Screen size", "Width = " + width + ". Height = " + height);
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

        layer.getHelicopter().setSpeed(diffX % 30, diffY % 30);

//        layer.getHelicopter().setOrientation((float)Math.atan((endX - startX)/(endY - startY)));

        return false;
    }

    @Override
    public boolean onTouchMove(MotionEvent me){
        return false;
    }
}
