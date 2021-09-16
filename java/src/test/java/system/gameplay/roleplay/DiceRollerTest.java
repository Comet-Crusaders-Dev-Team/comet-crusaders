package system.gameplay.roleplay;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests to verify that dice rolling methods work as expected. To account for the random outputs, the methods will be
 * called in a loop until all of the possible desired values have been returned. A return value that does not meet the
 * expected constraints will cause the test to fail.
 */
public class DiceRollerTest {

    private DiceRoller diceRoller;

    @Before
    public void setUp() {
        diceRoller = new DiceRoller();
    }

    @Test
    public void rollAbilityScoresReturnsAllValidAbilityScores() {
        List<Integer> valuesReturned = new ArrayList<>();
        int tries = 0;
        while (valuesReturned.size() < 16 && tries < 250) {
            tries++;
            List<Integer> abilityScoresRolled = diceRoller.rollAbilityScores();
            assertThat(abilityScoresRolled.size()).isEqualTo(6);

            for (Integer valueRolled : abilityScoresRolled) {
                assertThat(valueRolled).isBetween(3, 18);

                if (!valuesReturned.contains(valueRolled)) {
                    valuesReturned.add(valueRolled);
                }
            }
        }

        assertThat(valuesReturned)
                .containsExactlyInAnyOrder(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
    }

    @Test
    public void roll20ReturnsAllIntegersInRange() {
        List<Integer> valuesReturned = new ArrayList<>();

        int tries = 0;
        while (valuesReturned.size() < 20 && tries < 250) {
            tries++;
            int valueRolled = diceRoller.roll20();
            assertThat(valueRolled).isBetween(1, 20);

            if (!valuesReturned.contains(valueRolled)) {
                valuesReturned.add(valueRolled);
            }
        }

        assertThat(valuesReturned)
                .containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
    }
}