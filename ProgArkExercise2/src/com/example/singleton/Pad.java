package com.example.singleton;

import sheep.game.SpriteContainer;
import sheep.graphics.Image;
import java.util.HashMap;

/**
 * Created by tordly on 15.01.14.
 */
public class Pad extends Token{

    private static volatile Pad instance = null;
    private static volatile HashMap<Integer, Pad> instances = null;

    private Pad(Image img){
        super(img);
    }

    /**
     * Get the instance of Pad with the name 'number'.
     * @param number - There can be more than once instance
     * 'number' is the name of the instance we want to return
     * @return the current instantiation of Pad or creates a new one
     */
    public static synchronized Pad getInstance(int number){

        //If no instances have been made before
        if(instance == null){
            Image img = new Image(R.drawable.left1);
            instance = new Pad(img);

            //Create the Map and add the instance to the list of instances
            instances = new HashMap<Integer, Pad>();
            instances.put(Integer.valueOf(number), instance);
        }

        //There does exist at least 1 instance
        else {

            //If no instance with name 'number' exist, create it
            if (!instances.containsValue(number)) {
                Image img = new Image(R.drawable.left1);
                instance = new Pad(img);

                instances.put(Integer.valueOf(number), instance);
            }
        }

        //Return the instance with name 'number'
        return instances.get((Integer)number);
    }

    @Override
    public void setParent(SpriteContainer parent) {
        super.setParent(parent);
    }

    @Override
    public void update(float dt){
        super.update(dt);
    }
}
