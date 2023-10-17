package Chatty.Command;

import Chatty.Storage;
import Chatty.TaskList;
import Chatty.Ui;
import Chatty.tasks.*;

public class findCommand extends Command{

    public findCommand(String input){
        super(input);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String keyword = input.substring(5).trim();
        System.out.println("Here are the matching tasks in your list:");
        int matchCount = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.containsKeyword(keyword)) {
                ui.printMessage((i + 1) + ". " + task.getDescription());
                matchCount++;
            }
        }
        if (matchCount == 0) {
            System.out.println("No matching tasks found.");
        }
    }
}
