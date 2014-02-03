package com.example.singleton;

import android.graphics.Canvas;
import sheep.graphics.Color;
import sheep.graphics.Image;

import java.util.ArrayList;

/**
 * Created by tordly on 17.01.14.
 */
public class DebugInfo implements Observer {

    private static DebugInfo instance = null;
    private ArrayList<String> strings;
    private int framesLastSecond;
    private int framesThisSecond;
    private int msCounter;
    private GameState gameState;
    private int scoreSouth = 0, scoreNorth = 0;

    private DebugInfo(GameState gameState) {
        this.gameState = gameState;
        strings = new ArrayList<String>();
        framesLastSecond = 0;
        framesThisSecond = 0;
        msCounter = 0;
    }

    /**
     * Returns the DebugInfo instance or creates a new one.
     * Uses Singleton pattern for making sure
     * there is only one instance of this object
     * instantiated.
     * @return DebugInfo instance
     */
    public static DebugInfo getInstance(GameState gameState){
        if(instance == null){
            synchronized (DebugInfo.class){
                if(instance == null){
                    instance = new DebugInfo(gameState);
                }
            }
        }
        return instance;
    }

    public void update(float dt) {
        int millisecondsSinceLastFrame = (int) (dt * 1000);
        framesThisSecond++;
        msCounter += millisecondsSinceLastFrame;
        if (msCounter >= 1000) {
            msCounter -= 1000;
            framesLastSecond = framesThisSecond;
            framesThisSecond = 0;
        }
    }

    public void draw(Canvas canvas) {
        strings.add("NORTH: " + scoreNorth);
        strings.add("SOUTH: " + scoreSouth);

        for ( int i = 0; i < strings.size(); i++ ) {
            String string = strings.get(i);
            int y = (i+1)*15;
            canvas.drawText(string, 15, y, Color.GREEN);
        }
        strings = new ArrayList<String>();
    }

    @Override
    public void update(int scoreNorth, int scoreSouth) {
        this.scoreNorth = scoreNorth;
        this.scoreSouth = scoreSouth;
    }
}
