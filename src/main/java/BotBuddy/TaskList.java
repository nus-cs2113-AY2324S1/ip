package BotBuddy;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArrayList;

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    public void addTodoToTaskList(String parameters) {
        taskArrayList.add(new Todo(parameters));
    }

    public void addEventToTaskList(String[] eventDetails) {
        String eventName = eventDetails[0];
        String eventFrom = eventDetails[1];
        String eventTo = eventDetails[2];
        taskArrayList.add(new Event(eventName, eventFrom, eventTo));
    }

    public void addDeadlineToTaskList(String[] deadlineDetails) {
        String deadlineName = deadlineDetails[0];
        String deadlineBy = deadlineDetails[1];
        taskArrayList.add(new Deadline(deadlineName, deadlineBy));
    }

    public void listTasksInTaskList(int noOfTasks) {
        for (int i = 0; i < noOfTasks; i++) {
            System.out.println(i + 1 + ". " + taskArrayList.get(i));
        }
    }

    public void markTaskInTaskList(int taskToMark) {
        taskArrayList.get(taskToMark).markAsDone();
    }

    public void unmarkTaskInTaskList(int taskToUnmark) {
        taskArrayList.get(taskToUnmark).markAsUndone();
    }

    public void removeTaskFromTaskList(int taskToDelete) {
        taskArrayList.remove(taskToDelete);
    }
}