package commands;

import tasks.Todo;
import tasks.Tasklist;
import zenbot.UI;
import exceptions.TaskEmptyDescriptionException;

/**
 ** Represents a command to add a todo task to the tasklist.
 */
public class AddTodo extends Command {

    private String commandString;
    private Tasklist tasks;
    private String description;

    public AddTodo(String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    /**
     * Create a new Todo object and add it to the tasklist.
     * Print a message to the user to indicate that the Todo task has been added.
     * @throws TaskEmptyDescriptionException if the description of the todo task is empty.
     */
    @Override
    public void execute() throws TaskEmptyDescriptionException {
        description = commandString.substring(5);
        tasks.addTask(new Todo(description));

        UI.printSeperatorLine();
        System.out.println("\tBehold, a new endeavor enters the realm: " + description);
        System.out.print("\tThe grand tally of tasks has reached a harmonious count of ");
        System.out.println(tasks.getTaskListSize() + " in all.");
        UI.printSeperatorLine();
    }

}
