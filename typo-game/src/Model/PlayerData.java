package Model;

import java.io.Serializable;

public class PlayerData implements Serializable {
    private int score;
    private int lives;

    public String getIpAddress() {
        return ipAddress;
    }

    private String ipAddress;

    public PlayerData(int score, int lives, String ipAddress){
        this.score = score;
        this.lives = lives;
        this.ipAddress = ipAddress;
    }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getLives() { return lives; }
    public void setLives(int lives) { this.lives = lives; }
}
