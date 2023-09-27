package kenergeticbot.fileaccess;

import kenergeticbot.TaskList;
import kenergeticbot.task.Deadline;
import kenergeticbot.task.Event;
import kenergeticbot.task.Task;
import kenergeticbot.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;
    public static final String folderPath = "data";

    public Storage(String filePath) {
        this.filePath = filePath;
        initializeStorage(filePath);
    }

    public void initializeStorage(String filePath) {
        File f = new File(filePath);
        checkFolderExist();
        try {
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }

    public void checkFolderExist() {
        File f = new File(folderPath);
        if (f.mkdir()) {
            System.out.println("Directory is created");
        } else {
            System.out.println("Directory cannot be created");
        }
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }

    public void loadPreviousList(TaskList taskList) {
        try {
            System.out.println("Loading previous list");
            readFromFile(filePath, taskList);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
    }

    public void readFromFile(String filePath, TaskList taskList) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        if (f.length() == 0) {
            return;
        }
        Scanner s = new Scanner(f);
        ArrayList<String> previousTaskList = new ArrayList<String>();
        while (s.hasNext()) {
            previousTaskList.add(s.nextLine());
        }
        s.close();

        for (String task : previousTaskList) {
            String[] taskVariables = task.split(" \\| ");
            String taskType = taskVariables[0];
            String taskMark = taskVariables[1];
            String taskDescription = taskVariables[2];

            switch (taskType) {
                case "T" :
                    Task previousTodo = new Todo(taskDescription);
                    taskList.add(previousTodo);
                    System.out.println("adding:" + previousTodo);
                    break;
                case "D" :
                    String taskDeadline = taskVariables[3];
                    Task previousDeadline = new Deadline(taskDescription, taskDeadline);
                    taskList.add(previousDeadline);
                    System.out.println("adding:" + previousDeadline);
                    break;
                case "E" :
                    String taskEventDateTime = taskVariables[3];
                    Task previousEvent = new Event(taskDescription, taskEventDateTime);
                    taskList.add(previousEvent);
                    System.out.println("adding:" + previousEvent);
                    break;
                default :
                    throw new IllegalStateException("Unexpected value: " + taskType);
            }
        }
        try {
            new FileWriter(filePath, false).close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(String textToAdd) {
        File f = new File(filePath);
        if (f.length() == 0) {
            try {
                FileWriter myWriter = new FileWriter(filePath);
                myWriter.write(textToAdd);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                FileWriter myWriter = new FileWriter(filePath, true);
                myWriter.write(textToAdd);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveList(TaskList taskList) {
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(taskList.getTask(i));
            writeToFile(taskList.getTask(i).printTaskToSave());
        }
    }
}
