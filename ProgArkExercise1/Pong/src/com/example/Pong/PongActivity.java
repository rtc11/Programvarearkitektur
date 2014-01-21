package com.example.Pong;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import sheep.game.Game;

public class PongActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
