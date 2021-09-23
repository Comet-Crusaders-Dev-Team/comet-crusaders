package scenarios.crewcreation;

import crew.Captain;
import system.gameplay.engine.io.DataPrinter;
import system.gameplay.engine.io.PlayerInputPrompter;
import system.gameplay.engine.io.SystemInputScannerSingleton;
import system.gameplay.roleplay.DiceRoller;

import java.util.*;

public final class CharacterCreation {

    private static final Scanner scanner = SystemInputScannerSingleton.getInstance();
    private static final PlayerInputPrompter playerInputPrompter = PlayerInputPrompter.getInstance();

    // TODO: Move these constants to an enum
    private static final String PHYSICAL = "Physical";
    private static final String AGILITY = "Agility";
    private static final String BLASTER = "Blaster";
    private static final String PILOTING = "Piloting";
    private static final String REPAIR = "Repair";
    private static final String CHARISMA = "Charisma";

    public static Captain runCharacterCreation() {
        // TODO: Decide character limit after implementing Lanterna
        System.out.println("First things first, what is your name...?");
        String name = scanner.nextLine();

        System.out.println(
                name + ", eh? Not the name I was expecting for a star ship captain, if I'm being honest with you..."
        );

        // TODO: Add a bit of flavor text describing each ability score, move into seperate describeAbilityScores method
        System.out.println("Anywho, Ability Scores are what really show what someone's made of!");



        List<Integer> unassignedAbilityScores = DiceRoller.rollAbilityScores();

        printMeanScoreFlavorText((int) unassignedAbilityScores.stream().mapToInt(i -> i).average().orElse(0));

        System.out.println("Looks like these are your ability scores: " + unassignedAbilityScores);


        Map<String, Integer> assignedAbilityScores = assignAbilityScores(unassignedAbilityScores);

        return new Captain(
                name,
                assignedAbilityScores.get(PHYSICAL),
                assignedAbilityScores.get(AGILITY),
                assignedAbilityScores.get(BLASTER),
                assignedAbilityScores.get(PILOTING),
                assignedAbilityScores.get(REPAIR),
                assignedAbilityScores.get(CHARISMA)
        );
    }

    private static void printMeanScoreFlavorText (int meanScore) {
        if (meanScore == 18) {
            System.out.println("What?!?!?");
            System.out.println("There's no way...");
            System.out.println("I can't believe it! You've truly won the genetic lottery!");
        } else if (meanScore >= 14) {
            System.out.println("Very impressive! You remind me of myself back in my younger days...");
        } else if (meanScore >= 10) {
            System.out.println("Too good to be bad, but too bad to be good. You must have something to prove.");
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
    }

    private static Map<String, Integer> assignAbilityScores(List<Integer> scoresRolled) {
        Map<String, Integer> abilityScoreMap = new LinkedHashMap<>();

        System.out.println("Well then, what might your ability scores be?");
        System.out.println("Press any key to roll the dice and determine your potential attributes...");
        scanner.nextLine();
        while (true) {
            abilityScoreMap.put(PHYSICAL, null);
            abilityScoreMap.put(AGILITY, null);
            abilityScoreMap.put(BLASTER, null);
            abilityScoreMap.put(PILOTING, null);
            abilityScoreMap.put(REPAIR, null);
            abilityScoreMap.put(CHARISMA, null);

            List<Integer> availableScores = new ArrayList<>(scoresRolled);
            for (Map.Entry<String, Integer> ability : abilityScoreMap.entrySet()) {
                String abilityName = ability.getKey();
                System.out.println("");
                System.out.println("Which score would you like to assign to your [" + abilityName + "] ability?");
                System.out.println("Enter the number corresponding to the value you wish to use...");

                int selectedScoreIndex = selectScore(availableScores);

                printAbilityScoreFlavorText(availableScores.get(selectedScoreIndex), abilityName);
                ability.setValue(availableScores.get(selectedScoreIndex));
                availableScores.remove(selectedScoreIndex);
            }

            DataPrinter.printStringIntMap(abilityScoreMap);

            // TODO: (J) Potential yes/no utility method after implementing Lanterna
            boolean scoresConfirmed =
                    playerInputPrompter.promptYesOrNo("Are these the ability scores you wish to use?");
            if (scoresConfirmed) {
                break;
            } else {
                System.out.println("Let's try that again then. From the top.");
            }
        }
        return abilityScoreMap;
    }

    private static void printAbilityScoreFlavorText(int abilityScoreValue, String abilityName) {
        System.out.println();
        switch (abilityName) {
            case "Physical":
                printFlavorPhysicalTextScore(abilityScoreValue);
                break;
            case "Agility":
                printFlavorAgilityTextScore(abilityScoreValue);
                break;
            case "Blaster":
                printFlavorBlasterTextScore(abilityScoreValue);
                break;
            case "Piloting":
                printFlavorPilotingTextScore(abilityScoreValue);
                break;
            case "Repair":
                printFlavorRepairTextScore(abilityScoreValue);
                break;
            case "Charisma":
                printFlavorCharismaTextScore(abilityScoreValue);
                break;
            default:
                System.out.println("How did you get here?");
                break;
        }
        System.out.println();
    }

    private static void printFlavorPhysicalTextScore (int abilityScoreValue){
        if (abilityScoreValue == 18) {
            // TODO: change bears to alien species once world is more fleshed out
            System.out.println("Holy smokes! You must wrestle with bears for fun!");
        } else if (abilityScoreValue >= 14) {
            System.out.println("With numbers like that, I might be willing to train with you sometime.");
        } else if (abilityScoreValue >= 10) {
            System.out.println(
                    "You look like you may have seen the inside of a metagalactic economy training gym or two."
            );
        } else if (abilityScoreValue == 9) {
            System.out.println("Perfectly average physical capabilities.");
        } else if (abilityScoreValue >= 7) {
            System.out.println("Hit some mass-affected apparatuses, nerd.");
        } else if (abilityScoreValue >= 3) {
            System.out.println("Have you ever even been outside? Good luck...");
        }
    }

    private static void printFlavorAgilityTextScore (int abilityScoreValue){
        if (abilityScoreValue == 18) {
            // TODO: change cobra to alien species once world is more fleshed out
            System.out.println("Whoa there! You must go bobbing for cobras instead of apples!");
        } else if (abilityScoreValue >= 14) {
            System.out.println("You've got some slick moves there!");
        } else if (abilityScoreValue >= 10) {
            System.out.println("You look like a bit of a dancer.");
        } else if (abilityScoreValue == 9) {
            System.out.println("Perfectly average agility-wise. Hmph.");
        } else if (abilityScoreValue >= 7) {
            System.out.println("You look sluggish... Are you okay?");
        } else if (abilityScoreValue >= 3) {
            System.out.println("Watching you move is like hoping a brick will run...");
        }
    }

    private static void printFlavorBlasterTextScore (int abilityScoreValue){
        if (abilityScoreValue == 18) {
            System.out.println("Yee haw! You could shoot my eyebrow off with those moves!");
        } else if (abilityScoreValue >= 14) {
            System.out.println("You use a blaster like it's a high range sniper pulse rifle!");
        } else if (abilityScoreValue >= 10) {
            System.out.println("You must hit the holo-range often.");
        } else if (abilityScoreValue == 9) {
            System.out.println("As average with a blaster as can be.");
        } else if (abilityScoreValue >= 7) {
            System.out.println("Keep your finger OUTSIDE of the holo-finger guard...");
        } else if (abilityScoreValue >= 3) {
            System.out.println("I think you're gonna need a lot more than adult supervision around a blaster, son.");
        }
    }

    private static void printFlavorPilotingTextScore (int abilityScoreValue){
        if (abilityScoreValue == 18) {
            System.out.println("Wowee! You could fit a strike ship through a garbage chute!");
        } else if (abilityScoreValue >= 14) {
            System.out.println("We should go for a race sometime, you've got some fancy tricks.");
        } else if (abilityScoreValue >= 10) {
            System.out.println("You could handle low-orbit well. Try it out sometime.");
        } else if (abilityScoreValue == 9) {
            System.out.println("Fairly average, but at least you can pilot a ship.");
        } else if (abilityScoreValue >= 7) {
            System.out.println("I've seen worse.");
        } else if (abilityScoreValue >= 3) {
            System.out.println("You had your hyperdrive license suspended for how long?!");
        }
    }

    private static void printFlavorRepairTextScore (int abilityScoreValue){
        if (abilityScoreValue == 18) {
            System.out.println("Golly! You could fix things before they even break!");
        } else if (abilityScoreValue >= 14) {
            System.out.println("Impressive... Now listen, I've got this sound coming from my kinetic barriers..");
        } else if (abilityScoreValue >= 10) {
            System.out.println("Spent a summer working in the engineering bay of a Cruiser, eh?");
        } else if (abilityScoreValue == 9) {
            System.out.println("At least I can trust you not to mess things up.");
        } else if (abilityScoreValue >= 7) {
            System.out.println("Guess you skipped that module at the Academy.");
        } else if (abilityScoreValue >= 3) {
            System.out.println("Give that wrench back right now.");
        }
    }

    private static void printFlavorCharismaTextScore (int abilityScoreValue){
        if (abilityScoreValue == 18) {
            System.out.println("Wowzers! You could charm the pants off of me!");
        } else if (abilityScoreValue >= 14) {
            System.out.println("You must have no trouble getting into those selective Nebular clubs.");
        } else if (abilityScoreValue >= 10) {
            System.out.println("Had more than a few ladies hoping you'd take 'em for a night-time cruise, eh?");
        } else if (abilityScoreValue == 9) {
            System.out.println("Average, but it's better than some can say.");
        } else if (abilityScoreValue >= 7) {
            System.out.println("You're not convincing anyone.");
        } else if (abilityScoreValue >= 3) {
            System.out.println(
                    "I probably wouldn't even notice you exist, if I weren't being payed to talk to you..."
            );
        }
    }

    private static int selectScore (List<Integer> availableScores) {
        // TODO: Depending on how Lanterna is set up, we should potentially extract the following code into a
        //  utility method for players to select a choice from an ordered list
        DataPrinter.printAsOrderedList(availableScores);

        Integer selectedScore = null;
        while (selectedScore == null) {
            String invalidInputMessage = "Please enter a number from 1 to " + availableScores.size() + ".";

            try {
                selectedScore = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(invalidInputMessage);
                continue;
            }
            if (selectedScore < 1 || selectedScore > availableScores.size()) {
                System.out.println(invalidInputMessage);
                selectedScore = null;
            }
        }
        return selectedScore - 1;
        // Subtracting 1 to match selection to desired index for the selected value
    }

    private CharacterCreation() {}
}

