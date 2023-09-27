package kenergeticbot.fileaccess;

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

public abstract class Save  {

    public static final String filePath = "data/KenergeticBot.txt";
    public static final String folderPath = "data";
    public static void checkFileExist() {
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

    public static void checkFolderExist() {
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

    public static void loadPreviousList (ArrayList<Task> taskList) {
        try {
            readFromFile(filePath, taskList);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
    }

    public static void readFromFile (String filePath, ArrayList<Task> taskList) throws FileNotFoundException {
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
                Task previousTodo = new Todo(taskDescription, "[T]");
                taskList.add(previousTodo);
                break;
            case "D" :
                String[] taskDeadline = taskDescription.split("\\(");
                Task previousDeadline = new Deadline(taskDeadline[0], "[D]", "(" + taskDeadline[1]);
                taskList.add(previousDeadline);
                break;

            case "E" :
                String[] taskEvent = taskDescription.split("\\(");
                Task previousEvent = new Event(taskEvent[0], "[E]", "(" + taskEvent[1]);
                taskList.add(previousEvent);
                break;

            default :
                throw new IllegalStateException("Unexpected value: " + taskType);
            }
            System.out.println(taskDescription + " added to list");
        }
        try {
            new FileWriter(filePath, false).close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void writeToFile(String textToAdd) {
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

    public static void saveList(ArrayList<Task> taskList) {
        checkFileExist();
        for (Task task : taskList) {
            System.out.println(task);
            Save.writeToFile(task.printTaskToSave());
        }
    }
}
