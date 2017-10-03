package Model;

import java.io.Serializable;

public class Letter implements Serializable {
    private String character;
    private boolean typed;

    public Letter(String character){
        this.character = character;
    }

    public boolean type(String character){
        if(this.character == character){
            typed = true;
            return typed;
        }
        else{
            typed = false;
            return typed;
        }
    }

    public String getCharacter(){
        return character;
    }

    public boolean getTyped(){
        return typed;
    }
}