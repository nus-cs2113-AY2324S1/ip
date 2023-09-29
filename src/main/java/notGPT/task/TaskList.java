package notGPT.task;

import java.util.ArrayList;

import notGPT.storage.Storage;


public class TaskList {
    private ArrayList<Task> taskList;
    private int taskCount;

    public TaskList(Storage storage) {
        this.taskList = storage.getBuffer();
        this.taskCount = taskList.size();
    }

    public void addTodo(String taskName) {
        Task newTask = new ToDo(taskName);
        taskList.add(newTask);
        taskCount++; 
    }

    public void addDeadline(String taskName, String deadline) {
        Task newTask = new Deadlines(taskName, deadline);
        taskList.add(newTask);
        taskCount++;
    }

    public void addEvent(String taskName, String startTime, String endTime) {
        Task newTask = new Event(taskName, startTime, endTime);
        taskList.add(newTask);
        taskCount++;
    }

    public String[] getTasks() {
        String[] tasks = new String[taskCount];
        for (int i = 0; i < taskCount; i++) {
            tasks[i] = taskList.get(i).toString();
        }
        return tasks;
    }

    public void markTaskAsDone(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskCount) {
            taskList.get(taskNumber - 1).markAsDone();
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void unmarkTaskAsDone(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskCount) {
            taskList.get(taskNumber - 1).unmarkAsDone();
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void deleteTask(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskCount) {
            taskList.remove(taskNumber - 1);
            taskCount--;
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public String[] findTasks(String keyword) {
        ArrayList<String> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getTaskName().contains(keyword)) {
                matchingTasks.add(task.toString());
            }
        }
        return matchingTasks.toArray(new String[0]);
    }

    public int getTaskCount() {
        return taskCount;
    }

    public Task getTaskByNumber(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskCount) {
            return taskList.get(taskNumber - 1);
        } else {
            System.out.println("Invalid task number.");
            return null;
        }
    }
}

