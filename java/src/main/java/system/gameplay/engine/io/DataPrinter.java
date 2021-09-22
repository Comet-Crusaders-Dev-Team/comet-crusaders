package system.gameplay.engine.io;


import java.util.List;
import java.util.Map;

/**
 * Utility class that provides easily-readable methods to handle common printing functionality.
 */
public final class DataPrinter {

    /**
     * Prints the elements of a list in the style of an ordered list, with each element printed on it's own line.
     * Ex:
     * [1]: Winter
     * [2]: Spring
     *
     * @param list Wildcard list, make sure the output of the type passed in's toString method is equal to what you wish
     * to print.
     */
    public static void printAsOrderedList(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("[" + (i + 1) + "]:" + " " + list.get(i));
        }
    }

    /**
     * Use to print a map of Integers with String keys
     * @param map
     */
    public static void printStringIntMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("[" + entry.getKey() + "]: " + entry.getValue());
        }
    }

    private DataPrinter() {}
}
