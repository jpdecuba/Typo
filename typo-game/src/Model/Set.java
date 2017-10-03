package Model;

import java.util.ArrayList;

public class Set {
    private ArrayList<Letter> characters;

    public Set(String string){
        characters = new ArrayList<Letter>();
        for(int i = 0; i < string.length(); i++){
            characters.add(new Letter(string.substring(i, 1)));
        }
    }

    public ArrayList<Letter> getCharacters() {
        return characters;
    }
}
