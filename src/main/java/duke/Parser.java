package duke;

public class Parser {

    public Parser(){

    }

    public static Command extractCommand(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = null;
        if (parts.length > 1) {
            argument = parts[1];
        }
        return new Command(command, argument);
    }
}

