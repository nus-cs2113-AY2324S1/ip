package Commands;

import Exceptions.TaskEmptyDescriptionException;
import Tasks.Event;
import Tasks.Tasklist;
import ZenBot.ZenBot;

public class AddEvent extends Command {

    private String commandString;
    private Tasklist tasks;
    private String description;
    private String startTime;
    private String endTime;
    
    public AddEvent (String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    @Override
    public void execute() throws TaskEmptyDescriptionException {
        description = commandString.substring(6, commandString.indexOf("/from") - 1);
        startTime = commandString.substring(commandString.indexOf("/from") + 6, commandString.indexOf("/to") - 1);
        endTime = commandString.substring(commandString.indexOf("/to") + 4);
        tasks.addTask(new Event(description, startTime, endTime));

        ZenBot.printSeperatorLine();
        System.out.println("\tBehold, a new endeavor enters the realm: " + description);
        System.out.print("\tThe grand tally of tasks has reached a harmonious count of ");
        System.out.println(tasks.getTaskListSize() + " in all.");
        ZenBot.printSeperatorLine();
    }
}
