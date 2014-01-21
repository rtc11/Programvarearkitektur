package com.example.Task2;

import android.graphics.Canvas;
import sheep.game.Layer;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

/**
 * Created by tordly on 15.01.14.
 */
public class GameLayer extends Layer {

    private Helicopter helicopter;
    private Level level;

    public GameLayer(){

        Image[] imgs = {
                new Image(R.drawable.helliright),
                new Image(R.drawable.hellileft),
                new Image(R.drawable.helliup),
                new Image(R.drawable.hellidown)

        };
         Image[] animation = {
                 new Image(R.drawable.left1),
                 new Image(R.drawable.left2),
                 new Image(R.drawable.left3),
                 new Image(R.drawable.left4)
        };

        this.helicopter = new Helicopter(animation);
        this.level = new Level();


    }

    @Override
    public void update(float dt) {
        this.helicopter.update(dt);
    }

    @Override
    public void draw(Canvas canvas, BoundingBox box) {
        this.helicopter.draw(canvas);
//        this.level.draw(canvas);
    }

    public Helicopter getHelicopter(){
        return this.helicopter;
    }

    public Level getLevel(){
        return this.level;
    }
}
