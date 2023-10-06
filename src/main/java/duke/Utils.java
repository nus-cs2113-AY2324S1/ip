package duke;

/**
 * A utility class that provides various miscellaneous utility functions.
 */
public class Utils {
    /**
     * Print out whatever is inputted and a divider
     *
     * @param input The message required to be printed
     */
    public static void echo(String input) {
        System.out.println(input + "\n");
        printDivider();

    }

    /**
     * Prints out a divider line
     */
    public static void  printDivider(){
        System.out.println("___________________________________\n");
    }

    /**
     * Handles a case when there are string index issues and prints an error message with dividers
     *
     * @param e String index exception issue
     */
    public static void handleStringIndexOutOfBoundsException(StringIndexOutOfBoundsException e){
        Utils.printDivider();
        System.out.println("OOPS!!! The description of a " + e.getMessage() + " cannot be empty. :-(");
        Utils.printDivider();
    }

    /**
     * Handles the case when there is no task name provided.
     */
    public static void handleNoTaskNameException(){
        Utils.printDivider();
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        Utils.printDivider();
    }

    /**
     * Handles an exception related to array index out of bounds.
     */
    public static void handleArrayIndexOutOfBoundsException(){
        Utils.printDivider();
        System.out.println("OOPS!!! Please Input in the correct format! :-(");
        Utils.printDivider();
    }
}
