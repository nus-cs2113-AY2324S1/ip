package command;
import task.TaskList;
import ui.Ui;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTodo(description);
        Ui.printAddMessage(taskList);
    }
}
