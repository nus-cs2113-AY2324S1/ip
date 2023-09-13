package commands;

import tasks.Todo;
import tasks.Tasklist;
import zenbot.ZenBot;
import exceptions.TaskEmptyDescriptionException;

public class AddTodo extends Command {

    private String commandString;
    private Tasklist tasks;
    private String description;

    public AddTodo(String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    @Override
    public void execute() throws TaskEmptyDescriptionException {
        description = commandString.substring(5);
        tasks.addTask(new Todo(description));

        ZenBot.printSeperatorLine();
        System.out.println("\tBehold, a new endeavor enters the realm: " + description);
        System.out.print("\tThe grand tally of tasks has reached a harmonious count of ");
        System.out.println(tasks.getTaskListSize() + " in all.");
        ZenBot.printSeperatorLine();
    }

}
