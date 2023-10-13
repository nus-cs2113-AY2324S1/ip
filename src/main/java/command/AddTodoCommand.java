package command;
import task.TaskList;
import ui.Ui;

public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructor
     *
     * @param description the task description
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by calling addTodo method in Tasklist
     *
     * @param taskList the taskList of the current user
     * @param ui the ui for the current session
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTodo(description);
        Ui.printAddMessage(taskList);
    }
}
