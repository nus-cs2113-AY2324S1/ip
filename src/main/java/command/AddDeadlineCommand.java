package command;

import task.TaskList;
import ui.Ui;

/**
 * Represent the command "deadline"
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructor
     *
     * @param description the task description
     * @param by the due date/time
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command by calling addDeadline method in Tasklist
     *
     * @param taskList the taskList of the current user
     * @param ui the ui for the current session
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addDeadline(this.description, this.by);
        Ui.printAddMessage(taskList);
    }
}
