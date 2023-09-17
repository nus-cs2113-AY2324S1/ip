package RC;

import RC.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        TaskList.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) throws RCException {
        TaskList.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);

        String message = "\tGot it. I've added this task:\n\t  " + task.toString() + "\n\tNow you have " + tasks.size();
        if (tasks.size() > 1) {
            message += " tasks in the list.";
        } else {
            message += " task in the list.";
        }

        System.out.println(message);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public Task getTask(int taskNum) {
        return tasks.get(taskNum);
    }

    public int getSize() {
        return tasks.size();
    }
}
