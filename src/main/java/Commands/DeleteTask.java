package Commands;

import ZenBot.ZenBot;
import Tasks.Tasklist;

public class DeleteTask extends Command {
    
    private String commandString;
    private Tasklist tasks;
    private int taskNumber;

    public DeleteTask(String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        taskNumber = Integer.parseInt(commandString.substring(7));
        ZenBot.printSeperatorLine();
        System.out.println("\tI've playfully plucked this task away:");
        tasks.getTask(taskNumber).printTask();
        tasks.deleteTask(taskNumber);
        ZenBot.printSeperatorLine();
    }
}
