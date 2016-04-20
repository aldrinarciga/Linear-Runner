package com.aldrinarciga.spacejumper;

/**
 * Created by perpetualwave on 19/04/16.
 */
public class Game {
    private int score = 0;
    private int highScore = 0;
    private static Game instance;
    private boolean gameOver = false;

    public static Game getInstance() {
        if(instance == null)
            instance = new Game();
        return instance;
    }

    public void addScore(){
        score++;
        setHighScore();
    }

    public void resetGame(){
        gameOver = false;
        score = 0;
    }

    public String getScoreString() {
        return ""+score;
    }

    public String getHighScoreString() {
        return ""+highScore;
    }

    public void setHighScore() {
        if(score > highScore){
            highScore = score;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void gameOver() {
        this.gameOver = true;
    }
}
