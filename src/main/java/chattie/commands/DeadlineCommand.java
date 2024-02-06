package chattie.commands;

import chattie.Parser;
import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.tasks.Deadline;
import chattie.Storage;

/**
 * Deals with deadline related commands
 */
public class DeadlineCommand extends Command {

    private static final int DEADLINE_LENGTH = 9;
    private static final int BY_LENGTH = 3;
    private static String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    /**
     * Adds a new deadline to list of tasks
     *
     * @param tasks List of tasks
     * @param ui User interface
     * @throws ChattieException If command is empty or not formatted properly
     */
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
        by = Parser.parseDate(by.trim());

        Deadline deadline = new Deadline(task, by);
        tasks.add(deadline);
        ui.printAddMessage(tasks, deadline);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
