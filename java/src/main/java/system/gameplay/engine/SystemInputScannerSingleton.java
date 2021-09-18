package system.gameplay.engine;

import java.util.Scanner;

/**
 * Singleton implementation of java.util.Scanner that reads System.in.
 */
public class SystemInputScannerSingleton {

     private static Scanner scanner = null;

     private SystemInputScannerSingleton() {}

    public static Scanner getInstance() {
        if (scanner == null) {
            synchronized(SystemInputScannerSingleton.class) {
                if (scanner == null)
                    scanner = new Scanner(System.in);
            }
        }
        return scanner;
    }
}

