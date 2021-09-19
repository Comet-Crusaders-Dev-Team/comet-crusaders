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
}