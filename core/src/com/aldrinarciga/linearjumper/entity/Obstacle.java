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
    private int GROUND_HT = ((Ground.GROUND_HEIGHT) + TextureManager.GROUNDTEXT.getHeight());;
    private static int OBSTACLE_SIZE = 100;
    private static final int NORMAL_SIZE = 100, NON_NORMAL_SIZE = 40;
    private GameScreenEntityManager entityManager;
    private boolean isNormal;

    public Obstacle(Texture texture, Vector2 position, GameScreenEntityManager entityManager, boolean isNormal) {
        super(texture, position, new Vector2(isNormal? NORMAL_SPEED : (float) (NORMAL_SPEED * 1.5), 0));
        this.entityManager = entityManager;
        if((new Random()).nextInt() % 2 == 0){
            int rnd = (int) (Math.random() * 7) + 4;
            entityManager.addEntity(new Obstacle(randomizeObstacle(),new Vector2(position.x + (texture.getWidth()* (rnd + 4)), GROUND_HT - 5), entityManager, rnd%2 == 0));
        }
        if(!isNormal){
            this.texture = new Texture("hedge.png");
        }
        this.isNormal = isNormal;

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        OBSTACLE_SIZE = isNormal ? NORMAL_SIZE : NON_NORMAL_SIZE;
        spriteBatch.draw(texture, position.x, position.y + (isNormal ? 0 : 10), OBSTACLE_SIZE, OBSTACLE_SIZE);
    }

    @Override
    public Rectangle getBounds() {
        OBSTACLE_SIZE = isNormal ? NORMAL_SIZE : NON_NORMAL_SIZE;
        int toMinus = 30;
        return new Rectangle(position.x + toMinus, position.y - (isNormal ? toMinus : 0), OBSTACLE_SIZE - ((isNormal ? toMinus : 0) * 2), OBSTACLE_SIZE - (isNormal ? toMinus : 0));
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update() {
        position.add(direction);
        if(position.x < -OBSTACLE_SIZE){
            if(isNormal){
                texture = randomizeObstacle();
            }
            position.x = MainGame.WIDTH * entityManager.getObstacles().size + 250;

            if(!GameScreenEntityManager.hasNotNormal){
                entityManager.addEntity(new Obstacle(Obstacle.randomizeObstacle(),new Vector2(MainGame.WIDTH * (entityManager.getObstacles().size + 1) + 250, GROUND_HT - 5), entityManager, false));
                GameScreenEntityManager.hasNotNormal = true;
            }
        }
    }

    public static Texture randomizeObstacle(){
        int rnd = (int) (Math.random() * 3) + 1;
        return new Texture(rnd == 1 ? TextureManager.OBS1 : rnd == 2 ? TextureManager.OBS2: rnd == 3 ? TextureManager.OBS3 : TextureManager.OBS4);
    }
}
