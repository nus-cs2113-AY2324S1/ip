package dawson.command;

import dawson.exception.DawsonException;
import dawson.task.DeadlineTask;
import dawson.task.TaskList;
import dawson.ui.Messages;

public class DeadlineCommand extends Command {

    private static final String BY_DELIMITER = "/by";

    private String payload;
    private TaskList list;

    public DeadlineCommand(String payload, TaskList list) {
        this.payload = payload;
        this.list = list;
    }

    @Override
    public CommandResult execute() throws DawsonException {
        int by_position = payload.indexOf(BY_DELIMITER);

        if (by_position == -1) {
            String errorMsg = "Please include '/by' followed by the deadline!";
            throw new DawsonException(errorMsg);
        }

        String taskString = payload.substring(0, by_position).trim();
        String byString = payload.substring(by_position + BY_DELIMITER.length()).trim();

        DeadlineTask newTask = new DeadlineTask(taskString, byString);
        list.add(newTask);

        String[] msg = Messages.getAddSuccessMessage(newTask.toString(), list.getSize());
        return new CommandResult(msg);
    }

}