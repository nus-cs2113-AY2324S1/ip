package commands;

import tasks.Event;
import tasks.Tasklist;
import zenbot.UI;
import exceptions.TaskEmptyDescriptionException;

/**
 ** Represents a command to add an event task to the tasklist.
 */
public class AddEvent extends Command {

    private String FROM_DELIMITER = "/from";
    private String TO_DELIMETER = "/to";

    private String commandString;
    private Tasklist tasks;
    private String description;
    private String startTime;
    private String endTime;
    
    public AddEvent (String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    /**
     * Create a new Event object and add it to the tasklist.
     * Print a message to the user to indicate that the Event task has been added.
     * @throws TaskEmptyDescriptionException if the description of the event task is empty.
     */
    @Override
    public void execute() throws TaskEmptyDescriptionException {
        description = commandString.substring(6, commandString.indexOf(FROM_DELIMITER) - 1);
        startTime = commandString.substring(commandString.indexOf(FROM_DELIMITER) + 6, commandString.indexOf(TO_DELIMETER) - 1);
        endTime = commandString.substring(commandString.indexOf("/to") + 4);
        tasks.addTask(new Event(description, startTime, endTime));

        UI.printSeperatorLine();
        System.out.println("\tBehold, a new endeavor enters the realm: " + description);
        System.out.print("\tThe grand tally of tasks has reached a harmonious count of ");
        System.out.println(tasks.getTaskListSize() + " in all.");
        UI.printSeperatorLine();
    }
}
