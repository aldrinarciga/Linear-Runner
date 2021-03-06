package com.aldrinarciga.spacejumper.entity;

import com.aldrinarciga.spacejumper.TextureManager;
import com.aldrinarciga.spacejumper.screen.MenuScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class Ground extends Entity {

    private static final int TO_MINUS = 20;
    public static final int GROUND_HEIGHT = -40;

    public Ground(int pos) {
        super(new Texture(TextureManager.GROUND), new Vector2((pos * TextureManager.GROUNDTEXT.getWidth()) - TO_MINUS, GROUND_HEIGHT), new Vector2(NORMAL_SPEED, 0));
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update() {
        position.add(direction);
        if(position.x < -texture.getWidth()){
            position.x = (texture.getWidth() * (MenuScreen.NUM_GROUNDS - 1)) - TO_MINUS;
        }
    }
}
