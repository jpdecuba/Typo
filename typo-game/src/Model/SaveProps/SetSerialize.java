package Model.SaveProps;

import Model.Database.DBSet;
import Model.DatabaseClient;
import Model.Difficulty;
import Model.Repository.SetRepository;
import Model.Set;

import java.io.*;
import java.util.List;

public class SetSerialize {
    public static boolean SaveSets()
    {
        try
        {
            DatabaseClient set = new DatabaseClient(null);

            List<Set> setsBeginner = set.getSet(Difficulty.Beginner);
            List<Set> setsExpert = set.getSet(Difficulty.Expert);
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

    public static List<Set> GetSets(Difficulty difficulty)
    {
        try
        {
            String path = System.getenv("APPDATA") + "\\Typo\\";
            if(difficulty == Difficulty.Beginner)
                path += "BeginnerSets.ser";
            else
                path += "ExpertSets.ser";
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Set> sets = (List<Set>) in.readObject();
            in.close();
            fileIn.close();
            return sets;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("List<Set> class not found");
            c.printStackTrace();
        }
        return null;
    }
}
