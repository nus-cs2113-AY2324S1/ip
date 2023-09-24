package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;
import RC.task.Deadline;

public class DeadlineCommand extends RCCommand {
    private String input;
    private static final String BY_COMMAND = "/by";
    private static final String MESSAGE_MISSING_COMMAND = "\tOOPS!!! Please include /by followed by the " +
            "deadline. (eg. /by Monday)";
    private static final String MESSAGE_EMPTY = "\tOOPS!!! Please ensure description and deadline are filled.";

    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws RCException {
        int splitIndex = input.indexOf(BY_COMMAND);
        if (splitIndex == -1) {
            throw new RCException(MESSAGE_MISSING_COMMAND);
        }

        String description = input.substring(0, splitIndex).trim();
        String by = input.substring(splitIndex + BY_COMMAND.length()).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new RCException(MESSAGE_EMPTY);
        }

        taskList.add(new Deadline(description, by), ui);
    }
}
