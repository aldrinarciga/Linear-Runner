package com.aldrinarciga.linearjumper.entitymanagers;

import com.aldrinarciga.linearjumper.Game;
import com.aldrinarciga.linearjumper.entity.Entity;
import com.aldrinarciga.linearjumper.entity.Obstacle;
import com.aldrinarciga.linearjumper.entity.Player;
import com.aldrinarciga.linearjumper.screen.MenuScreen;
import com.aldrinarciga.linearjumper.screen.ScreenManager;
import com.badlogic.gdx.utils.Array;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class GameScreenEntityManager extends EntityManager {

    @Override
    public void checkCollisions() {
        Player player = getPlayer();
        for(Obstacle obstacle : getObstacles()){
            if(obstacle.getBounds().overlaps(player.getBounds())){
                ScreenManager.setScreen(new MenuScreen());
            }
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
