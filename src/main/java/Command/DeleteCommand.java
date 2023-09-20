package Command;

import Soccat.Task;
import Storage.TaskList;

public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "delete";
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks) {
        int taskListLength = tasks.getTaskListLength();
        if (taskListLength == 0) {
            System.out.println("No tasks to delete for now, you may take a break!");
            return;
        }
        try {
            Task task = tasks.removeTask(taskIndex);
            System.out.println("Noted, I have removed this task:");
            System.out.println("\t" + task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops, task number is out of range!");
            System.out.println("Please use index from 1 to " + tasks.getTaskListLength());
        }
    }
}
