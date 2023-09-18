package commands;

import tasks.Deadline;
import tasks.Tasklist;
import exceptions.TaskEmptyDescriptionException;
import zenbot.UI;

public class AddDeadline extends Command {
    
    private String BY_DELIMETER = "/by";
    private String commandString;
    private Tasklist tasks;
    private String description;
    private String deadline;
    
    public AddDeadline(String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    @Override
    public void execute() throws TaskEmptyDescriptionException {
        description = commandString.substring(9, commandString.indexOf(BY_DELIMETER) - 1);
        deadline = commandString.substring(commandString.indexOf(BY_DELIMETER) + 4);
        tasks.addTask(new Deadline(description, deadline));

        UI.printSeperatorLine();
        System.out.println("\tBehold, a new endeavor enters the realm: " + description);
        System.out.print("\tThe grand tally of tasks has reached a harmonious count of ");
        System.out.println(tasks.getTaskListSize() + " in all.");
        UI.printSeperatorLine();
    }
}
