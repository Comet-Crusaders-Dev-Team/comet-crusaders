package crew;

import java.util.List;

public class Crew {

    private final Captain captain;
    private final List<CrewMember> crewMembers;
    //TODO: Add ship


    public Crew(Captain captain, List<CrewMember> crewMembers) {
        this.captain = captain;
        this.crewMembers = crewMembers;
    }

    public Captain getCaptain() {
        return captain;
    }

    public List<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    //TODO: Add methods to add/remove crew members, method to get specific crew member
}
