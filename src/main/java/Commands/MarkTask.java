package Commands;

import Tasks.Tasklist;
import ZenBot.ZenBot;

public class MarkTask extends Command {
    
    private String commandString;
    private Tasklist tasks;
    private int taskNumber;

    public MarkTask(String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        taskNumber = Integer.parseInt(commandString.substring(5));
        tasks.markTaskAsDone(taskNumber);
        ZenBot.printSeperatorLine();
        System.out.println("\tHuzzah! I've adorned this task with the badge of completion:");
        tasks.getTask(taskNumber).printTask();
        ZenBot.printSeperatorLine();
    }

}
