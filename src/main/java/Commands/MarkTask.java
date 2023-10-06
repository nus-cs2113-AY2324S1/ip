package commands;

import tasks.Tasklist;
import zenbot.UI;
import exceptions.OutOfRangeException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkTask extends Command {
    
    private String commandString;
    private Tasklist tasks;
    private int taskNumber;

    public MarkTask(String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    /**
     * Mark a task as done.
     * Print a message to the user to indicate that the task has been marked as done.
     * @throws OutOfRangeException if the task number is out of range.
     */
    @Override
    public void execute() throws OutOfRangeException {
        try {
            taskNumber = Integer.parseInt(commandString.substring(5));
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter a valid task number!");
            return;
        }
        if (taskNumber > tasks.getTaskListSize() || taskNumber < 1) {
            throw new OutOfRangeException();
        }
        tasks.markTaskAsDone(taskNumber);
        UI.printSeperatorLine();
        System.out.println("\tHuzzah! I've adorned this task with the badge of completion:");
        tasks.getTask(taskNumber).printTask();
        UI.printSeperatorLine();
    }

}
