package system.gameplay.engine.io;

import java.util.Scanner;

// TODO: This class may not be needed after migration to Lanterna, if so, delete it.
public final class PlayerInputPrompter {

    private static PlayerInputPrompter INSTANCE;
    private final Scanner scanner;

    private PlayerInputPrompter() {
        scanner = SystemInputScannerSingleton.getInstance();
    }

    public static PlayerInputPrompter getInstance() {
        if (INSTANCE == null) {
            synchronized(PlayerInputPrompter.class) {
                if (INSTANCE == null)
                    INSTANCE = new PlayerInputPrompter();
            }
        }
        return INSTANCE;
    }

    public boolean promptYesOrNo (String prompt) {
        while (true) {
            System.out.println(prompt + " (yes/no)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Please answer with (yes/no).");
            }
        }
    }
}