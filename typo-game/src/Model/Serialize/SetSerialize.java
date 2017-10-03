package Model.Serialize;

import Model.Database.DBSet;
import Model.Difficulty;
import Model.Repository.SetRepository;
import Model.Set;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class SetSerialize {
    public static boolean SaveSets()
    {
        try
        {
            SetRepository set = new SetRepository(new DBSet());

            List<Set> setsBeginner = set.GetSets(Difficulty.Beginner);
            List<Set> setsExpert = set.GetSets(Difficulty.Expert);
            if(setsBeginner == null || setsExpert == null)
                return false;

            File file = new File(System.getenv("APPDATA") + "\\Typo");
            file.mkdirs();
            if(Write(file.getCanonicalPath() + "\\BeginnerSets.ser", setsBeginner) && Write(file.getCanonicalPath() + "\\ExpertSets.ser", setsExpert))
                    return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean Write(String path, List<Set> sets)
    {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(sets);
            out.close();
            fileOut.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
