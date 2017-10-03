package Model;

public class Set {
    private Letter[] characters;

    public Set(String string){
        characters = new Letter[string.length()];
        for(int i = 0; i < string.length(); i++){
            characters[i] = new Letter(string.substring(i, 1));
        }
    }

    public Letter[] getCharacters() {
        return characters;
    }
}
