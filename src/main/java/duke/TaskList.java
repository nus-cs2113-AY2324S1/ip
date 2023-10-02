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
     * @param task Task object to add to the taskList
     */
    public void add(Task task){
        taskList.add(task);
        addTaskCallback(task);
    }

    /**
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
                ui.echo("    " + (taskList.indexOf(task) + 1) + ".");
                task.printTask();
            }
        }
        ui.printLine();
    }

    /**
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
     * @param taskCount Index of task object to mark/unmark.
     * @param isMark boolean to diffentiate between marking and unmarking.
     */
    public void markItem(int taskCount, boolean isMark) {
        ui.printLine();
        if (isMark) {
            taskList.get(taskCount).isDone = true;
            ui.printMarked();
            ui.echo("       " + taskList.get(taskCount).getStatusIcon()
                    + " " + taskList.get(taskCount).description + "\n");
        } else {
            taskList.get(taskCount).isDone = false;
            ui.printUnmarked();
            ui.echo("       " + taskList.get(taskCount).getStatusIcon()
                    + " " + taskList.get(taskCount).description + "\n");
        }
        ui.printLine();
    }

    /**
     * @param task Task object added to be reflected in the callback message.
     */
    public void addTaskCallback(Task task){
        ui.printLine();
        ui.echo("    Got it. I've added this task:\n");
        ui.printIndentTask();
        task.printTask();
        ui.echo("    Now you have " + (taskList.size())
                + " tasks in the list.\n");
        ui.printLine();
    }

    /**
     * @param task Task object added to be reflected in the callback message.
     */
    public void deleteTaskCallback(Task task){
        ui.printLine();
        ui.echo("    Noted. I've removed this task:\n");
        ui.printIndentTask();
        task.printTask();
        ui.echo("    Now you have " + taskList.size()
                + " tasks in the list.\n");
        ui.printLine();
    }

    /**
     * @return Size of the taskList.
     */
    public int getSize(){
        return taskList.size();
    }
}
