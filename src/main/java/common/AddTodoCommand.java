package common;
import listWhisper.task.TaskList;
import ui.Ui;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTodo(description);
        Messages.printAddMessage(taskList);
    }
}
