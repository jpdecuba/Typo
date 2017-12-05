package Model.Shared;

import Model.Difficulty;
import Model.HighScore;

import java.io.Serializable;

public class Request implements Serializable {
    public Difficulty diff;

    public RequestType msg;

    public HighScore score;

    public Request(Difficulty diff, RequestType msg) {
        this.diff = diff;
        this.msg = msg;
    }

    public Request(Difficulty diff, RequestType msg, HighScore score) {
        this.diff = diff;
        this.msg = msg;
        this.score = score;
    }

}
