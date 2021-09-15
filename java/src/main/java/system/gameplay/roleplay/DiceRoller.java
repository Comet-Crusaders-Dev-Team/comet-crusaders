package system.gameplay.roleplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DiceRoller {

    private final Random random;

    public DiceRoller() {
        random = new Random();
    }

    public List<Integer> rollAbilityScores() {
        //TODO: (A) This line is here so the compiler won't complain, remove it when you add the functionality for this method
        return new ArrayList<>();
    }

    public int roll20() {
        // Returns a random int between 1 and 20
        return random.nextInt(20) + 1;
    }

    private List<Integer> sortIntsHighToLow(List<Integer> list) {
        Collections.reverse(list);
        return list;
    }
}
