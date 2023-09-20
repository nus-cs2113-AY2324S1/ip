package Command;

import Soccat.Deadline;
import Storage.TaskList;
import Storage.Storage;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    private final Deadline deadlineTask;

    public DeadlineCommand(String taskName, String deadline) {
        this.deadlineTask = new Deadline(taskName, deadline);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(deadlineTask);
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" + deadlineTask);
        System.out.println("Now you have " + tasks.getTaskListLength() + " tasks in the list.");
    }
}