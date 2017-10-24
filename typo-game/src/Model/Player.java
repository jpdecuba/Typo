package Model;

import java.util.ArrayList;
import java.util.Observable;

public class Player extends Observable{

	private int score;
	private int lives;
	private int combo;
	private int tempPoints;
	private ArrayList<HighScore> highScores;

	private Object LIVES = new Object();
    private Object COMBO = new Object();

    public ComboTimer ComboTimer = new ComboTimer();

	public int getScore() {
		return this.score;
	}


    public Player() {
        score = 0;
        lives = 3;
        combo = 1;
        tempPoints = 0;
    }
    // Adding tempPoints to to score and set tempPoints on zero
    public void AwardPoints() {

        synchronized (this) {
            score += (tempPoints);
            tempPoints = 0;
        }

    }
    public synchronized int getLives() {

            return this.lives;
	}


	public void AddLives(int lives) {
	    synchronized (LIVES) {
            this.lives += lives;
        }
	}


	public boolean WrongKeypress(){
	    synchronized (LIVES){
            lives -= 1;
        }
	    if(lives <= 0){

	        this.setChanged();
	        this.notifyObservers(false);
	        return false;
        }
        return  true;

    }


	public synchronized int getCombo() {
            return this.combo;

	}


	public void setCombo(int combo) {
	    synchronized (COMBO) {
            if (combo >= 1) {
                this.combo = combo;
            }
        }
	}

	public int getTempPoints() {
		return this.tempPoints;
	}

	/**
	 *
	 * @param tempPoints
	 */
	public void setTempPoints(int tempPoints) {
	    synchronized(this) {

            this.tempPoints += tempPoints * combo;

        }
	}

    public ArrayList<HighScore> getHighScores() {
        return highScores;
    }

    public void setHighScores(ArrayList<HighScore> highScores) {
        this.highScores = highScores;
    }


}