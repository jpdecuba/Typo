package Model.Repository;

import Model.Database.DBSet;
import Model.Difficulty;
import Model.Set;
import Model.Repository.RepositoryInterface.ISetContext;

import java.util.List;

public class SetRepository {
    private ISetContext context;
    private DBSet dbSet;

    public SetRepository(ISetContext context)
    {
        this.context = context;
        dbSet = new DBSet();
    }

    public List<Set> GetSets(Difficulty difficulty)
    {
        return dbSet.GetSets(difficulty);
    }
}
