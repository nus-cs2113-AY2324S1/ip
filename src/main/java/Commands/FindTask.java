package commands;

import java.util.ArrayList;

import tasks.Task;
import tasks.Tasklist;
import zenbot.UI;

public class FindTask extends Command {
    
    private String commandString;
    private Tasklist tasks;
    private String keyword;
    
    public FindTask(String commandString, Tasklist tasks) {
        this.commandString = commandString;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        keyword = commandString.substring(5);
        ArrayList<Task> resultList = tasks.searchTasks(keyword);
        UI.printSeperatorLine();
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < resultList.size(); i++) {
            System.out.print("\t" + (i + 1) + ".");
            resultList.get(i).printTask();
        }
        UI.printSeperatorLine();
    }
}
