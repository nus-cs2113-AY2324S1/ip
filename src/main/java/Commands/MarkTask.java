package commands;

import tasks.Tasklist;
import zenbot.UI;
import exceptions.OutOfRangeException;

public class MarkTask extends Command {
    
    private String commandString;
    private Tasklist tasks;
    private int taskNumber;

    public MarkTask(String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

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
