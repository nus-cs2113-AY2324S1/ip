package eggybyte.ip.command;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.task.Task;

public class AddCommand extends Command {

    protected Task task;

    public AddCommand(String type, int validArgumentAmount) {
        super(type, validArgumentAmount);
    }

    @Override
    public String customFunction() {
        runningState.tasks.add(task);
        return task.toString();
    }

    @Override
    public CommandResult getCommandResult(String content) {
        // return new CommandResult("Got it. I've added this task:\n"
        // + content
        // + "\nNow you have " + runningState.tasks.size() + " tasks in the list.");
        return new CommandResult("added: " + content);
    }
}
