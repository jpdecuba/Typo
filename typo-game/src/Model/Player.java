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


	public int getScore() {
		return this.score;
	}


    public Player() {

        score = 0;
        lives = 3;
        combo = 0;
        tempPoints = 0;
    }

    public void AwardPoints() {

	    synchronized (this) {
            score += (tempPoints);
            tempPoints = 0;
        }

    }

    public int getLives() {
	    synchronized (LIVES) {
            return this.lives;
        }
	}

	/**
	 *
	 * @param lives
	 */
	public void AddLives(int lives) {
	    synchronized (LIVES) {
            this.lives += lives;
        }
	}


	public boolean WrongKeypress(){
	    synchronized (LIVES){
            lives -= 1;
        }
	    if(lives <= 0){;

	        notifyObservers(false);
	        return false;
        }
        return  true;

    }


	public int getCombo() {
        synchronized (COMBO) {
            return this.combo;
        }
	}


	public void setCombo(int combo) {
	    synchronized (COMBO) {
            this.combo = combo;
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