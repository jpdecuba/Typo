package Model.Repository;

import Model.Database.DBOpportunity;
import Model.Difficulty;
import Model.Opportunity;
import Model.Repository.RepositoryInterface.IOpportunityContext;

import java.util.*;

public class OpportunityRepository {
    private IOpportunityContext context;
    private DBOpportunity dbOpportunity;

    public OpportunityRepository(IOpportunityContext context)
    {
        this.context = context;
        dbOpportunity = new DBOpportunity();
    }

    public List<Opportunity> GetOpportunities(Difficulty difficulty)
    {
        return dbOpportunity.GetOpportunities(difficulty);
    }
}
