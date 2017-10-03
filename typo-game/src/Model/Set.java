package Model;

import java.util.ArrayList;

public class Set {
    private ArrayList<Letter> characters;

    public Set(String string){
        characters = new ArrayList<Letter>();
        for(int i = 0; i < string.length(); i++){
<<<<<<< HEAD
            characters[i] = new Letter(string.substring(i, 1));
=======
            characters.add(new Letter(string.substring(i)));
>>>>>>> da46798fc91964feabecbfa22933d985001a5a96
        }
    }

    public ArrayList<Letter> getCharacters() {
        return characters;
    }
}
