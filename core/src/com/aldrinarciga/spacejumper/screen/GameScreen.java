package com.aldrinarciga.spacejumper.screen;

import com.aldrinarciga.spacejumper.Game;
import com.aldrinarciga.spacejumper.MainGame;
import com.aldrinarciga.spacejumper.TextureManager;
import com.aldrinarciga.spacejumper.camera.OrthoCamera;
import com.aldrinarciga.spacejumper.entity.GameBg;
import com.aldrinarciga.spacejumper.entity.Ground;
import com.aldrinarciga.spacejumper.entity.Obstacle;
import com.aldrinarciga.spacejumper.entity.Player;
import com.aldrinarciga.spacejumper.entitymanagers.GameScreenEntityManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class GameScreen extends Screen {

    public static final int NUM_GROUNDS = 4;

    private OrthoCamera camera;
    private GameScreenEntityManager entityManager;
    private long startTime;
    private int GROUND_HT = ((Ground.GROUND_HEIGHT) + TextureManager.GROUNDTEXT.getHeight());

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
        entityManager.addEntity(new Player(entityManager));
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

        if(System.currentTimeMillis() - startTime > 1000 && entityManager.getObstacles().size < 3){
            entityManager.addEntity(new Obstacle(Obstacle.randomizeObstacle(),new Vector2(MainGame.WIDTH * (entityManager.getObstacles().size + 1), GROUND_HT - 5), entityManager, true));
        }

        /*if(System.currentTimeMillis() % 2 == 0 && !GameScreenEntityManager.hasNotNormal){
            entityManager.addEntity(new Obstacle(Obstacle.randomizeObstacle(),new Vector2(MainGame.WIDTH * (entityManager.getObstacles().size + 1), GROUND_HT - 5), entityManager, false));
            GameScreenEntityManager.hasNotNormal = true;
        }*/
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
        entityManager.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
