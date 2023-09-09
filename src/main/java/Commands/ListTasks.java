package Commands;

import Tasks.Tasklist;
import ZenBot.ZenBot;

public class ListTasks extends Command {
    
    private Tasklist tasks;

    public ListTasks(Tasklist tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        ZenBot.printSeperatorLine();
        System.out.println("\tAllow me to unveil the tasks dwelling within your list:");
        tasks.printTaskList();
        ZenBot.printSeperatorLine();
    }

}
