package com.example.module;

import android.view.Window;
import sheep.game.Game;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MyActivity extends Activity {
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

    }
}
