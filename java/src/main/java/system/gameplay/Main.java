package system.gameplay;

import crew.Captain;
import crew.Crew;
import system.gameplay.roleplay.DiceRoller;
import system.graphics.AsciiGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        DiceRoller diceRoller =  new DiceRoller();

        runIntroText();
        Crew crew = runCrewCreation(scanner, diceRoller);
    }

    private static void runIntroText() throws InterruptedException {
        System.out.println(AsciiGraphics.TITLE_BANNER);
        System.out.println("Welcome to Comet Crusaders...");
        String galaxyName = "Sussudio"; // You can change the name for the galaxy if you want
        System.out.println(
                "As a newly licensed star ship captain, you must be itching to start exploring the "
                        + galaxyName + " Galaxy!"
        );
        System.out.println("Before you set off, however, there are a few things we need to know about you...");
    }

    private static Crew runCrewCreation(Scanner scanner, DiceRoller diceRoller) {
        Captain playerCharacter = runCharacterCreation(scanner, diceRoller);
        // TODO: Add method to run ship creation, and another method to add one or two crew members to the party
        return new Crew(playerCharacter, new ArrayList<>());
    }

    private static Captain runCharacterCreation(Scanner scanner, DiceRoller diceRoller) {
        System.out.println("First things first, what is your name...?");
        String name = scanner.nextLine();

        System.out.println(
                name + ", eh? Not the name I was expecting for a star ship captain, if I'm being honest with you..."
        );

        System.out.println("Anywho, Ability Scores are what really show what someone's made of!");
        // TODO: Add a bit of flavor text describing each ability score

        System.out.println("Well then, what might your ability scores be?");
        System.out.println("Press any key to roll the dice and determine your potential attributes...");
        scanner.nextLine();

        List<Integer> unassignedAbilityScores = diceRoller.rollAbilityScores();

        int meanScore = (int) unassignedAbilityScores.stream().mapToInt(i -> i).average().orElse(0);

        System.out.println("Looks like these are your ability scores: " + unassignedAbilityScores);

        if (meanScore == 18) {
            System.out.println("What?!?!?");
            System.out.println("There's no way...");
            System.out.println("I can't believe it! You've truly won the genetic lottery!");
        } else if (meanScore >= 14) {
            System.out.println("Very impressive! You remind me of myself back in my younger days...");
        } else if (meanScore >= 10) {
            System.out.println("Pretty good, you definitely have some potential in there somewhere...");
        } else if (meanScore == 9) {
            System.out.println("I must say, you're exceptionally average.");
            System.out.println("You're like a ruler for the rest of the universe to measure themselves against...");
        } else if (meanScore >= 7) {
            System.out.println("You're a bit below average, but I guess you've never let that stop you before...");
        } else if (meanScore >= 4) {
            System.out.println("These stats are quite low... life must be a bit of a struggle for you, huh?");
        } else {
            System.out.println("Wow... I don't think I've ever seen anything quite so terrible before.");
            System.out.println("Just so you know, there's no shame in restarting the game.");
            System.out.println("I probably would, if I were you...");
        }

        // TODO: (A) Have player assign their ability scores

        int physical;
        int agility;
        int blaster;
        int piloting;
        int repair;
        int charisma;

        return null;
        /* TODO: (A)
        The above line is here so you can run the dice tests, otherwise the compiler would complain about using values
        that haven't been set. Delete the line above and uncomment the line below when you get the dice tests passing
        and you're ready to start adding the functionality for the player to set their ability scores using the values
        that they rolled.
        */
        // return new Captain(name, physical, agility, blaster, piloting, repair, charisma);
    }
}
