package duke;

public class Parser {
    public static String[] parseCommand(String input) {
        // Split the input into command and arguments
        return input.split(" ");
    }
}
