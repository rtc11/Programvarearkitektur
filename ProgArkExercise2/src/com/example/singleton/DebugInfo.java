package com.example.singleton;

import android.graphics.Canvas;
import sheep.graphics.Color;

import java.util.ArrayList;

/**
 * Created by tordly on 17.01.14.
 */
public class DebugInfo {
    private ArrayList<String> strings;
    private int framesLastSecond;
    private int framesThisSecond;
    private int msCounter;
    GameState gameLayer;

    public DebugInfo(GameState gameLayer) {
        this.gameLayer = gameLayer;
        strings = new ArrayList<String>();
        framesLastSecond = 0;
        framesThisSecond = 0;
        msCounter = 0;
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
        strings.add("FPS: " + framesLastSecond);
        strings.add("Cap-X:" + gameLayer.getBall().getX());
        strings.add("Cap-Y:" + gameLayer.getBall().getY());
        strings.add("NORTH: " + gameLayer.getBall().getNorth());
        strings.add("SOUTH: " + gameLayer.getBall().getSouth());


        for ( int i = 0; i < strings.size(); i++ ) {
            String string = strings.get(i);
            int y = (i+1)*15;
            canvas.drawText(string, 15, y, Color.GREEN);
        }
        strings = new ArrayList<String>();
    }
}
