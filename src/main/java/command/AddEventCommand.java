package command;

import task.TaskList;
import ui.Ui;

public class AddEventCommand extends Command {
    private final String description;
    private final String startTime;
    private final String endTime;

    public AddEventCommand(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addEvent(this.description, this.startTime, this.endTime);
        Ui.printAddMessage(taskList);
    }
}
