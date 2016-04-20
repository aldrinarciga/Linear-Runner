package com.aldrinarciga.spacejumper.entitymanagers;

import com.aldrinarciga.spacejumper.entity.Entity;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class MenuScreenEntityManager extends EntityManager {
    @Override
    public void checkCollisions() {

    }

    @Override
    public void removeEntity(Entity entity) {

    }

    @Override
    public void dispose() {
        for(Entity entity : entities){
            entity.dispose();
        }
    }
}
