package Model;

import java.util.*;

public enum Difficulty {
    Beginner(1),
    Expert(2),
    Easy(3),
    Medium(4),
    Hard(5);

    private int value;
    private static Map map = new HashMap<>();

    Difficulty(int value) {
        this.value = value;
    }

    static {
        for (Difficulty difficulty : Difficulty.values()) {
            map.put(difficulty.value, difficulty);
        }
    }

    public static Difficulty valueOf(int difficulty) {
        return (Difficulty) map.get(difficulty);
    }

    public int getValue() {
        return value;
    }
}
