package scenarios.crewcreation;

import crew.Captain;
import system.gameplay.engine.SystemInputScannerSingleton;
import system.gameplay.roleplay.DiceRoller;

import java.util.List;
import java.util.Scanner;

public final class CharacterCreation {

    private static final Scanner scanner = SystemInputScannerSingleton.getInstance();

    public static Captain runCharacterCreation() {
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

        List<Integer> unassignedAbilityScores = DiceRoller.rollAbilityScores();
        // TODO: Implement functionality of the rollAbilityScore method

        int meanScore = (int) unassignedAbilityScores.stream().mapToInt(i -> i).average().orElse(0);

        System.out.println("Looks like these are your ability scores: " + unassignedAbilityScores);

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

        // TODO: (A) Have player assign their ability scores

        Integer[] abilityScoreValues = askAbilityScores(unassignedAbilityScores);

        int physical = abilityScoreValues[0];
        int agility = abilityScoreValues[1];
        int blaster = abilityScoreValues[2];
        int piloting = abilityScoreValues[3];
        int repair = abilityScoreValues[4];
        int charisma = abilityScoreValues[5];


        //return null;
        /* TODO: (A)
        The above line is here so you can run the dice tests, otherwise the compiler would complain about using values
        that haven't been set. Delete the line above and uncomment the line below when you get the dice tests passing
        and you're ready to start adding the functionality for the player to set their ability scores using the values
        that they rolled.
        */
        return new Captain(name, physical, agility, blaster, piloting, repair, charisma);
    }

    public static void printAbilityScores(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + " " + list.get(i));
        }
    }

    public static Integer[] askAbilityScores(List<Integer> list) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        int end = 0;
        Integer[] abilityScoreValues = new Integer[6];
        String[] abilityScoreNames = new String[6];
        abilityScoreNames[0] = ("Physical");
        abilityScoreNames[1] = ("Agility");
        abilityScoreNames[2] = ("Blaster");
        abilityScoreNames[3] = ("Piloting");
        abilityScoreNames[4] = ("Repair");
        abilityScoreNames[5] = ("Charisma");
        String abilityScoreName = abilityScoreNames[i];
        while (end == 0) {
            while (i < 6) {
                abilityScoreName = abilityScoreNames[i];
                System.out.println("");
                System.out.println("Which score would you like to assign to your [" + abilityScoreName + "] ability?");
                System.out.println("Enter the number corresponding to the value you wish to use...");
                printAbilityScores(list);
                int abilityScoreSelection = Integer.parseInt(scanner.nextLine());
                if (abilityScoreSelection < 1 || abilityScoreSelection > list.size()) {
                    System.out.println("Please enter a valid selection.");
                    continue;
                }
                abilityScoreFlavorText(list.get(abilityScoreSelection - 1), abilityScoreName);
                abilityScoreValues[i] = list.get(abilityScoreSelection-1);
                list.remove(abilityScoreSelection - 1);
                i++;
            }

            printAbilityScoresWithNames(abilityScoreNames, abilityScoreValues);
            System.out.println("Are these the ability scores you wish to use? (yes/no)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                end = 1;
                break;
            } else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
                System.out.println("Let's try that again then. From the top.");
                for (i = 0; i < 6; i++) {
                    list.add(abilityScoreValues[i]);
                    /*
                    The following line will cause your code to break right now, because sortIntsHighToLow is a
                    private method in DiceRoller it can no longer be accessed by this method, since we moved it out of
                    the class. You won't need this line to have the list sorted properly when restarting score
                    assignment after you implement the review feedback.
                     */
                    // DiceRoller.sortIntsHighToLow(list);
                }
                i = 0;
            }
            else {
                System.out.println("Please answer with (yes/no).");
            }
        }
        return abilityScoreValues;
    }

    public static void abilityScoreFlavorText(int abilityScoreValue, String abilityScoreName) {
        System.out.println();
        if (abilityScoreName.equals("Physical")) {
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
        } else if (abilityScoreName.equals("Agility")) {
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
        } else if (abilityScoreName.equals("Blaster")) {
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
        } else if (abilityScoreName.equals("Piloting")) {
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
        } else if (abilityScoreName.equals("Repair")) {
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
        } else if (abilityScoreName.equals("Charisma")) {
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
        System.out.println();
    }

    public static void printAbilityScoresWithNames(String[] Names, Integer[] Values) {
        for (int i = 0; i < Values.length; i++) {
            System.out.println("[" + Names[i] + "]: " + Values[i]);
        }
    }

    private CharacterCreation() {}
}

