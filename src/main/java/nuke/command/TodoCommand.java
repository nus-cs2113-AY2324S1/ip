package nuke.command;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.Nuke;

public class TodoCommand extends Command {
    public static final String TYPE = "todo";
    private static final String USAGE = TYPE + " ((name))";

    private String name;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (args.isEmpty()) {
            throwArgumentException(ERROR_MSG_NO_ARGS);
        }
        checkForbiddenCharacters(new String[]{args});
        name = args;
    }

    @Override
    protected String getType() {
        return TYPE;
    }

    @Override
    protected String getUsage() {
        return USAGE;
    }

    @Override
    public void run(Nuke nuke) {
        nuke.addTodo(name);
    }

    private static final String ERROR_MSG_NO_ARGS =
            "Command 'todo' should have one argument, name of the task.";
}
