package com.example.singleton;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import sheep.game.Game;

public class MyActivity extends Activity {

    public static int WIDTH = 0, HEIGHT = 0;
    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);

        //Remove title from screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Create the game
        Game game = new Game(this, null);

        //Push the main state
        game.pushState(new GameState());

        //View the game
        setContentView(game);

        //Get the dimensions of the screen
        getScreenDimensions();

    }

    private void getScreenDimensions(){
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;

        WIDTH = width;
        HEIGHT = height;

        Log.i("SCREEN SIZE:", "WIDTH: " + width + ", HEIGHT: " + height);
    }
}
