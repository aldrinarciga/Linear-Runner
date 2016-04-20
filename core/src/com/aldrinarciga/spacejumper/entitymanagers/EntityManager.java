package com.aldrinarciga.spacejumper.entitymanagers;

import com.aldrinarciga.spacejumper.entity.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

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

    public abstract void dispose();
}
