package magpie.input;
import magpie.exceptions.MagpieException;
public class InputValidator {

    public static void validateTodo() throws MagpieException {

        if (Parser.splitInputs.length <= 1) {
            throw new MagpieException("Please provide a description for your task!");
        }
    }

    public static void validateDeadline() throws MagpieException {
        boolean hasBy = Parser.arguments.contains("/by");

        if (!hasBy) {
            throw new MagpieException("Please specify a deadline using /by!");
        }
    }

    public static void validateEvent() throws MagpieException {
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

    public static void validateIndexIsPresent() throws MagpieException {
        if (Parser.splitInputs.length != 2) {
            throw new MagpieException("Please provide one index!");
        }
    }

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
