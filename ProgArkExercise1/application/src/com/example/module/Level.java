package com.example.module;

import android.graphics.Canvas;
import sheep.graphics.*;

import java.util.ArrayList;

/**
 * Created by tordly on 15.01.14.
 */
public class Level{

    private int width;
    private int height;

    public Level(){
        this.width = 768;
        this.height = 1184;

    }

    public void draw(Canvas canvas) {

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
