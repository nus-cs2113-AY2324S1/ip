package alice.tasks;

import alice.ui.Ui;

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
     * List all alice.tasks that have been added by user.
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
     * Add a new task to alice.tasks array
     * @param newTask is a class
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);

        int totalNumberOfTasks = this.tasks.size();
        String newTaskDescription = newTask.toString();

        Ui.printOneTabMessage("Gotcha! I have added the following task:");
        Ui.printTwoTabMessage(newTaskDescription);
        Ui.printOneTabMessage("Total no. of alice.tasks: " + totalNumberOfTasks + " --- YOU'VE GOT THIS!\n");
        Ui.printLineDivider();
    }

    public void deleteTask(int taskIndex) {
        String taskDescription = this.tasks.get(taskIndex).toString();

        Ui.printOneTabMessage("Gotcha! I have removed the following task:");
        Ui.printTwoTabMessage(taskDescription);

        this.tasks.remove(taskIndex);
        int totalNumberOfTasks = this.tasks.size();

        Ui.printOneTabMessage("Total no. of alice.tasks: " + totalNumberOfTasks + " --- YOU'VE GOT THIS!");
        Ui.printLineDivider();
    }

    public void markTask(int taskId) {
        tasks.get(taskId).markTask();
    }

    public void unmarkTask(int taskId) {
        tasks.get(taskId).unmarkTask();
    }

}
