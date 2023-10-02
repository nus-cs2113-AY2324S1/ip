package dawson.command;

import dawson.exception.DawsonException;
import dawson.task.EventTask;
import dawson.task.TaskList;
import dawson.ui.Messages;

public class EventCommand extends Command {

    private static final String FROM_DELIMITER = "/from";
    private static final String TO_DELIMITER = "/to";

    private String payload;

    public EventCommand(String payload) {
        this.payload = payload;
    }

    @Override
    public CommandResult execute(TaskList list) throws DawsonException {
        int from_position = payload.indexOf(FROM_DELIMITER);
        int to_position = payload.indexOf(TO_DELIMITER);

        if (from_position == -1 || to_position == -1) {
            String errorMsg = "Please include '/from' and '/to' for the start and end time!";
            throw new DawsonException(errorMsg);
        }

        String taskString = payload.substring(0, from_position).trim();
        String fromString = payload.substring(from_position + FROM_DELIMITER.length(), to_position).trim();
        String toString = payload.substring(to_position + TO_DELIMITER.length()).trim();

        EventTask newTask = new EventTask(taskString, fromString, toString);
        list.add(newTask);

        String[] msg = Messages.getAddSuccessMessage(newTask.toString(), list.getSize());
        return new CommandResult(msg);
    }

}