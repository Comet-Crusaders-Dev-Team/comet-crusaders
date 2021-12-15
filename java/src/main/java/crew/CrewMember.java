package crew;

public abstract class CrewMember {

    private final String name;
    private int level;
    private Health health;
    private int physical;
    private int agility;
    private int blaster;
    private int piloting;
    private int repair;
    private int charisma;
    //    private Headgear headgear;
    //    private Armor armor;
    //    private Weapon weapon;


    protected CrewMember(
            String name,
            Health health,
            int level,
            int physical,
            int agility,
            int blaster,
            int piloting,
            int repair,
            int charisma
    ) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.physical = physical;
        this.agility = agility;
        this.blaster = blaster;
        this.piloting = piloting;
        this.repair = repair;
        this.charisma = charisma;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getPhysical() {
        return physical;
    }

    public int getAgility() {
        return agility;
    }

    public int getBlaster() {
        return blaster;
    }

    public int getPiloting() {
        return piloting;
    }

    public int getRepair() {
        return repair;
    }

    public int getCharisma() {
        return charisma;
    }

    public Health getHealth() { return health; }

    // TODO: method to run level up
}
