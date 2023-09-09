public class inputValidator {

    public static boolean isValidTodo() {
        if (inputHandler.splitInput.length <= 1) {
            System.out.println("Please provide a description for your task!");
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidDeadline() {
        boolean hasBy = inputHandler.arguments.contains("/by");

        if (!hasBy) {
            System.out.println("Please specify a deadline using /by!");
        }
        return hasBy;
    }

    public static boolean isValidEvent() {
        boolean hasFrom = inputHandler.arguments.contains("/from");
        boolean hasTo = inputHandler.arguments.contains("/to");

        if (!hasFrom) {
            System.out.println("Please specify a starting time using /from!");
        }

        if (!hasTo) {
            System.out.println("Please specify a ending time using /to!");
        }

        return hasFrom && hasTo;
    }

    public static boolean isValidMarking() {
        if (inputHandler.splitInput.length != 2) {
            System.out.println("Please provide one index!");
            return false;
        } else {
            return true;
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
