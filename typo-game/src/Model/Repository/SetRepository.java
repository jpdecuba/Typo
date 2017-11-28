package Model.Repository;

import Model.Difficulty;
import Model.Set;
import Model.Repository.RepositoryInterface.ISetContext;

import java.util.List;

public class SetRepository {
    private ISetContext context;

    public SetRepository(ISetContext context)
    {
        this.context = context;
    }

    public List<Set> GetSets(Difficulty difficulty)
    {
        return context.GetSets(difficulty);
    }
}
