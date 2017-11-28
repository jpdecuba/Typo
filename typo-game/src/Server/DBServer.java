package Server;

import Model.Database.DBHighScore;
import Model.Database.DBOpportunity;
import Model.Database.DBSet;
import Model.Difficulty;
import Model.Repository.HighScoreRepository;
import Model.Repository.OpportunityRepository;
import Model.Repository.SetRepository;
import Model.Set;
import Model.Shared.Request;
import Model.Shared.RequestType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class DBServer {

    private HighScoreRepository hsRep = new HighScoreRepository(new DBHighScore());

    private OpportunityRepository OppRep = new OpportunityRepository(new DBOpportunity());
    private SetRepository SetRep = new SetRepository(new DBSet());

    private List<Model.Opportunity> OppList;

    private Socket Socket;

    private List<Model.HighScore> Highscorelist;

    private List<Set> SetList;

    public DBServer(Socket socket) {
        this.Socket = socket;
    }

    public void DBGet(Request object){


        try {
            ObjectOutputStream output = new ObjectOutputStream(Socket.getOutputStream());

            RequestType msg = object.msg;
            Difficulty diff = object.diff;
            switch (msg){
                case HighScore:
                    Highscorelist = hsRep.GetHighScores();
                    output.writeObject(Highscorelist);
                case Opportunity:

                    OppList =  OppRep.GetOpportunities(diff);
                    output.writeObject(OppList);

                case Sets:
                    OppList =  OppRep.GetOpportunities(diff);
                    output.writeObject(OppList);
                default:
                    output.writeObject("Message needs to contain request enum");


            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }


    }


