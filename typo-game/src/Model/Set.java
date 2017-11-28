package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Set implements Serializable {
    private ArrayList<Letter> characters;

    public Set(String string){
        characters = new ArrayList<Letter>();
        for(int i = 0; i < string.length(); i++){
            characters.add(new Letter(string.substring(i, i+1)));
        }
    }

    public ArrayList<Letter> getCharacters() {
        return characters;
    }
}
