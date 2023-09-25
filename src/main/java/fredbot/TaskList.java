package fredbot;

import fredbot.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public void markTask(int index, boolean mark) {
        tasks.get(index).setDone(mark);
    }

    public void printTasks() {
        StringBuilder taskList = new StringBuilder();
        taskList.append(INDENT).append(TASK_LIST_MESSAGE);
        int numTask = Task.getNumTask();
        for (int i = 0; i < numTask; i++) {
            String number = (i + 1) + ".";
            taskList.append(INDENT).append(number).append(tasks.get(i).toString()).append("\n"); // Can be formatted
        }
        printMessage(taskList.toString());
    }
}
