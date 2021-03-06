package Model.Sockets;

import Model.Opportunity;
import Model.Player;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;

public class SocketModel implements Serializable{
    private Player player;
    private Opportunity opp;
    private String cmd;

    public SocketModel(Player player, Opportunity opp, @Nullable String cmd) {
        this.player = player;
        this.opp = opp;
        this.cmd = cmd;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Opportunity getOpp() {
        return opp;
    }

    public void setOpp(Opportunity opp) {
        this.opp = opp;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}
