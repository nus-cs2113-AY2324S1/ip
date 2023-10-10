/**
 * Parser object that handles splitting of command line arguments given by the user.
 */
public class Parser {

    public Parser() {
    }

    /**
     * Split user input into command and argument of the command.
     *
     * @param fullCommand User input.
     * @return Return a Command object for execution of the command.
     */
    public Command parse(String fullCommand) {
        String[] substr = fullCommand.split("\\s+", 2);
        if (substr.length == 1) {
            return new Command(substr[0], "");
        }
        return new Command(substr[0], substr[1]);
    }
}
