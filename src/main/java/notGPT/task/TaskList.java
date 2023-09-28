package notGPT.task;

// import java.util.Scanner;   
// import java.io.File;
// import java.io.FileWriter;
// import java.io.IOException;
import java.util.ArrayList;

import notGPT.storage.Storage;


public class TaskList {
    private ArrayList<Task> taskList;
    private int taskCount;
    // private String line = "____________________________________________________________";

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

    // public void loadTasks() {
    //     try {
    //         File dataDirectory = new File("./data");
    //         if (!dataDirectory.exists()) {
    //             dataDirectory.mkdir();
    //         }

    //         File taskListFile = new File(dataDirectory, "tasks.txt");
    //         if (!taskListFile.exists()) {
    //             taskListFile.createNewFile();
    //         }

    //         Scanner sc = new Scanner(taskListFile);
    //         while (sc.hasNextLine()) {
    //             String task = sc.nextLine();
    //             String[] taskDetails = task.split("\\|");
    //             switch (taskDetails[0]) {
    //                 case "T":
    //                     addTodoFromFile(taskDetails);
    //                     break;
    //                 case "D":
    //                     addDeadlineFromFile(taskDetails);
    //                     break;
    //                 case "E":
    //                     addEventFromFile(taskDetails);
    //                     break;
    //             }
    //         }
    //     } catch (IOException e) {
    //         System.out.println("Error: Unable to create or access data file/directory!\n"
    //                 + e.getMessage() + "\n" + line + "\n");
    //     }
    // }

    // private void addTodoFromFile(String[] taskDetails) {
    //     String taskName = taskDetails[2];
    //     addTodo(taskName);
    //     if (taskDetails[1].equals("1")) {
    //         markTaskAsDone(taskCount);
    //     }
    // }

    // private void addDeadlineFromFile(String[] taskDetails) {
    //     String taskName = taskDetails[2];
    //     String deadline = taskDetails[3];
    //     addDeadline(taskName, deadline);
    //     if (taskDetails[1].equals("1")) {
    //         markTaskAsDone(taskCount);
    //     }
    // }

    // private void addEventFromFile(String[] taskDetails) {
    //     String taskName = taskDetails[2];
    //     String startTime = taskDetails[3];
    //     String endTime = taskDetails[4];
    //     addEvent(taskName, startTime, endTime);
    //     if (taskDetails[1].equals("1")) {
    //         markTaskAsDone(taskCount);
    //     }
    // }

    // public void saveTasks() {
    //     try {
    //         File dataDirectory = new File("./data");
    //         if (!dataDirectory.exists()) {
    //             dataDirectory.mkdir();
    //         }

    //         File taskListFile = new File(dataDirectory, "tasks.txt");
    //         if (!taskListFile.exists()) {
    //             taskListFile.createNewFile();
    //         }

    //         FileWriter fw = new FileWriter(taskListFile);
    //         for (int i = 0; i < taskCount; i++) {
    //             fw.write(taskList.get(i).toFileString() + "\n");
    //         }
    //         fw.close();
    //     } catch (IOException e) {
    //         System.out.println("Error: Unable to create or access data file/directory!\n"
    //                 + e.getMessage() + "\n" + line + "\n");
    //     }
    // }
}

