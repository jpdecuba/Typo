package Model.Repository.RepositoryInterface;

import Model.HighScore;

import java.util.List;

public interface IHighScoreContext {
    boolean Save(HighScore highScore);
    List<HighScore> GetHighScores();
}
