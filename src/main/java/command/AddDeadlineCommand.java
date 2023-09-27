package command;

import task.TaskList;
import ui.Ui;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addDeadline(this.description, this.by);
        Ui.printAddMessage(taskList);
    }
}
