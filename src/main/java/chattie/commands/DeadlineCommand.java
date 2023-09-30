package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.tasks.Deadline;
import chattie.Storage;

public class DeadlineCommand extends Command {

    private static final int DEADLINE_LENGTH = 9;
    private static final int BY_LENGTH = 3;
    private static String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        if (command.trim().length() < DEADLINE_LENGTH) { //check if deadline is empty
            throw new ChattieException(ErrorType.EMPTY_DEADLINE);
        }

        int slashIndex = command.indexOf("/by");
        if (slashIndex < 0) {
            throw new ChattieException(ErrorType.INVALID_DEADLINE); //check if '/by' is present
        }

        String task = command.substring(DEADLINE_LENGTH, slashIndex);
        String by = command.substring(slashIndex + BY_LENGTH);
        if (task.isEmpty() || by.isEmpty()) {
            throw new ChattieException(ErrorType.INVALID_DEADLINE); //check if [task] or [by] empty
        }

        Deadline deadline = new Deadline(task, by);
        tasks.add(deadline);
        ui.printAddMessage(deadline, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
