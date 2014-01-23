package com.example.TaskThree;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.game.World;
import sheep.input.TouchListener;
import sheep.math.Vector2;

/**
 * Created by tordly on 15.01.14.
 */
public class GameState extends State implements TouchListener, CollisionListener {

    private World world;
    private GameLayer layer;
    private DebugInfo debugInfo;
    private float startX, startY, endX, endY;
    private CollisionLayer collisionLayer;

    public GameState (){

        //Create the game world
        world = new World();

        //Create the layer
        layer = new GameLayer();

        //Add the layer to the world
        world.addLayer(layer);

        debugInfo = new DebugInfo(layer);

        //Add collision listeners
        collisionLayer = new CollisionLayer();
        for(Token token : layer.getTokens()){
            collisionLayer.addSprite(token);
            token.addCollisionListener(token);
            token.addCollisionListener(this);
        }

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
        return false;
    }

    @Override
    public boolean onTouchMove(MotionEvent me){

        layer.getPad().setPosition(300, me.getY());
        return false;
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite2) {
        Log.i("COLLISION", "GAME STATE");
        Ball ball;
        Pad pad;

        if(sprite instanceof Ball && sprite2 instanceof Pad){
            ball = (Ball)sprite;
            pad = (Pad)sprite2;
        }
        else{
            ball = (Ball)sprite2;
            pad = (Pad)sprite;
        }

        Vector2 v = ball.getSpeed();
        float speedx = v.getX();
        float speedy = v.getY();
        speedx *= -1;
        float x = ball.getX();
        float y = ball.getY();
        ball.setSpeed(new Vector2(speedx, speedy));
        ball.setPosition(new Vector2(x, y));
    }
}
