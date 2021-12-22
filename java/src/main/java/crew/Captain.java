package crew;

public class Captain extends CrewMember {

    private static final int DEFAULT_HEALTH = 10;

    public Captain(String name, int physical, int agility, int blaster, int piloting, int repair, int charisma) {
        super(name, new Health(DEFAULT_HEALTH), 1, physical, agility, blaster, piloting, repair, charisma);
    }
}
