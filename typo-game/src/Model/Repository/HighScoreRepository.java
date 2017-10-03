package Model.Repository;

import Model.HighScore;
import Model.Repository.RepositoryInterface.IHighScoreContext;

import java.util.List;

public class HighScoreRepository {
    private IHighScoreContext context;

    public HighScoreRepository(IHighScoreContext context)
    {
        this.context = context;
    }

    public boolean Save(HighScore highScore)
    {
        return context.Save(highScore);
    }

    public List<HighScore> GetHighScores()
    {
        return  context.GetHighScores();
    }
}
