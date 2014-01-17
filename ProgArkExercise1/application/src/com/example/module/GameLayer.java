package com.example.module;

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
        this.helicopter = new Helicopter(new Image(R.drawable.helli));
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
