package Model.Database;

import Model.Difficulty;
import Model.OppName;
import Model.Opportunity;
import Model.Repository.RepositoryInterface.IOpportunityContext;
import java.sql.*;
import java.util.*;

public class DBOpportunity implements IOpportunityContext {

    @Override
    public List<Opportunity> GetOpportunities(Difficulty difficulty) {
        List<Opportunity> opportunities = new ArrayList<>();
        try
        {
            String sql = "SELECT effect, Difficultyid FROM Opportunity WHERE Difficultyid <= ?";
            PreparedStatement statement = Database.connection().prepareStatement(sql);
            statement.setInt(1, difficulty.getValue());
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                OppName name;
                switch (rs.getString(1)) {
                    case "Reverse":
                        name = OppName.Reverse;
                        break;
                    case "ComboBonus":
                        name = OppName.ComboBonus;
                        break;
                    case "ComboPunish":
                        name = OppName.ComboPunish;
                        break;
                    case "ExtraLife":
                        name = OppName.ExtraLife;
                        break;
                    case "SpotLight":
                        name = OppName.Spotlight;
                        break;
                    default:
                        name = OppName.ExtraLife;
                        break;
                }
                opportunities.add(new Opportunity(name, Difficulty.valueOf(rs.getInt(2))));
            }
            return opportunities;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
