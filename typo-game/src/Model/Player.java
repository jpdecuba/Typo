package Model;

import java.util.ArrayList;

public class Player {

	private int score;
	private int lives;
	private int combo;
	private int tempPoints;
	private ArrayList<HighScore> highScores;

	private Object LIVES = new Object();




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
		return this.lives;
	}

	/**
	 * 
	 * @param lives
	 */
	public void AddLives(int lives) {
		this.lives += lives;
	}


	public boolean WrongKeypress(){
	    synchronized (LIVES){
            lives -= 1;
        }


	    if(lives <= 0){

	        return false;

        }

        return  true;

    }


	public int getCombo() {
		return this.combo;
	}


	public void setCombo(int combo) {
		this.combo = combo;
	}

	public int getTempPoints() {
		return this.tempPoints;
	}

	/**
	 * 
	 * @param tempPoints
	 */
	public void setTempPoints(int tempPoints) {
		this.tempPoints += tempPoints * combo ;
	}

}