package commands;

import tasks.Tasklist;
import zenbot.UI;
import exceptions.OutOfRangeException;

public class UnmarkTask extends Command {
    
    private String commandString;
    private Tasklist tasks;
    private int taskNumber;

    public UnmarkTask(String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    @Override
    public void execute() throws OutOfRangeException {
        taskNumber = Integer.parseInt(commandString.substring(7));
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
