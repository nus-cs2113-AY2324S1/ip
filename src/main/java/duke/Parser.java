package duke;

import java.util.Arrays;

public class Parser {
    protected static int FIRST_INDEX=0;
    protected static int SECOND_INDEX=1;

    public Parser(){}

    public static Command parse(String fullCommand){
        String[] arguments = fullCommand.split("\\s+"); // split by space
        String actionCommand = arguments[FIRST_INDEX];
        actionCommand = actionCommand.toLowerCase(); // make sure all same case to account for typing error
        String[] newArguments = Arrays.copyOfRange(arguments, SECOND_INDEX, arguments.length); // ignore command
        return new Command(actionCommand, newArguments);
    }
}
