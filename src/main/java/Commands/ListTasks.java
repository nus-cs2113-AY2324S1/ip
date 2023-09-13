package commands;

import tasks.Tasklist;
import zenbot.UI;

public class ListTasks extends Command {
    
    private Tasklist tasks;

    public ListTasks(Tasklist tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        UI.printSeperatorLine();
        System.out.println("\tAllow me to unveil the tasks dwelling within your list:");
        tasks.printTaskList();
        UI.printSeperatorLine();
    }

}
