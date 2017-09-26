public class Player {

	private Int score;
	private Int lives;
	private Int combo;
	private Int tempPoints;
	private List<HighScore> highScores;

	public Player() {
		// TODO - implement Player.Player
		throw new UnsupportedOperationException();
	}

	public void AwardPoints() {
		// TODO - implement Player.AwardPoints
		throw new UnsupportedOperationException();
	}

	public Int getScore() {
		return this.score;
	}

	/**
	 * 
	 * @param score
	 */
	public void setScore(Int score) {
		this.score = score;
	}

	public Int getLives() {
		return this.lives;
	}

	/**
	 * 
	 * @param lives
	 */
	public void setLives(Int lives) {
		this.lives = lives;
	}

	public Int getCombo() {
		return this.combo;
	}

	/**
	 * 
	 * @param combo
	 */
	public void setCombo(Int combo) {
		this.combo = combo;
	}

	public Int getTempPoints() {
		return this.tempPoints;
	}

	/**
	 * 
	 * @param tempPoints
	 */
	public void setTempPoints(Int tempPoints) {
		this.tempPoints = tempPoints;
	}

}