package commands;

import exceptions.UserInputValidation;
import exceptions.ZranExceptions;
import taskmanagement.Event;
import taskmanagement.Storage;
import taskmanagement.Task;
import taskmanagement.TaskList;
import userinputs.Ui;

import java.util.ArrayList;

public class FindTaskCommand extends Commands{
    public FindTaskCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        String key = input.substring(Commands.USER_FIND_COMMAND.length()).trim();
        filteredTasks = findTask(key, tasks.listItems.toArray(new Task[0]));
        if(!filteredTasks.isEmpty()){
            Ui.echo(filteredTasks);
        } else{
            System.out.println("    Oopsies! Looks like there are no tasks with " +
                    input + "!");
            Ui.showLine();
        }
    }

    public static ArrayList<Task> findTask(String key, Task... items) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        int index = 0;
        for (Task item : items) {
            if (item != null && item.getDescription().contains(key)) {
                filteredTasks.add(item);
            }
        }
        return filteredTasks;
    }
}
