package linguobot.command;

import linguobot.exceptions.LinguoBotException;

/**
 * Base class for commands in the LinguoBot application.
 * It provides a simple mechanism for executing commands.
 */
public class Command {
    private String input;

    public Command(String input) {
        this.input = input;
    }

    public Command() {
    }

    public String getInput() {
        return this.input;
    }

    public void execute() throws LinguoBotException {}
}
