package system.gameplay.roleplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DiceRoller {

    private final Random random;

    public DiceRoller() {
        random = new Random();
    }

    public List<Integer> rollAbilityScores() {
        //TODO: (A) This line is here so the compiler won't complain, remove it when you add the functionality for this method
        ArrayList<Integer> rollAbilityScoresList = new ArrayList<>();
        int topThreeSixSidedDie = topThreeSixSidedDie();
        for (int i = 1; i <= 6; i++) {
                rollAbilityScoresList.add(topThreeSixSidedDie);
                topThreeSixSidedDie = topThreeSixSidedDie();
        }

        sortIntsHighToLow(rollAbilityScoresList);
        return rollAbilityScoresList;
    }

    public int topThreeSixSidedDie() {
        ArrayList<Integer> diceRollValues = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            diceRollValues.add(roll6());
        }

        sortIntsHighToLow(diceRollValues);

        diceRollValues.remove(3);
        int diceRollValuesSum = 0;

        for (int i: diceRollValues) {
            diceRollValuesSum += i;
        }

        return diceRollValuesSum;
    }

    public int roll6() {
        return random.nextInt(6) + 1;
    }

    public int roll20() {
        // Returns a random int between 1 and 20
        return random.nextInt(20) + 1;
    }

    private List<Integer> sortIntsHighToLow(List<Integer> list) {
        Collections.reverse(list);
        return list;
    }

    // Let me know if this should be placed in a different location
    public void printAbilityScores(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + " " + list.get(i));
        }
    }

    public List<Integer> askAbilityScores(List<Integer> list) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        Integer[] abilityScoreValues = new Integer[6];
        String[] abilityScoreNames = new String[6];
        abilityScoreNames[0] = ("Physical");
        abilityScoreNames[1] = ("Agility");
        abilityScoreNames[2] = ("Blaster");
        abilityScoreNames[3] = ("Piloting");
        abilityScoreNames[4] = ("Repair");
        abilityScoreNames[5] = ("Charisma");
        String abilityScoreName = abilityScoreNames[i];
        while (true) {
            while (i < 6) {
                System.out.println("");
                System.out.println("Which score would you like to assign to your [" + abilityScoreName + "] ability?");
                System.out.println("Enter the number corresponding to the value you wish to use...");
                printAbilityScores(list);
                int abilityScoreSelection = Integer.parseInt(scanner.nextLine());
                abilityScoreFlavorText(list.get(abilityScoreSelection - 1), abilityScoreName);
                abilityScoreValues[i] = list.get(abilityScoreSelection-1);
                list.remove(abilityScoreSelection - 1);
                i++;
                abilityScoreName = abilityScoreNames[i];
            }

            System.out.println("Are these the ability scores you wish to use? (yes/no)");
        }
    }
    
    public void abilityScoreFlavorText (int abilityScoreValue, String abilityScoreName) {
        System.out.println();
        if (abilityScoreName.equals("Physical")) {
            if (abilityScoreValue == 18) {
                System.out.println("Holy smokes! You must wrestle with bears for fun!");
            } else if (abilityScoreValue >= 14) {
                System.out.println("With numbers like that, I might be willing to train with you sometime.");
            } else if (abilityScoreValue >= 10) {
                System.out.println("You look like you may have seen the inside of a metagalactic economy training gym or two.");
            } else if (abilityScoreValue == 9) {
                System.out.println("Perfectly average physical capabilities.");
            } else if (abilityScoreValue >= 7) {
                System.out.println("Hit some mass-affected apparatuses, nerd.");
            } else if (abilityScoreValue >= 3) {
                System.out.println("Have you ever been outside? Good luck...");
            }
        } else if (abilityScoreName.equals("Agility")) {
            if (abilityScoreValue == 18) {
                System.out.println("Whoa there! You must go bobbing for cobras instead of apples!");
            } else if (abilityScoreValue >= 14) {
                System.out.println("You've got some slick moves there!");
            } else if (abilityScoreValue >= 10) {
                System.out.println("You look like a bit of a dancer.");
            } else if (abilityScoreValue == 9) {
                System.out.println("Perfectly averagely agile. Hmph.");
            } else if (abilityScoreValue >= 7) {
                System.out.println("You look sluggish...Are you okay?");
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
                System.out.println("As average a blaster could get.");
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
                System.out.println("Not bad...Now listen, I've got this sound coming from my kinetic barriers..");
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
                System.out.println("Had more than a few ladies hoping you'd take 'em for a night-time cruise, I see.");
            } else if (abilityScoreValue == 9) {
                System.out.println("Average, but it's better than some can say.");
            } else if (abilityScoreValue >= 7) {
                System.out.println("You're not convincing anyone.");
            } else if (abilityScoreValue >= 3) {
                System.out.println("Was that an attempt?...");
            }
        }
    }

    public void printAbilityScoresWithNames (String[] Names, Integer[] Values) {
        for (int i = 0; i < Values.length; i++) {
            System.out.println("[" + Names[i] + " " + "]: " + Values[i]);
        }
    }
}
