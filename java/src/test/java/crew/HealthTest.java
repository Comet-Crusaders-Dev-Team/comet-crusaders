package crew;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class HealthTest {

    private Health health;

    @Rule
    private final ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setup() {
        health = new Health(10);
    }

    @Test
    public void currentHealthCannotBeSetBelow0() {
        health.takeDamage(20);
        assertEquals(Health.MIN_CURRENT_HEALTH, health.getCurrentHealth());

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("currentHealth cannot be set below " + Health.MIN_CURRENT_HEALTH);
        health.setCurrentHealth(-10);
    }

    @Test
    public void currentHealthCannotBeSetAboveMaxHealth() {
        health.heal(20);
        assertEquals(health.getMaxHealth(), health.getCurrentHealth());

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("currentHealth cannot be greater than maxHealth " + health.getMaxHealth());
        health.setCurrentHealth(20);
    }

    @Test
    public void maxHealthCannotBeSetBelow1() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("currentHealth cannot be set below " + Health.MIN_MAX_HEALTH);
        health.setMaxHealth(0);
    }
}