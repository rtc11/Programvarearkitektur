package com.example.singleton;

import sheep.game.SpriteContainer;
import sheep.graphics.Image;
import java.util.HashMap;

/**
 * Created by tordly on 15.01.14.
 */
public class Pad extends Token{


    public Pad(Image img, float positionX, float positionY){
        super(img);
        setPosition(positionX, positionY);
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
