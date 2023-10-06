package duke;

import java.util.ArrayList;

/**
 * Class to handle operations regarding the TaskList as a whole without
 * specific manipulations to the Task objects itself.
 */
public class TaskList {

    public static ArrayList<Task> taskList = new ArrayList<>();
    private final Ui ui = new Ui();

    /**
     * Method to add task object to the taskList.
     * @param task Task object to add to the taskList
     */
    public void add(Task task){
        taskList.add(task);
        addTaskCallback(task);
    }

    /**
     * Method to delete a task object from the task list given its index.
     * @param taskCount Index of Task object to be deleted from the Task list.
     */
    public void deleteTask(int taskCount){
        Task oldTask = TaskList.taskList.get(taskCount);
        taskList.remove(taskCount);
        deleteTaskCallback(oldTask);
    }

    /**
     * Method to list all the tasks in the list.
     */
    public void listTask(){
        ui.printLine();
        if (!taskList.isEmpty()) {
            for (Task task : taskList) {
                ui.echo((taskList.indexOf(task) + 1) + ".");
                task.printTask();
            }
        } else {
            ui.echo("There are no tasks on your list!\n");
        }
        ui.printLine();
    }

    /**
     * Method to create the respective task object based on the user input.
     * @param userInput User input string to be assigned to the respective subclasses based on the
     *                  task type command by the user.
     */
    public void addTasks(String[] userInput){
        switch (userInput[0]) {
        case "todo":
            Task todo = new ToDo(userInput, false);
            add(todo);
            break;
        case "deadline":
            Deadline deadline = new Deadline(userInput, false);
            add(deadline);
            break;
        case "event":
            Task event = new Event(userInput, false);
            add(event);
            break;
        default:
        }
    }

    /**
     * Method to mark the task as done or not done based on its index.
     * @param taskCount Index of task object to mark/unmark.
     * @param isMark boolean to diffentiate between marking and unmarking.
     */
    public void markItem(int taskCount, boolean isMark) {
        ui.printLine();
        if (isMark) {
            taskList.get(taskCount).isDone = true;
            ui.printMarked();
            ui.echo(taskList.get(taskCount).getStatusIcon()
                    + " " + taskList.get(taskCount).description + "\n");
        } else {
            taskList.get(taskCount).isDone = false;
            ui.printUnmarked();
            ui.echo(taskList.get(taskCount).getStatusIcon()
                    + " " + taskList.get(taskCount).description + "\n");
        }
        ui.printLine();
    }

    /**
     * Method to output acknowledgement of adding the task to the tasklist.
     * @param task Task object added to be reflected in the callback message.
     */
    private void addTaskCallback(Task task){
        ui.printLine();
        ui.echo("Got it. I've added this task:\n");
        ui.printIndentTask();
        task.printTask();
        ui.echo("Now you have " + (taskList.size())
                + " tasks in the list.\n");
        ui.printLine();
    }

    /**
     * Method to output acknowledgement of deleting a task from the tasklist.
     * @param task Task object added to be reflected in the callback message.
     */
    private void deleteTaskCallback(Task task){
        ui.printLine();
        ui.echo("Noted. I've removed this task:\n");
        ui.printIndentTask();
        task.printTask();
        ui.echo("Now you have " + taskList.size()
                + " tasks in the list.\n");
        ui.printLine();
    }

    /**
     * Method to get the current size of the taskList.
     * @return Size of the taskList.
     */
    public int getSize(){
        return taskList.size();
    }

    public void findTask(String word){
        boolean foundItem = false;
        ui.printLine();
        for (int i = 0; i < taskList.size(); i++){
            if (taskList.get(i).description.contains(word)) {
                ui.echo((i+1) + ".");
                taskList.get(i).printTask();
                foundItem = true;
            }
        }

        if (!foundItem){
            ui.echo("No task on the tasklist matches your keyword :-(\n");
        }
        ui.printLine();
    }
}
