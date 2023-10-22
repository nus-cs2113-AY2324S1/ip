package linguobot.command;

import linguobot.task.TaskList;
/**
 * The <code>ViewTaskList</code> class represents a command to view and print the list of tasks in the taskList.
 */
public class ViewTaskList extends Command{
    private final TaskList taskList;

    /**
     * Constructs a ViewTaskList command with the given taskList.
     * @param taskList The taskList to view and print tasks from.
     */
    public ViewTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the ViewTaskList command by printing the list of tasks from the associated taskList.
     */
    @Override
    public void execute() {
        taskList.printTaskList();
    }
}
