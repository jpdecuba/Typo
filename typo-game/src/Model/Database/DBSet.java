package Model.Database;

import Model.Repository.RepositoryInterface.ISetContext;
import Model.*;
import Model.Set;

import java.sql.*;
import java.util.*;

public class DBSet implements ISetContext{
    private Database database = new Database();

    @Override
    public List<Set> GetSets(Difficulty difficulty) {
        List<Set> sets = new ArrayList<>();
        try
        {
            String sql = "SELECT letters FROM Set WHERE Difficultyid = ?";
            PreparedStatement statement = database.connection().prepareStatement(sql);
            statement.setInt(1, difficulty.getValue());
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                sets.add(new Set(rs.getString(1)));
            }
            return sets;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
