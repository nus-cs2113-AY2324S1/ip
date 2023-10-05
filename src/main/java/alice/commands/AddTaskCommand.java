package alice.commands;

import alice.tasks.*;

public class AddTaskCommand extends Command{
    private Task newTask;
    private TaskList tasks;

    public AddTaskCommand(Task newTask, TaskList tasks) {
        this.newTask = newTask;
        this.tasks = tasks;
    }

    public void handleCommand() {
        this.tasks.addTask(this.newTask);
    }

}
