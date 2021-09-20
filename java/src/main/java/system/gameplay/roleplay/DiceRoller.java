package system.gameplay.roleplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class DiceRoller {

    private static final Random random = new Random();

    public static List<Integer> rollAbilityScores() {
        ArrayList<Integer> rollAbilityScoresList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            int topThreeSixSidedDie = topThreeSixSidedDie();
            rollAbilityScoresList.add(topThreeSixSidedDie);
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

    // TODO: Maybe move this method out into a separate utility class that will deal with common list functionality
    private static void sortIntsHighToLow(List<Integer> list) {
        list.sort(Collections.reverseOrder());
    }

    private DiceRoller() {}
}
