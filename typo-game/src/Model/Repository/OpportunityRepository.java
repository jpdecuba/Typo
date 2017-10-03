package Model.Repository;

import Model.Difficulty;
import Model.Opportunity;
import Model.Repository.RepositoryInterface.IOpportunityContext;

import java.util.*;

public class OpportunityRepository {
    private IOpportunityContext context;

    public OpportunityRepository(IOpportunityContext context)
    {
        this.context = context;
    }

    public List<Opportunity> GetOpportunities(Difficulty difficulty)
    {
        return context.GetOpportunities(difficulty);
    }
}
