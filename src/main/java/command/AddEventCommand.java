package command;

import task.TaskList;
import ui.Ui;

public class AddEventCommand extends Command {
    private final String description;
    private final String startTime;
    private final String endTime;

    /**
     * Constructor
     *
     * @param description the task description
     * @param startTime the starting time of the event
     * @param endTime the ending time of the event
     */
    public AddEventCommand(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Executes the command by calling addEvent method in Tasklist
     *
     * @param taskList the taskList of the current user
     * @param ui the ui for the current session
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addEvent(this.description, this.startTime, this.endTime);
        Ui.printAddMessage(taskList);
    }
}
