package nuke.command;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.Nuke;

public class CommandTodo extends Command {
    public String name;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgumentException(ERROR_MSG_NO_ARGS);
        }
        name = args;
    }

    @Override
    protected String getUsage() {
        return "todo ((name))";
    }

    @Override
    public void run() {
        Nuke.addTodo(name);
    }

    private static final String ERROR_MSG_NO_ARGS =
            "Command 'todo' should have one argument, name of the task.";
}
