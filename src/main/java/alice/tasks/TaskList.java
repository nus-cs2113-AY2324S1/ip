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
     * List all tasks that have been added by user
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
     * Add a new task to TaskList object
     *
     * @param newTask the new Task object to be added
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);

        int totalNumberOfTasks = this.tasks.size();
        String newTaskDescription = newTask.toString();

        Ui.printOneTabMessage("Gotcha! I have added the following task:");
        Ui.printTwoTabMessage(newTaskDescription);
        Ui.printOneTabMessage("Total no. of alice.tasks: " + totalNumberOfTasks + " --- YOU'VE GOT THIS!");
        Ui.printLineDivider();
    }

    /**
     * Delete specific task from TaskList object
     *
     * @param taskIndex the index of the task item in the ArrayList
     */
    public void deleteTask(int taskIndex) {
        String taskDescription = this.tasks.get(taskIndex).toString();

        Ui.printOneTabMessage("Gotcha! I have removed the following task:");
        Ui.printTwoTabMessage(taskDescription);

        this.tasks.remove(taskIndex);
        int totalNumberOfTasks = this.tasks.size();

        Ui.printOneTabMessage("Total no. of alice.tasks: " + totalNumberOfTasks + " --- YOU'VE GOT THIS!");
        Ui.printLineDivider();
    }

    /**
     * Print tasks that consist of keyword.
     *
     * @param keyword the word that user is searching for
     */
    public void printFilteredTasks(String keyword) {
        int taskNumber = 0;
        boolean haveFilteredTask = false;

        Ui.printOneTabMessage("Here are the tasks you are searching for: ");

        for (Task task : tasks) {
            taskNumber++;
            String description = task.getDescription();

            //Finding for keyword the task
            boolean keywordIsFound = false;
            String[] wordsInDescription = description.split(" ");
            for (String word : wordsInDescription) {
                word = word.toLowerCase();
                if (keyword.equals(word)) {
                    keywordIsFound = true;
                    haveFilteredTask = true;
                    break;
                }
            }

            //Printing filtered task item
            if (keywordIsFound) {
                Ui.printTwoTabMessage(taskNumber + ". " + task.toString());
            }
        }

        if (!haveFilteredTask) {
            Ui.printTwoTabMessage("<No task found in your wonderland>");
        }
        Ui.printLineDivider();
    }
}
