package Commands;

import ZenBot.ZenBot;
import Exceptions.OutOfRangeException;
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
    public void execute() throws OutOfRangeException {
        taskNumber = Integer.parseInt(commandString.substring(7));
        if (taskNumber > tasks.getTaskListSize() || taskNumber < 1) {
            throw new OutOfRangeException();
        }
        ZenBot.printSeperatorLine();
        System.out.println("\tI've playfully plucked this task away:");
        tasks.getTask(taskNumber).printTask();
        ZenBot.printSeperatorLine();
        tasks.deleteTask(taskNumber);
    }
}
