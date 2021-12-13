package crew;

public class Captain extends CrewMember {

    public Captain(String name, int physical, int agility, int blaster, int piloting, int repair, int charisma) {
        super(name, new Health(10), 1, physical, agility, blaster, piloting, repair, charisma);
    }
}
