package linguobot.command;

import linguobot.Ui;
import linguobot.task.Todo;
import linguobot.task.TaskList;

import static linguobot.file.TaskFile.saveTaskListToFile;

public class AddTodo extends Command{
    private final Todo taskToAdd;
    private final TaskList taskList;

    public AddTodo(Todo taskToAdd, TaskList taskList) {
        this.taskToAdd = taskToAdd;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        taskList.addTask(taskToAdd);
        saveTaskListToFile(taskList);
        Ui.printMultipleText(new String[] {"Got it. I've added this task:", taskToAdd.toString(),
                "Now you have " + taskList.getNumberOfTasks() + " task(s) in the list."});
    }
}
