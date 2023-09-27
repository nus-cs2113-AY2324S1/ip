package eggybyte.ip.command;

import eggybyte.ip.data.task.Todo;

public class TodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";
    protected static final int validArgumentAmount = 1;

    public TodoCommand(String[] arguments) throws Exception {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        task = new Todo(arguments[0]);
    }

    @Override
    public String customFunction() {
        runningState.tasks.add(task);
        return task.getDescription();
    }
}
