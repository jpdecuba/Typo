package Model.Repository;

import Model.Database.DBHighScore;
import Model.HighScore;
import Model.Repository.RepositoryInterface.IHighScoreContext;

import java.util.List;

public class HighScoreRepository {
    private IHighScoreContext context;
    private DBHighScore dbHighScore;

    public HighScoreRepository(IHighScoreContext context)
    {
        this.context = context;
        dbHighScore = new DBHighScore();
    }

    public boolean Save(HighScore highScore)
    {
        return dbHighScore.Save(highScore);
    }

    public List<HighScore> GetHighScores()
    {
        return  dbHighScore.GetHighScores();
    }
}
