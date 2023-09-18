package RC;

import RC.task.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);

        String message = "\tGot it. I've added this task:\n\t  " + task.toString() + "\n\tNow you have " + tasks.size();
        message += printNumTasks();

        System.out.println(message);
    }

    public void load(Task task) {
        tasks.add(task);
    }

    public void delete(String index) throws RCException {
        final int taskNum = getTaskNum(index);

        String message = "\tNoted. I've removed the following task:\n\t  " + tasks.get(taskNum);
        tasks.remove(taskNum);
        message += "\n\tNow you have " + tasks.size();
        message += printNumTasks();
        System.out.println(message);
    }

    public void markAsDone(String index) throws RCException {
        final int taskNum = getTaskNum(index);

        tasks.get(taskNum).markAsDone();
        System.out.println("\tNice! I've marked this task as done:\n\t  " + tasks.get(taskNum));
    }

    public void unmarkTask(String index) throws RCException {
        final int taskNum = getTaskNum(index);

        tasks.get(taskNum).unmarkTask();
        System.out.println("\tOK, I've marked this task as not done yet:\n\t  " + tasks.get(taskNum));
    }

    public boolean isValidIndex(int index) {
        return (index >= 0 && index < tasks.size());
    }

    private String printNumTasks() {
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
            String errorMessage = "\tOOPS!!! Please enter a valid integer.";
            throw new RCException(errorMessage);
        }

        if (!isValidIndex(taskNum)) {
            String errorMessage = "\tOOPS!!! Index is out of range of list.";
            throw new RCException(errorMessage);
        }
        return taskNum;
    }
}
