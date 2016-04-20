package com.aldrinarciga.spacejumper.screen;

import com.aldrinarciga.spacejumper.MainGame;
import com.aldrinarciga.spacejumper.camera.OrthoCamera;
import com.aldrinarciga.spacejumper.entity.GameBg;
import com.aldrinarciga.spacejumper.entity.Ground;
import com.aldrinarciga.spacejumper.entity.PlayButton;
import com.aldrinarciga.spacejumper.entitymanagers.MenuScreenEntityManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class MenuScreen extends Screen {

    public static final int NUM_GROUNDS = 4;

    private OrthoCamera camera;
    private MenuScreenEntityManager entityManager;

    @Override
    public void create() {
        entityManager = new MenuScreenEntityManager();
        entityManager.addEntity(new GameBg(new Vector2(0, 0)));
        entityManager.addEntity(new GameBg(new Vector2(MainGame.WIDTH, 0)));
        for(int x = 0; x < NUM_GROUNDS; x++){
            entityManager.addEntity(new Ground(x));
        }
        camera = new OrthoCamera();
        entityManager.addEntity(new PlayButton(camera));
        camera.update();
        camera.resize();
    }

    @Override
    public void update() {
        camera.update();
        camera.resize();
        entityManager.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        entityManager.render(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.resize();
    }

    @Override
    public void dispose() {
        entityManager.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
