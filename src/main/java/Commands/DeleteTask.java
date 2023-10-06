package commands;

import exceptions.OutOfRangeException;
import tasks.Tasklist;
import zenbot.UI;

/**
 * Represents a command to delete a task from the tasklist.
 */
public class DeleteTask extends Command {
    
    private String commandString;
    private Tasklist tasks;
    private int taskNumber;

    public DeleteTask(String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    /**
     * Delete a task from the tasklist.
     * Print a message to the user to indicate that the task has been deleted.
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
        UI.printSeperatorLine();
        System.out.println("\tI've playfully plucked this task away:");
        tasks.getTask(taskNumber).printTask();
        UI.printSeperatorLine();
        tasks.deleteTask(taskNumber);
    }
}
