package commands;

import tasks.Tasklist;
import zenbot.ZenBot;
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
        taskNumber = Integer.parseInt(commandString.substring(5));
        if (taskNumber > tasks.getTaskListSize() || taskNumber < 1) {
            throw new OutOfRangeException();
        }
        tasks.markTaskAsDone(taskNumber);
        ZenBot.printSeperatorLine();
        System.out.println("\tHuzzah! I've adorned this task with the badge of completion:");
        tasks.getTask(taskNumber).printTask();
        ZenBot.printSeperatorLine();
    }

}
