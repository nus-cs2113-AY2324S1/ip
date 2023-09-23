package magpie.input;
import magpie.exceptions.MagpieException;

/**
 * <b>InputValidator</b> class is responsible for validating parsed user input for each command e.g adding Deadline.<br>
 * Ensures that the necessary arguments are present <i>(e.g /by)</i> and <code>int</code>
 * can be parsed from <code>String</code>.<br>
 * Throws <code>MagpieException</code> if validation fails.
 */
public class InputValidator {


    /**
     * Validates user input for adding a <code>Todo</code> Task by checking number of arguments.
     *
     * @throws MagpieException if splitInputs.length <= 1.
     */
    public static void validateTodo() throws MagpieException {

        if (Parser.splitInputs.length <= 1) {
            throw new MagpieException("Please provide a description for your task!");
        }
    }

    /**
     * Validates user input for adding a <code>Deadline</code> Task by checking number of arguments and if "/by" was
     * given.
     *
     * @throws MagpieException if splitInputs.length <= 3 or if "/by" is missing.
     */
    public static void validateDeadline() throws MagpieException {

        if (Parser.splitInputs.length <= 3) {
            throw new MagpieException("Looks like you're missing an argument. Specify a description and /by deadline!");
        }

        boolean hasBy = Parser.arguments.contains("/by");

        if (!hasBy) {
            throw new MagpieException("Please specify a deadline using /by!");
        }
    }

    /**
     * Validates user input for adding a <code>Event</code> Task by checking number of arguments,
     * and if "/from" and "/to" was given.
     *
     * @throws MagpieException if splitInputs.length <= 5 or if "/from" or "/to" is missing.
     */

    public static void validateEvent() throws MagpieException {

        if (Parser.splitInputs.length <= 5) {
            throw new MagpieException("Looks like you're missing an argument. Specify a description, from and to!");
        }

        boolean hasFrom = Parser.arguments.contains("/from");
        boolean hasTo = Parser.arguments.contains("/to");

        if(!hasFrom && !hasTo){
            throw new MagpieException("Please specify starting and ending times using /from and /to!");
        }
        else if (!hasFrom) {
            throw new MagpieException("Please specify a starting time using /from!");
        }
        else if (!hasTo) {
            throw new MagpieException("Please specify a ending time using /to!");

        }

    }

    /**
     * Validate user input contains exactly one target by checking if number of arguments is 2.
     *
     * @throws MagpieException if splitInputs.length != 2
     */

    public static void validateTargetIsPresent() throws MagpieException {
        if (Parser.splitInputs.length != 2) {
            throw new MagpieException("Please provide one target! e.g delete 2 or find book");
        }
    }


    /**
     * Validate if given String can be parsed into an Integer.
     *
     * @param input <code>String</code> input to be validated.
     * @return <code>true</code> if input can be parsed, otherwise <code>false</code>.
     */
    public static boolean isValidInt(String input) {
        int number = -1;

        try {
            number = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println(input + " is not a valid integer!");
            return false;
        }

    }
}
