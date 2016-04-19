package com.aldrinarciga.linearjumper.screen;

import com.aldrinarciga.linearjumper.Game;
import com.aldrinarciga.linearjumper.MainGame;
import com.aldrinarciga.linearjumper.TextureManager;
import com.aldrinarciga.linearjumper.camera.OrthoCamera;
import com.aldrinarciga.linearjumper.entity.GameBg;
import com.aldrinarciga.linearjumper.entity.Ground;
import com.aldrinarciga.linearjumper.entity.Obstacle;
import com.aldrinarciga.linearjumper.entity.Player;
import com.aldrinarciga.linearjumper.entitymanagers.GameScreenEntityManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class GameScreen extends Screen {

    public static final int NUM_GROUNDS = 10;

    private OrthoCamera camera;
    private GameScreenEntityManager entityManager;
    private long startTime;
    private int GROUND_HT = ((Ground.GROUND_HEIGHT) + TextureManager.GROUND.getHeight());

    private BitmapFont font;

    @Override
    public void create() {
        camera = new OrthoCamera();
        camera.update();
        camera.resize();
        entityManager = new GameScreenEntityManager();
        entityManager.addEntity(new GameBg(new Vector2(0, 0)));
        entityManager.addEntity(new GameBg(new Vector2(MainGame.WIDTH, 0)));
        //For Ground
        for(int x = 0; x < NUM_GROUNDS; x++){
            entityManager.addEntity(new Ground(x));
        }
        //For Player
        entityManager.addEntity(new Player(camera));
        startTime = System.currentTimeMillis();

        font = new BitmapFont();
        font.setColor(Color.RED);

        Game.getInstance().resetGame();
    }

    @Override
    public void update() {
        camera.update();
        camera.resize();
        entityManager.update();

        if(System.currentTimeMillis() - startTime > 1000 && entityManager.getObstacles().size < 5){
            int rnd = (int) (Math.random() * 2);
            Texture texture = rnd == 0 ? TextureManager.OBS1 : rnd == 1 ? TextureManager.OBS2: TextureManager.OBS3;
            entityManager.addEntity(new Obstacle(texture,new Vector2(MainGame.WIDTH * (entityManager.getObstacles().size + 1), GROUND_HT - 5), entityManager));
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        entityManager.render(spriteBatch);
        font.draw(spriteBatch, "Score: " + Game.getInstance().getScoreString(), 20, MainGame.HEIGHT - 20);
        font.draw(spriteBatch, "High Score: " +Game.getInstance().getHighScoreString(), 20 , MainGame.HEIGHT - 40);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.resize();
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
