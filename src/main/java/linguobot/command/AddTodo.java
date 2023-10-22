package linguobot.command;

import linguobot.Ui;
import linguobot.task.Todo;
import linguobot.task.TaskList;

import static linguobot.file.TaskFile.saveTaskListToFile;

/**
 * The AddTodo class represents a command to add a <code>Todo</code> task to the LinguoBot application.
 */
public class AddTodo extends Command{
    private final Todo taskToAdd;
    private final TaskList taskList;

    /**
     * Constructs a new AddTodo command.
     * @param taskToAdd The <code>Todo</code> task to be added.
     * @param taskList The taskList where the task will be added.
     */
    public AddTodo(Todo taskToAdd, TaskList taskList) {
        this.taskToAdd = taskToAdd;
        this.taskList = taskList;
    }

    /**
     * Executes the AddTodo command, adding the specified <code>Todo</code> task to the taskList and saving the updated list to taskFile.
     */
    @Override
    public void execute() {
        taskList.addTask(taskToAdd);
        saveTaskListToFile(taskList);
        Ui.printMultipleText(new String[] {"Got it. I've added this task:", taskToAdd.toString(),
                "Now you have " + taskList.getNumberOfTasks() + " task(s) in the list."});
    }
}
