package com.example.module;

import android.graphics.Canvas;
import sheep.game.State;
import sheep.game.World;

/**
 * Created by tordly on 15.01.14.
 */
public class GameState extends State {

    private World world;
    private GameLayer layer;


    public GameState (){
        world = new World();
        layer = new GameLayer();
        world.addLayer(layer);
    }

    @Override
    public void draw (Canvas canvas){
        world.draw(canvas);

    }
}
