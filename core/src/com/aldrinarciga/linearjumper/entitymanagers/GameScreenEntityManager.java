package com.aldrinarciga.linearjumper.entitymanagers;

import com.aldrinarciga.linearjumper.Game;
import com.aldrinarciga.linearjumper.MainGame;
import com.aldrinarciga.linearjumper.entity.Entity;
import com.aldrinarciga.linearjumper.entity.Obstacle;
import com.aldrinarciga.linearjumper.entity.Player;
import com.aldrinarciga.linearjumper.screen.MenuScreen;
import com.aldrinarciga.linearjumper.screen.ScreenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class GameScreenEntityManager extends EntityManager {

    public boolean gameOver = false;
    private BitmapFont font = new BitmapFont();

    @Override
    public void checkCollisions() {
        Player player = getPlayer();
        for(Obstacle obstacle : getObstacles()){
            if(obstacle.getBounds().overlaps(player.getBounds())){
                gameOver = true;
            }
        }
    }

    @Override
    public void update(){
        if(!gameOver) {
            for (int x = 0; x < entities.size; x++) {
                Entity entity = entities.get(x);
                entity.update();
            }
            checkCollisions();
        }
        if(Gdx.input.justTouched() && gameOver){
            ScreenManager.setScreen(new MenuScreen());
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch){
        for(int x = 0; x < entities.size; x++) {
            Entity entity = entities.get(x);
            entity.render(spriteBatch);
        }

        if(gameOver){
            font.setColor(Color.RED);
            font.draw(spriteBatch, "GAME OVER! PRESS SCREEN TO PLAY AGAIN.", (MainGame.WIDTH / 2) - 160, (MainGame.HEIGHT / 2) + 150);
        }
    }

    @Override
    public void removeEntity(Entity entity) {
        entities.removeValue(entity, false);
    }

    public Array<Obstacle> getObstacles(){
        Array<Obstacle> obstacles = new Array<Obstacle>();
        for(Entity entity : entities){
            if(entity instanceof Obstacle){
                obstacles.add((Obstacle) entity);
            }
        }
        return obstacles;
    }

    private Player getPlayer(){
        Player player = null;
        for(Entity entity : entities){
            if(entity instanceof Player){
                player = (Player) entity;
                break;
            }
        }
        return player;
    }
}
