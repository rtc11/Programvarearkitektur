package com.example.module;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import sheep.game.State;
import sheep.game.World;
import sheep.input.KeyboardListener;

/**
 * Created by tordly on 15.01.14.
 */
public class GameState extends State implements KeyboardListener{

    private World world;
    private GameLayer layer;

    public GameState (){

        //Create the game world
        world = new World();

        //Create the layer
        layer = new GameLayer();

        //Add the layer to the world
        world.addLayer(layer);

        //Add keyboard listener
        this.addKeyboardListener(this);
    }

    @Override
    public void draw (Canvas canvas){
        canvas.drawColor(Color.WHITE);
        world.draw(canvas);
    }

    @Override
    public void update(float ft){
        world.update(ft);
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
