package Model.Repository.RepositoryInterface;

import Model.Difficulty;
import Model.Opportunity;

import java.util.List;

public interface IOpportunityContext {
    List<Opportunity> GetOpportunities(Difficulty difficulty);
}
