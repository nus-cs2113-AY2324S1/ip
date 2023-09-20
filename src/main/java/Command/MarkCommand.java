package Command;

import Storage.TaskList;
import Soccat.Task;

public class MarkCommand extends Command{
    public static final String COMMAND_WORD_DONE = "mark";
    public static final boolean MARK_DONE = true;
    public static final String COMMAND_WORD_UNDONE = "unmark";
    public static final boolean MARK_UNDONE = false;
    private final boolean isDone;
    private final int taskIndex;


    public MarkCommand(boolean isDone, int taskIndex) {
        this.isDone = isDone;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks) {
        int taskListLength = tasks.getTaskListLength();
        if (taskListLength == 0) {
            System.out.println("No tasks to mark for now, you may take a break!");
            return;
        }
        try {
            Task task = tasks.getTask(taskIndex);
            task.setDone(isDone);
            System.out.println("\t" + task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops, task number is out of range!");
            System.out.println("Please use index from 1 to " + tasks.getTaskListLength());
        }
    }
}
