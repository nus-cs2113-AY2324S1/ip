package linguobot.task;
import linguobot.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(Task ... tasks) {
        taskList.addAll(List.of(tasks));
    }

    public void addTask(Task taskToAdd) {
        taskList.add(taskToAdd);
    }

    public void deleteTask(int taskIndex) {
        taskList.remove(taskIndex);
    }

    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }

    public void printTaskList() {
        int listSize = taskList.size();
        String[] texts = new String[listSize + 1];
        texts[0] = "Here are the tasks in your list:";
        for (int i = 1; i <= listSize; i++) {
            Task currentTask = taskList.get(i - 1);
            texts[i] = i + "." + currentTask.toString();
        }
        Ui.printMultipleText(texts);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    @Override
    public String toString() {
        return taskList.stream().map(Task::toString).collect(Collectors.joining("\n"));
    }
}
