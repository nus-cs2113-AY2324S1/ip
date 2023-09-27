package eggybyte.ip.command;

import eggybyte.ip.data.task.Task;

public class TaskCommand extends AddCommand {
    public static final String COMMAND_WORD = "task";
    protected static final int validArgumentAmount = 0;

    public TaskCommand(String description, String[] arguments) throws Exception {
        super(COMMAND_WORD, validArgumentAmount);
        // checkArguments(arguments);
        task = new Task(description);
    }

    @Override
    public String customFunction() {
        runningState.tasks.add(task);
        return task.getDescription();
    }
}
