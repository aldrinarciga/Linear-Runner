package com.aldrinarciga.linearjumper.entitymanagers;

import com.aldrinarciga.linearjumper.entity.Entity;
import com.aldrinarciga.linearjumper.entity.Obstacle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created by perpetualwave on 19/04/16.
 */
public abstract class EntityManager {
    protected Array<Entity> entities = new Array<Entity>();

    public void update(){
        for(int x = 0; x < entities.size; x++) {
            Entity entity = entities.get(x);
            entity.update();
        }
        checkCollisions();
    }

    public void render(SpriteBatch spriteBatch){
        for(int x = 0; x < entities.size; x++) {
            Entity entity = entities.get(x);
            entity.render(spriteBatch);
        }
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public abstract void checkCollisions();

    public abstract void removeEntity(Entity entity);
}
