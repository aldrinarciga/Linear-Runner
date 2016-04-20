package com.aldrinarciga.linearjumper.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 19/04/16.
 */
public abstract class Entity {

    public static final int NORMAL_SPEED = -8;

    protected Texture texture;
    protected Vector2 position, direction;

    public Entity(Texture texture, Vector2 position, Vector2 direction) {
        this.texture = texture;
        this.position = position;
        this.direction = direction;
    }

    public abstract void handleInput();
    public abstract void update();

    public void dispose(){
        texture.dispose();
    }

    public void render(SpriteBatch spriteBatch){
        spriteBatch.draw(texture, position.x, position.y);
    }

    public Rectangle getBounds(){
        return new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setDirection(float x, float y){
        direction.set(x, y);
        direction.scl(Gdx.graphics.getDeltaTime());
    }
}
