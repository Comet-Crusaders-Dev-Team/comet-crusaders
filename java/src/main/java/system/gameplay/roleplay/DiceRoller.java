package system.gameplay.roleplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class DiceRoller {

    private static final Random random = new Random();

    public static List<Integer> rollAbilityScores() {
        //TODO: (A) This line is here so the compiler won't complain, remove it when you add the functionality for this method
        ArrayList<Integer> rollAbilityScoresList = new ArrayList<>();
        int topThreeSixSidedDie = topThreeSixSidedDie();
        for (int i = 1; i <= 6; i++) {
                rollAbilityScoresList.add(topThreeSixSidedDie);
                topThreeSixSidedDie = topThreeSixSidedDie();
        }

        // Sort scores to make it easier for player to keep track of how they're prioritizing their abilities
        sortIntsHighToLow(rollAbilityScoresList);
        return rollAbilityScoresList;
    }

    public static int roll6() {
        return random.nextInt(6) + 1;
    }

    public static int roll20() {
        return random.nextInt(20) + 1;
    }

    public static int topThreeSixSidedDie() {
        ArrayList<Integer> diceRollValues = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            diceRollValues.add(roll6());
        }

        sortIntsHighToLow(diceRollValues);

        diceRollValues.remove(3);
        int diceRollValuesSum = 0;

        for (int i: diceRollValues) {
            diceRollValuesSum += i;
        }

        return diceRollValuesSum;
    }

    private static void sortIntsHighToLow(List<Integer> list) {
        list.sort(Collections.reverseOrder());
    }

    private DiceRoller() {}
}
