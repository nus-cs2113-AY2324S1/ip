package notGPT.task;

import java.util.Scanner;   
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    private Task[] taskList;
    private int taskCount;
    private String line = "____________________________________________________________";

    public TaskList() {
        this.taskList = new Task[100];
        this.taskCount = 0;
    }

    public void addTodo(String taskName) {
        Task newTask = new ToDo(taskName);
        taskList[taskCount] = newTask;
        taskCount++;
    }

    public void addDeadline(String taskName, String deadline) {
        Task newTask = new Deadlines(taskName, deadline);
        taskList[taskCount] = newTask;
        taskCount++;
    }

    public void addEvent(String taskName, String startTime, String endTime) {
        Task newTask = new Event(taskName, startTime, endTime);
        taskList[taskCount] = newTask;
        taskCount++;
    }

    public String[] getTasks() {
        String[] tasks = new String[taskCount];
        for (int i = 0; i < taskCount; i++) {
            tasks[i] = taskList[i].toString();
        }
        return tasks;
    }

    public void markTaskAsDone(int taskNumber) {
        taskList[taskNumber - 1].markAsDone();
    }   

    public void unmarkTaskAsDone(int taskNumber) {
        taskList[taskNumber - 1].unmarkAsDone();
    }

    public void deleteTask(int taskNumber) {
        taskList[taskNumber - 1] = null;
        for (int i = taskNumber - 1; i < taskCount - 1; i++) {
            taskList[i] = taskList[i + 1];
        }
        taskCount--;
    }

    public String[] findTasks(String keyword) {
        String[] tasks = new String[taskCount];
        int taskIndex = 0;
        for (int i = 0; i < taskCount; i++) {
            if (taskList[i].getTaskName().contains(keyword)) {
                tasks[taskIndex] = taskList[i].toString();
                taskIndex++;
            }
        }
        return tasks;
    }
    
    public int getTaskCount() {
        return taskCount;
    }

    public Task getTaskByNumber(int taskNumber) {
        return taskList[taskNumber - 1];
    }

    public void loadTasks() {
        try {
            File dataDirectory = new File("./data");
            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }
            
            File taskListFile = new File(dataDirectory, "tasks.txt");
            if (!taskListFile.exists()) {
                taskListFile.createNewFile();
            }

            Scanner sc = new Scanner(taskListFile);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] taskDetails = task.split("\\|"); 
                switch (taskDetails[0]) {
                    case "T":
                        addTodoFromFile(taskDetails);
                        break;
                    case "D":
                        addDeadlineFromFile(taskDetails);
                        break;
                    case "E":
                        addEventFromFile(taskDetails);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to create or access data file/directory!\n" 
            + e.getMessage() + "\n" + line + "\n");
        }
    }

    private void addTodoFromFile(String[] taskDetails) {
        String taskName = taskDetails[2];
        addTodo(taskName);
        if (taskDetails[1].equals("1")) {
            markTaskAsDone(taskCount);
        }
    }

    private void addDeadlineFromFile(String[] taskDetails) {
        String taskName = taskDetails[2];
        String deadline = taskDetails[3];
        addDeadline(taskName, deadline);
        if (taskDetails[1].equals("1")) {
            markTaskAsDone(taskCount);
        }
    }

    private void addEventFromFile(String[] taskDetails) {
        String taskName = taskDetails[2];
        String startTime = taskDetails[3];
        String endTime = taskDetails[4];
        addEvent(taskName, startTime, endTime);
        if (taskDetails[1].equals("1")) {
            markTaskAsDone(taskCount);
        }
    }

    public void saveTasks() {
        try {
            File dataDirectory = new File("./data");
            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }

            File taskListFile = new File(dataDirectory, "tasks.txt");
            if (!taskListFile.exists()) {
                taskListFile.createNewFile();
            }

            FileWriter fw = new FileWriter(taskListFile);
            for (int i = 0; i < taskCount; i++) {
                fw.write(taskList[i].toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to create or access data file/directory!\n" 
            + e.getMessage() + "\n" + line + "\n");
        }
    }
}
