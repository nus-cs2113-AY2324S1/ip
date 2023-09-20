package Command;

import Soccat.Todo;
import Storage.TaskList;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    private final Todo todoTask;

    public TodoCommand(String taskName) {
        this.todoTask = new Todo(taskName);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(todoTask);
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" + todoTask);
        System.out.println("Now you have " + tasks.getTaskListLength() + " tasks in the list.");
    }
}
