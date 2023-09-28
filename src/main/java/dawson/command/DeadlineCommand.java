package dawson.command;

import dawson.TaskList;
import dawson.exception.DawsonException;
import dawson.task.Deadline;

public class DeadlineCommand extends Command {

    private static final String BY_DELIMITER = "/by";

    private String payload;
    private TaskList list;

    public DeadlineCommand(String payload, TaskList list) {
        this.payload = payload;
        this.list = list;
    }

    @Override
    public void execute() throws DawsonException {
        int by_position = payload.indexOf(BY_DELIMITER);

        if (by_position == -1) {
            String errorMsg = "Please include '/by' followed by the deadline!";
            throw new DawsonException(errorMsg);
        }

        String taskString = payload.substring(0, by_position).trim();
        String byString = payload.substring(by_position + BY_DELIMITER.length()).trim();

        Deadline newTask = new Deadline(taskString, byString);
        list.add(newTask);
    }

}