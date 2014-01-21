package com.example.module;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import sheep.game.Camera;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.game.World;
import sheep.input.KeyboardListener;
import sheep.math.Vector2;

/**
 * Created by tordly on 15.01.14.
 */
public class GameState extends State implements KeyboardListener{

    private World world;
    private GameLayer layer;
    private DebugInfo debugInfo;


    private Sprite westWall, eastWall, northWall, southWall;

    public GameState (){

        //Create the game world
        world = new World();

        //Create the layer
        layer = new GameLayer();

        //Add the layer to the world
        world.addLayer(layer);

        debugInfo = new DebugInfo(layer);

        //Add keyboard listener
        this.addKeyboardListener(this);

        //Get the dimensions of the screen
        Context context = getGame().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
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

    public boolean onKeyDown(KeyEvent e){
        switch(e.getKeyCode()){
             case KeyEvent.KEYCODE_A:
                 layer.getHelicopter().moveLeft(true);
                 break;
             case KeyEvent.KEYCODE_D:
                layer.getHelicopter().moveRight(true);
                 break;
            case KeyEvent.KEYCODE_W:
                layer.getHelicopter().moveUp(true);
                break;
            case KeyEvent.KEYCODE_S:
                layer.getHelicopter().moveDown(true);
                break;
        }
        return true;
    }

    public boolean onKeyUp(KeyEvent e){
         switch(e.getKeyCode()){
             case KeyEvent.KEYCODE_A:
                 layer.getHelicopter().moveLeft(false);
                 break;
             case KeyEvent.KEYCODE_D:
                 layer.getHelicopter().moveRight(false);
                 break;
             case KeyEvent.KEYCODE_W:
                 layer.getHelicopter().moveUp(false);
                 break;
             case KeyEvent.KEYCODE_S:
                 layer.getHelicopter().moveDown(false);
                 break;
         }
        return true;
    }
}
