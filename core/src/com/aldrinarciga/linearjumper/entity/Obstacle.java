package com.aldrinarciga.linearjumper.entity;

import com.aldrinarciga.linearjumper.MainGame;
import com.aldrinarciga.linearjumper.TextureManager;
import com.aldrinarciga.linearjumper.entitymanagers.GameScreenEntityManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import javax.swing.text.html.ObjectView;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class Obstacle extends Entity {
    private int GROUND_HT = ((Ground.GROUND_HEIGHT) + TextureManager.GROUND.getHeight());
    private static final int OBSTACLE_SIZE = 100;
    private GameScreenEntityManager entityManager;

    public Obstacle(Texture texture, Vector2 position, GameScreenEntityManager entityManager) {
        super(texture, position, new Vector2(NORMAL_SPEED, 0));
        this.entityManager = entityManager;
        if((new Random()).nextInt() % 2 == 0){
            int rnd = (int) (Math.random() * 2);
            Texture t = rnd == 0 ? TextureManager.OBS1 : rnd == 1 ? TextureManager.OBS2: TextureManager.OBS3;
            entityManager.addEntity(new Obstacle(t,new Vector2(position.x + (texture.getWidth()* (rnd + 5)), GROUND_HT - 5), entityManager));
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, position.x, position.y, OBSTACLE_SIZE, OBSTACLE_SIZE);
    }

    @Override
    public Rectangle getBounds() {
        int toMinus = 30;
        return new Rectangle(position.x + toMinus, position.y - toMinus, OBSTACLE_SIZE - (toMinus * 2), OBSTACLE_SIZE - toMinus);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update() {
        position.add(direction);
        if(position.x < -OBSTACLE_SIZE){
            entityManager.removeEntity(this);
            int rnd = (int) (Math.random() * 2);
            Texture texture = rnd == 0 ? TextureManager.OBS1 : rnd == 1 ? TextureManager.OBS2: TextureManager.OBS3;
            entityManager.addEntity(new Obstacle(texture,new Vector2(MainGame.WIDTH * (entityManager.getObstacles().size + 1), GROUND_HT - 5), entityManager));
        }
    }
}
