package com.aldrinarciga.spacejumper.entity;

import com.aldrinarciga.spacejumper.Game;
import com.aldrinarciga.spacejumper.TextureManager;
import com.aldrinarciga.spacejumper.entitymanagers.GameScreenEntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class Player extends Entity {

    TextureRegion[] animationFrames;
    Animation animation;
    float elapsedTime;
    private static final int MAX_X = 150;
    private static final int MAX_X_2 = 200;
    private static final int DIFF = 22;
    private static final int X_GRAVITY = 150;
    private static final int Y_GRAVITY = -800;
    private static final int JUMP_GRAVITY = 800;
    private static final int JUMP_DIFF = 220;
    private int GROUND_HT = ((Ground.GROUND_HEIGHT) + TextureManager.GROUNDTEXT.getHeight()) - DIFF;
    private boolean canJump = true, maxXReached = false;
    private long jumpTime;

    //FOR PLAYER
    int ctr = 0;
    int ROW = 2;
    int COL = 5;
    int PLAYER_WIDTH =  TextureManager.PLAYERTEXT.getWidth() / COL;

    private GameScreenEntityManager entityManager;
    public Player(GameScreenEntityManager entityManager) {
        super(new Texture(TextureManager.PLAYER), new Vector2(-(TextureManager.PLAYERTEXT.getWidth() / 5), (Ground.GROUND_HEIGHT) + TextureManager.GROUNDTEXT.getHeight()), new Vector2(5,-5));
        TextureRegion[][] temp = TextureRegion.split(texture, PLAYER_WIDTH, TextureManager.PLAYERTEXT.getHeight() / ROW);
        animationFrames = new TextureRegion[ROW*COL];
        for(int x = 0; x < ROW; x++){
            for(int y = 0 ; y < COL; y++) {
                animationFrames[ctr++] = temp[x][y];
            }
        }

        animation = new Animation(1f/16f, animationFrames);
        this.entityManager = entityManager;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        //super.render(spriteBatch);
        elapsedTime += Gdx.graphics.getDeltaTime();
        assert(animation != null);
        spriteBatch.draw(animation.getKeyFrame(elapsedTime, true), position.x, position.y);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, PLAYER_WIDTH, texture.getHeight() / ROW);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched() && canJump){
            setDirection(X_GRAVITY, JUMP_GRAVITY);
            jumpTime = System.currentTimeMillis();
            canJump = false;
        }
    }

    @Override
    public void update() {
        handleInput();
        Game.getInstance().addScore();
        position.add(direction);
        if(position.y < (GROUND_HT)){
            canJump = true;
            position.y = GROUND_HT;
            setDirection(-X_GRAVITY, Y_GRAVITY);
        }

        if(!maxXReached) {
            if (position.x > MAX_X) {
                position.x = MAX_X;
            }

            if (position.x > MAX_X) {
                maxXReached = true;
            }
        }else{
            if (position.x < MAX_X) {
                position.x = MAX_X;
            }
        }



        if((System.currentTimeMillis() - jumpTime) > JUMP_DIFF) {
            setDirection(X_GRAVITY, Y_GRAVITY);
        }
    }
}
