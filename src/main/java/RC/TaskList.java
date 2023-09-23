package RC;

import RC.UI.Ui;
import RC.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static final String MESSAGE_INTEGER_MISSING = "\tOOPS!!! Please enter a valid integer.";
    private static final String MESSAGE_INDEX_OUT_OF_RANGE = "\tOOPS!!! Index is out of range of list.";
    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task, Ui ui) {
        tasks.add(task);

        String message = "\tGot it. I've added this task:\n\t  " + task.toString() + "\n\tNow you have " + tasks.size();
        message += getNumTasks();

        ui.showMessage(message);
    }

    public void load(Task task) {
        tasks.add(task);
    }

    public void delete(String index, Ui ui) throws RCException {
        final int taskNum = getTaskNum(index);

        String message = "\tNoted. I've removed the following task:\n\t  " + tasks.get(taskNum);
        tasks.remove(taskNum);
        message += "\n\tNow you have " + tasks.size();
        message += getNumTasks();
        ui.showMessage(message);
    }

    public void markAsDone(String index, Ui ui) throws RCException {
        final int taskNum = getTaskNum(index);

        tasks.get(taskNum).markAsDone();
        ui.showMessage("\tNice! I've marked this task as done:\n\t  " + tasks.get(taskNum));
    }

    public void unmarkTask(String index, Ui ui) throws RCException {
        final int taskNum = getTaskNum(index);

        tasks.get(taskNum).unmarkTask();
        ui.showMessage("\tOK, I've marked this task as not done yet:\n\t  " + tasks.get(taskNum));
    }

    public boolean isValidIndex(int index) {
        return (index >= 0 && index < tasks.size());
    }

    private String getNumTasks() {
        if (tasks.size() > 1) {
            return " tasks in the list.";
        }
        return " task in the list.";
    }

    private int getTaskNum(String index) throws RCException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new RCException(MESSAGE_INTEGER_MISSING);
        }

        if (!isValidIndex(taskNum)) {
            throw new RCException(MESSAGE_INDEX_OUT_OF_RANGE);
        }
        return taskNum;
    }
}
