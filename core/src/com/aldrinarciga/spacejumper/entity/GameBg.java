package com.aldrinarciga.spacejumper.entity;

import com.aldrinarciga.spacejumper.MainGame;
import com.aldrinarciga.spacejumper.TextureManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class GameBg extends Entity{
    public GameBg(Vector2 position) {
        super(new Texture(TextureManager.BG), position, new Vector2(-1, 0));
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update() {
        position.add(direction);
        if(position.x < -MainGame.WIDTH){
            position.x = MainGame.WIDTH;
        }
    }
}
