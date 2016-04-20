package com.aldrinarciga.linearjumper.entity;

import com.aldrinarciga.linearjumper.MainGame;
import com.aldrinarciga.linearjumper.TextureManager;
import com.aldrinarciga.linearjumper.camera.OrthoCamera;
import com.aldrinarciga.linearjumper.screen.GameScreen;
import com.aldrinarciga.linearjumper.screen.ScreenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class PlayButton extends Entity {

    private static final int TOUCH_BOUNDS = 10;
    private OrthoCamera camera;

    public PlayButton(OrthoCamera camera) {
        super(new Texture(TextureManager.PLAY_BUTTON), new Vector2((MainGame.WIDTH / 2) - TextureManager.PLAY_BUTTONTEXT.getWidth() / 2, MainGame.HEIGHT / 2), new Vector2(0,0));
        this.camera = camera;
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 vec=new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0);
            camera.unproject(vec);
            Rectangle touchRect = new Rectangle(vec.x, vec.y, TOUCH_BOUNDS, TOUCH_BOUNDS);
            if(touchRect.overlaps(getBounds())){
                ScreenManager.setScreen(new GameScreen());
            }
        }
    }

    @Override
    public void update() {
        handleInput();
        position.add(direction);
    }
}
