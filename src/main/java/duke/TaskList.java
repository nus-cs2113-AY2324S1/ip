package duke;

import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> taskList = new ArrayList<>();
    private final Ui ui = new Ui();

    public void add(Task task){
        taskList.add(task);
        addTaskCallback(task);
    }

    public void deleteTask(int taskCount){
        Task oldTask = TaskList.taskList.get(taskCount);
        taskList.remove(taskCount);
        deleteTaskCallback(oldTask);
    }

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

    public void addTaskCallback(Task task){
        ui.printLine();
        ui.echo("    Got it. I've added this task:\n");
        ui.printIndentTask();
        task.printTask();
        ui.echo("    Now you have " + (taskList.size())
                + " tasks in the list.\n");
        ui.printLine();
    }

    public void deleteTaskCallback(Task task){
        ui.printLine();
        ui.echo("    Noted. I've removed this task:\n");
        ui.printIndentTask();
        task.printTask();
        ui.echo("    Now you have " + taskList.size()
                + " tasks in the list.\n");
        ui.printLine();
    }

    public int getSize(){
        return taskList.size();
    }

    public void findTask(String word){
        boolean foundItem = false;
        ui.printLine();
        for (int i = 0; i < taskList.size(); i++){
            if (taskList.get(i).description.contains(word)) {
                ui.echo("    " + (i+1) + ".");
                taskList.get(i).printTask();
                foundItem = true;
            }
        }

        if (!foundItem){
            ui.echo("    No task on the tasklist matches your keyword :-(\n");
        }
        ui.printLine();
    }
}
