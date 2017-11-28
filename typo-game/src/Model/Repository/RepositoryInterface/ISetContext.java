package Model.Repository.RepositoryInterface;

import Model.Difficulty;
import Model.Set;

import java.util.List;

public interface ISetContext {
    List<Set> GetSets(Difficulty difficulty);
}
