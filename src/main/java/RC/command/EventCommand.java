package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.task.Event;

public class EventCommand extends RCCommand {
    private String input;
    private static final String FROM_COMMAND = "/from";
    private static final String TO_COMMAND = "/to";
    private static final String MESSAGE_MISSING_COMMAND = "\tOOPS!!! Please include /from and /to for the start and end time.";
    private static final String MESSAGE_EMPTY = "\tOOPS!!! Please ensure description, start and end time are filled.";

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList) throws RCException {
        int fromIndex = input.indexOf(FROM_COMMAND);
        int toIndex = input.indexOf(TO_COMMAND);
        if (fromIndex == -1 || toIndex == -1) {
            throw new RCException(MESSAGE_MISSING_COMMAND);
        }

        String description = input.substring(0, fromIndex).trim();
        String from = input.substring(fromIndex + FROM_COMMAND.length(), toIndex).trim();
        String to = input.substring(toIndex + TO_COMMAND.length()).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new RCException(MESSAGE_EMPTY);
        }

        taskList.add(new Event(description, from, to));
    }
}
