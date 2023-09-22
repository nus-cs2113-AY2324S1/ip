package duke;

import java.util.Arrays;

// utility class
public class Parser {
    final static int FIRST_INDEX=0;
    final static int SECOND_INDEX=1;

    private Parser(){}

    public static Command parse(String fullCommand){
        String[] arguments = fullCommand.split("\\s+"); // split by space
        String actionCommand = arguments[FIRST_INDEX];
        actionCommand = actionCommand.toLowerCase(); // make sure all same case to account for typing error
        String[] newArguments = Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length); // ignore command
        return new Command(actionCommand, newArguments);
    }
}
