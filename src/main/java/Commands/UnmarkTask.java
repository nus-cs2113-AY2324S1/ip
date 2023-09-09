package Commands;

import Tasks.Tasklist;
import ZenBot.ZenBot;

public class UnmarkTask extends Command {
    
    private String commandString;
    private Tasklist tasks;
    private int taskNumber;

    public UnmarkTask(String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        taskNumber = Integer.parseInt(commandString.substring(7));
        tasks.markTaskAsUndone(taskNumber);
        ZenBot.printSeperatorLine();
        System.out.println("\tAh, chuckles! I've playfully returned this task to its untamed state:");
        tasks.getTask(taskNumber).printTask();
        ZenBot.printSeperatorLine();
    }

}
