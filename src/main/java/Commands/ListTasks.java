package commands;

import tasks.Tasklist;
import zenbot.UI;

/**
 * Represents a command to list all tasks
 */
public class ListTasks extends Command {
    
    private Tasklist tasks;

    public ListTasks(Tasklist tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints all tasks in the tasklist
     */
    @Override
    public void execute() {
        if (tasks.getTaskListSize() == 0) {
            System.out.println("\tYour list is empty, please add some tasks!");
            return;
        }
        
        UI.printSeperatorLine();
        System.out.println("\tAllow me to unveil the tasks dwelling within your list:");
        tasks.printTaskList();
        UI.printSeperatorLine();
    }

}
