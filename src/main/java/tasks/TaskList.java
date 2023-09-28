package tasks;

import exceptions.InvalidCommandException;
import ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int taskId) {
        return this.tasks.get(taskId);
    }

    /**
     * List all tasks that have been added by user.
     */
    public void listTasks() {
        int taskCount = 0;
        String taskDescription;

        for (int i = 0; i < this.tasks.size(); i++){
            taskCount++;
            taskDescription = this.tasks.get(i).toString();
            Ui.printOneTabMessage(taskCount + ". " + taskDescription);
        }

        Ui.printLineDivider();
    }

    /**
     * Add a new task to tasks array
     * @param newTask is a class
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);

        int totalNumberOfTasks = this.tasks.size();
        String newTaskDescription = newTask.toString();

        Ui.printOneTabMessage("Gotcha! I have added the following task:");
        Ui.printTwoTabMessage(newTaskDescription);
        Ui.printOneTabMessage("Total no. of tasks: " + totalNumberOfTasks + " --- YOU'VE GOT THIS!\n");
        Ui.printLineDivider();
    }

    public void deleteTask(String userInput) throws InvalidCommandException {
        int taskId = Integer.parseInt(userInput.split(" ")[1]);
        if (taskId > this.tasks.size() || taskId < 1) {
            throw new InvalidCommandException();
        }

        int taskPosition = taskId - 1;
        String taskDescription = this.tasks.get(taskPosition).toString();

        Ui.printOneTabMessage("Gotcha! I have removed the following task:");
        Ui.printTwoTabMessage(taskDescription);

        this.tasks.remove(taskPosition);
        int totalNumberOfTasks = this.tasks.size();

        Ui.printOneTabMessage("Total no. of tasks: " + totalNumberOfTasks + " --- YOU'VE GOT THIS!\n");
        Ui.printLineDivider();
    }

}
