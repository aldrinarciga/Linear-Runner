package com.aldrinarciga.linearjumper.entity;

import com.aldrinarciga.linearjumper.Game;
import com.aldrinarciga.linearjumper.MainGame;
import com.aldrinarciga.linearjumper.TextureManager;
import com.aldrinarciga.linearjumper.camera.OrthoCamera;
import com.aldrinarciga.linearjumper.entitymanagers.GameScreenEntityManager;
import com.badlogic.gdx.Gdx;
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
    private static final int Y_GRAVITY = -550;
    private static final int JUMP_GRAVITY = 650;
    private static final int JUMP_DIFF = 250;
    private int GROUND_HT = ((Ground.GROUND_HEIGHT) + TextureManager.GROUND.getHeight()) - DIFF;
    private boolean canJump = true, maxXReached = false;
    private long jumpTime;

    //FOR PLAYER
    int ctr = 0;
    int ROW = 2;
    int COL = 5;
    int PLAYER_WIDTH =  TextureManager.PLAYER.getWidth() / COL;

    private GameScreenEntityManager entityManager;
    public Player(GameScreenEntityManager entityManager) {
        super(TextureManager.PLAYER, new Vector2(-(TextureManager.PLAYER.getWidth() / 5), (Ground.GROUND_HEIGHT) + TextureManager.GROUND.getHeight()), new Vector2(5,-5));
        TextureRegion[][] temp = TextureRegion.split(texture, PLAYER_WIDTH, TextureManager.PLAYER.getHeight() / ROW);
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
        return new Rectangle(position.x, position.y, PLAYER_WIDTH, TextureManager.PLAYER.getHeight() / ROW);
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
