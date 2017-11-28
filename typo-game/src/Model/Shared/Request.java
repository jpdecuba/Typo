package Model.Shared;

import Model.Difficulty;

import java.io.Serializable;

public class Request implements Serializable {
    public Difficulty diff;

    public RequestType msg;

    public Request(Difficulty diff, RequestType msg) {
        this.diff = diff;
        this.msg = msg;
    }

}
