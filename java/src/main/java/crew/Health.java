package crew;

public class Health {
    private int maxHealth;
    private int currentHealth;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void takeDamage(int damage) {
        if ((currentHealth - damage) <= 0) {
            currentHealth = 0;
        } else {
            currentHealth-= damage;
        }
    }

    public void heal(int amountHealed) {
        if ((currentHealth + amountHealed) >= maxHealth) {
            currentHealth = maxHealth;
        } else {
            currentHealth+= amountHealed;
        }
    }

    public int getMaxHealth()  {
        return this.maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setMaxHealth(int newMaxHealth) {
        this.maxHealth = newMaxHealth;
    }

    public void setCurrentHealth(int newCurrentHealth) {
        currentHealth =  newCurrentHealth;
    }
}
