package Model;

public class Letter {
    private char character;
    private boolean typed;

    public Letter(char character){
        this.character = character;
    }

    public boolean type(char character){
        if(this.character == character){
            typed = true;
            return typed;
        }
        else{
            typed = false;
            return typed;
        }
    }

    public char getCharacter(){
        return character;
    }

    public boolean getTyped(){
        return typed;
    }
}
