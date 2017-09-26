package Model;

public class HighScore {
    private String name;
    private int score;
    private Difficulty diff;

    public HighScore(String name, int score, Difficulty diff) {
        this.name = name;
        this.score = score;
        this.diff = diff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Difficulty getDiff() {
        return diff;
    }

    public void setDiff(Difficulty diff) {
        this.diff = diff;
    }
}
