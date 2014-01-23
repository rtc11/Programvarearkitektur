package com.example.TaskThree;

import android.graphics.Canvas;
import sheep.collision.CollisionLayer;
import sheep.game.Layer;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

import java.util.ArrayList;

/**
 * Created by tordly on 15.01.14.
 */
public class GameLayer extends Layer {

    private Pad pad;
    private Ball ball;
    private Level level;
    private ArrayList<Token> tokens;

    public GameLayer(){
        Image img = new Image(R.drawable.left1);
        this.pad = new Pad(img);
        this.ball = new Ball(img);
        this.level = new Level();

        tokens = new ArrayList<Token>();
        tokens.add(pad);
        tokens.add(ball);
    }

    public ArrayList<Token> getTokens(){
        return this.tokens;
    }

    @Override
    public void update(float dt) {
        this.pad.update(dt);
        this.ball.update(dt);
    }

    @Override
    public void draw(Canvas canvas, BoundingBox box) {
        this.pad.draw(canvas);
        this.ball.draw(canvas);
    }

    public Pad getPad(){
        return this.pad;
    }

    public Ball getBall(){
            return this.ball;
    }

    public Level getLevel(){
        return this.level;
    }
}
