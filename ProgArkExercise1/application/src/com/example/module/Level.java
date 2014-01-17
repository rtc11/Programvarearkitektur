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
    private ArrayList<Wall> walls;

    public Level(){
        walls = new ArrayList<Wall>();
        walls.add(new Wall(0, 0, 999, 100, Color.BLUE));
        walls.add(new Wall(0, 100, 40, 950, Color.BLUE));
        walls.add(new Wall(0, 950, 999, 999, Color.BLUE));
        walls.add(new Wall(40, 630, 300, 690, Color.BLUE));
        walls.add(new Wall(100, 150, 400, 555, Color.BLUE));
        walls.add(new Wall(200, 850, 275, 950, Color.BLUE));
        walls.add(new Wall(355, 555, 400, 950, Color.BLUE));
        walls.add(new Wall(475, 100, 675, 325, Color.BLUE));
        walls.add(new Wall(630, 400, 675, 950, Color.BLUE));
        walls.add(new Wall(675, 100, 930, 200, Color.BLUE));
        walls.add(new Wall(930, 100, 999, 950, Color.BLUE));
        this.width = 1000;
        this.height = 1000;
    }

    public void draw(Canvas canvas) {
        for (Wall wall : walls) {
            wall.draw(canvas);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
