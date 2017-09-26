package Model;

import java.time.LocalDate;

public class HighScore {
    private String name;
    private int score;
    private Difficulty diff;
    private LocalDate date;

    public HighScore(String name, int score, Difficulty diff, LocalDate date) {
        this.name = name;
        this.score = score;
        this.diff = diff;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
