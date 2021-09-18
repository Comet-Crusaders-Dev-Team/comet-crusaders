package system.gameplay;

import crew.Captain;
import crew.Crew;
import scenarios.crewcreation.CharacterCreation;
import system.audio.AudioPlayer;
import system.graphics.AsciiGraphics;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        AudioPlayer.loop("/audio/space-wave-bgm.wav");
        runIntroText();
        Crew crew = runCrewCreation();
    }

    private static void runIntroText() {
        System.out.println(AsciiGraphics.TITLE_BANNER);
        System.out.println("Welcome to Comet Crusaders...");
        String galaxyName = "Sussudio"; // You can change the name for the galaxy if you want
        System.out.println(
                "As a newly licensed star ship captain, you must be itching to start exploring the "
                        + galaxyName + " Galaxy!"
        );
        System.out.println("Before you set off, however, there are a few things we need to know about you...");
    }

    private static Crew runCrewCreation() {
        Captain playerCharacter = CharacterCreation.runCharacterCreation();
        // TODO: Add method to run ship creation, and another method to add one or two crew members to the party
        return new Crew(playerCharacter, new ArrayList<>());
    }
}
