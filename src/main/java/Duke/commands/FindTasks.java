package duke.commands;

import duke.Task;
import duke.inputProcess.TaskList;

public class FindTasks {
    String userInput;
    TaskList tasks;
    public FindTasks(String userInput, TaskList tasks){
        this.userInput = userInput;
        this.tasks = tasks;
    }
    public void find() {
        int count = 1;
        for (Task task : tasks.getList()) {
            if (task.getDescription().toLowerCase().contains(userInput.toLowerCase())) {
                if (count == 1){
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println("\t" +count + "."  + task);
                count++;
            }
        }
        if (count == 1){
            System.out.println("\tCannot find task with " + userInput);
        }
    }
}
