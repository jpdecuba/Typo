package Model.Database;

import Model.*;
import Model.Repository.RepositoryInterface.IHighScoreContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHighScore implements IHighScoreContext {
    private Database database = new Database();

    @Override
    public boolean Save(HighScore highScore)
    {
        try
        {
            String sql = "INSERT INTO HighScore (Difficultyid, name, score, date) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = database.connection().prepareStatement(sql);
            statement.setInt(1, highScore.getDiff().getValue());
            statement.setString(2, highScore.getName());
            statement.setInt(3, highScore.getScore());
            statement.setDate(4, java.sql.Date.valueOf(highScore.getDate()));
            statement.execute();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<HighScore> GetHighScores()
    {
        List<HighScore> highScores = new ArrayList<>();
        try
        {
            String sql = "SELECT name, score, Difficultyid, date FROM HighScore";
            PreparedStatement statement = database.connection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                highScores.add(new HighScore(rs.getString(1), rs.getInt(2), Difficulty.valueOf(rs.getInt(3)), rs.getDate(4).toLocalDate()));
            }
            return highScores;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
