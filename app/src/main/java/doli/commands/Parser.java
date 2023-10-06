package doli.commands;

import doli.exceptions.DoliExceptions;

/**
 * <h3>Parser class</h3>
 * The parser class takes a user input and extracts the necessary
 * information to convert it into an object of type command. It splits
 * the input into two parts, command and details while making sure that
 * the inputs are valid (meaning that the right amount of details are
 * provided in the specific cases).
 *
 * @author pappalardodaniel
 * @version 1.0
 * @since 2023-11-03
 */
public class Parser {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String OVERVIEW_BY_SPECIFIC_DATE_COMMAND = "overview";
    private static final String FIND_COMMAND = "find";
    private static final int NR_EVENT_ARGS = 3;
    private static final int DATE_FORMAT_LENGTH = 10; // "yyyy-MM-dd"
    private static final int NR_DEADLINE_ARGS = 2;

    /**
     * Constructs an empty object of type Parser.
     */
    public Parser() {
    }

    /**
     * Splits the input String into an array of length 2 containing the
     * command keyword eventually alongside with some specific details.
     *
     * @param input of type String which represents the user input.
     * @return an array of two strings, the command keyword and some details.
     */
    private static String[] splitCommandAndDetails(String input) {
        String[] commandAndDetails = input.toLowerCase().split(" ", 2);
        if (commandAndDetails.length == 2) {
            return commandAndDetails;
        } else {
            return commandAndDetails = new String[]{commandAndDetails[0], ""};
        }
    }

    /**
     * Creates a new object of type command with the user input command and details
     * after checking validity of inputs and splitting the input into its sub-parts.
     *
     * @param input of type String which represents the user input.
     * @return a new object of type Command.
     * @throws DoliExceptions in case the input is not valid.
     */
    public static Command parseInputIntoCommand(String input) throws DoliExceptions {
        final String command = splitCommandAndDetails(input)[0];
        final String details = splitCommandAndDetails(input)[1];
        final String[] args = details.split("/");
        checkForValidInput(command, args);
        Command newCommand = new Command(command, args);
        return newCommand;
    }

    /**
     * Checks whether the input String can be converted i.e. parsed into a number.
     *
     * @param date of type String.
     * @return true if the String can be parsed into an Integer.
     */
    public static boolean isANumber(String date) {
        try {
            Integer.parseInt(date);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    /**
     * Checks whether the input String contains a valid month.
     *
     * @param date of type String.
     * @return true if the String contains a valid month.
     */
    public static boolean containsAValidMonth(String date) {
        int month = Integer.parseInt(date.trim().split("-")[1]);
        if (month < 13) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Checks whether the input String contains a valid day.
     *
     * @param date of type String.
     * @return true if the String contains a valid day.
     */
    public static boolean containsAValidDay(String date) {
        int day = Integer.parseInt(date.trim().split("-")[2]);
        if (day < 32) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether the input date is actually a valid date, meaning of format "yyyy-MM-dd".
     *
     * @param date of type String must be of format "yyyy-MM-dd" for the method to return true.
     * @return true if the date's format is valid and false otherwise.
     */
    public static boolean checkForValidDate(String date) {
        boolean dateIsValid;
        if (date.trim().length() == DATE_FORMAT_LENGTH
                && date.indexOf('-') == 4 && date.lastIndexOf('-') == 7
                && isANumber(date.trim().split("-")[0])
                && isANumber(date.trim().split("-")[1])
                && isANumber(date.trim().split("-")[2])
                && containsAValidDay(date) && containsAValidMonth(date)) {
            dateIsValid = true;
        } else {
            dateIsValid = false;
        }
        return dateIsValid;
    }

    /**
     * Checks for the validity of the user input in terms of the amount of
     * details specified in each scenario. It checks the following commands: todo,
     * deadline, event, mark, unmark, delete, overview and find.
     *
     * @param command of type String specifying the command keyword contained in the user input.
     * @param details an array of type String containing eventual details of the input exceeding the command keyword.
     * @throws DoliExceptions in case the proper amount of details required by the command is not met.
     */
    public static void checkForValidInput(String command, String[] details) throws DoliExceptions {
        switch (command) {
        case TODO_COMMAND:
            if (details[0].trim().equals("")) {
                throw new DoliExceptions("Input of a todo cannot be blank!");
            }
            break;
        case DEADLINE_COMMAND:
            if (details.length < NR_DEADLINE_ARGS || !checkForValidDate(details[1])) {
                throw new DoliExceptions("There is something amiss in your deadline date, please try again");
            }
            break;
        case EVENT_COMMAND:
            if (details.length < NR_EVENT_ARGS
                    || !checkForValidDate(details[1]) || !checkForValidDate(details[2])) {
                throw new DoliExceptions("Start-time, end-time or description missing for event");
            }
            break;
        case MARK_COMMAND:
            if (details.length == 0 || !isANumber(details[0])) {
                throw new DoliExceptions("Please specify the index of the task to mark");
            }
            break;
        case UNMARK_COMMAND:
            if (details.length == 0 || !isANumber(details[0])) {
                throw new DoliExceptions("Please specify the index of the task to unmark");
            }
            break;
        case DELETE_COMMAND:
            if (details.length == 0 || !isANumber(details[0])) {
                throw new DoliExceptions("Please specify the index of the task to delete");
            }
            break;
        case OVERVIEW_BY_SPECIFIC_DATE_COMMAND:
            if (details.length == 0 || !checkForValidDate(details[0])) {
                throw new DoliExceptions("Please specify a proper date to limit the overview of your agenda");
            }
            break;
        case FIND_COMMAND:
            if (details.length == 0) {
                throw new DoliExceptions("Please specify a proper keyword to filter and search your agenda");
            }
            break;
        default:
            break;
        }
    }
}
