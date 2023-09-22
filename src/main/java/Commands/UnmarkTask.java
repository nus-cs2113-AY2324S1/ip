package commands;

import tasks.Tasklist;
import zenbot.UI;
import exceptions.OutOfRangeException;

/**
 * Represents a command to unmark a task from the tasklist.
 */
public class UnmarkTask extends Command {
    
    private String commandString;
    private Tasklist tasks;
    private int taskNumber;

    public UnmarkTask(String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    /**
     * Unmark a task from the tasklist.
     * Print a message to the user to indicate that the task has been unmarked.
     * @throws OutOfRangeException if the task number is out of range.
     */
    @Override
    public void execute() throws OutOfRangeException {
        try {
            taskNumber = Integer.parseInt(commandString.substring(7));
        } catch (NumberFormatException e) {
            System.out.println("\tPlease enter a valid task number!");
            return;
        }
        if (taskNumber > tasks.getTaskListSize() || taskNumber < 1) {
            throw new OutOfRangeException();
        }
        tasks.markTaskAsUndone(taskNumber);
        UI.printSeperatorLine();
        System.out.println("\tAh, chuckles! I've playfully returned this task to its untamed state:");
        tasks.getTask(taskNumber).printTask();
        UI.printSeperatorLine();
    }

}
