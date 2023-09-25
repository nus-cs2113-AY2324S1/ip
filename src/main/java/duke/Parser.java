package duke;

import java.util.Arrays;

/**
 * The {@code Parser} class is a utility class responsible for interpreting user inputs to create
 * {@code Command} objects that can be executed by the application. The Parser class is designed
 * to extract meaningful information, such as action commands and their arguments, from user-inputted strings.
 * <p>
 * The class is designed with a private constructor to prevent instantiation, reflecting its role as a utility class
 * that does not maintain any state. The primary functionality is provided by the {@code parse} static method,
 * which interprets the user input and returns an appropriate {@code Command} object.
 * </p>
 * <p>
 * The {@code Parser} class provides constants {@code FIRST_INDEX} and {@code SECOND_INDEX} representing the indices
 * of the action command and arguments in the command string, respectively.
 * </p>
 * <ul>
 *     <li>{@link #parse(String)} - Parses the given string command to create a new {@code Command} object.</li>
 * </ul>
 *
 * @see Command
 *
 * @author  Ashok Balaji
 * @version 1.0
 * @since   2023-09-25
 */
public class Parser {
    final static int FIRST_INDEX=0;
    final static int SECOND_INDEX=1;

    private Parser(){}

    /**
     * Parses the given string command to create a new Command object.
     * <p>
     * The method takes a full command string, splits it into action command and arguments, and initializes a new
     * Command object with them. The action command is converted to lowercase to account for typing errors.
     * The action command represents the first word in the string, and the remaining words are considered as arguments.
     * </p>
     *
     * @param fullCommand The full string command to be parsed.
     * @return A Command object initialized with the parsed action command and arguments.
     */
    public static Command parse(String fullCommand){
        String[] arguments = fullCommand.split("\\s+"); // split by space
        String actionCommand = arguments[FIRST_INDEX];
        actionCommand = actionCommand.toLowerCase(); // make sure all same case to account for typing error
        String[] newArguments = Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length); // ignore command
        return new Command(actionCommand, newArguments);
    }
}
