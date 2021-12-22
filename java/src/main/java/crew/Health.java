package crew;

public class Health {

    protected static final int MIN_CURRENT_HEALTH = 0;
    protected static final int MIN_MAX_HEALTH = 1;

    private int maxHealth;
    private int currentHealth;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void takeDamage(int damageTaken) {
        if ((currentHealth - damageTaken) <= MIN_CURRENT_HEALTH) {
            currentHealth = MIN_CURRENT_HEALTH;
        } else {
            currentHealth -= damageTaken;
        }
    }

    public void heal(int amountHealed) {
        if ((currentHealth + amountHealed) >= maxHealth) {
            currentHealth = maxHealth;
        } else {
            currentHealth += amountHealed;
        }
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Sets maxHealth to desired value. Access is protected because this method should not be used for anything other
     * than leveling up.
     */
    protected void setMaxHealth(int newMaxHealth) {
        if (newMaxHealth <= MIN_MAX_HEALTH) {
            throw new IllegalArgumentException(
                    String.format("Tried to set maxHealth to [%s]. maxHealth cannot be set below [%s]",
                            newMaxHealth,
                            MIN_MAX_HEALTH
                    )
            );
        }
        this.maxHealth = newMaxHealth;
    }

    /**
     * Sets currentHealth to desired value. Access is protected because this method should not be used for anything
     * other than leveling up. To modify currentHealth outside of leveling up use {@link #heal(int)} and
     * {@link #takeDamage(int)} instead.
     */
    protected void setCurrentHealth(int newCurrentHealth) {
        if (newCurrentHealth < MIN_CURRENT_HEALTH) {
            throw new IllegalArgumentException(
                    String.format(
                            "Tried to set currentHealth to [%s]. currentHealth cannot be set below [%s]",
                            newCurrentHealth,
                            MIN_CURRENT_HEALTH
                    )
            );
        }

        if (newCurrentHealth > maxHealth) {
            throw new IllegalArgumentException(
                    String.format(
                            "Tried to set currentHealth to [%s]. currentHealth cannot be greater than maxHealth [%s]",
                            newCurrentHealth,
                            maxHealth
                    )
            );
        }
        currentHealth = newCurrentHealth;
    }
}
