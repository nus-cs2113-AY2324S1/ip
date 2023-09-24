package linguobot.file;

import linguobot.task.Deadline;
import linguobot.task.Event;
import linguobot.task.Task;
import linguobot.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;


public class TaskFile{
    private File tasks;

    public TaskFile (String filename) {
        tasks = new File(filename);
    }

    public void createFile() {
        try {
            if (tasks.exists()) {
                System.out.println("file exists");
                return;
            }
            if (!tasks.getParentFile().exists()) {
                tasks.getParentFile().mkdirs();
            }
            tasks.createNewFile();
        } catch (IOException e) {
            System.out.println("Cannot create file; reason: " + e.getMessage());
        }
    }

    private ArrayList readFile() throws IOException {
        if (!tasks.exists()) {
            createFile();
        }
        if (tasks.length() == 0) {
            System.out.println("You do not have any prior tasks.");
        }
        else {
            System.out.println("Your tasks are as follows:");
            Scanner s = new Scanner(tasks); // Create a Scanner directly from the file
            while (s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
            s.close(); // Close the Scanner when done
        }
        ArrayList<String> dataItems = (ArrayList) Files.readAllLines(tasks.toPath(), Charset.defaultCharset());

        return dataItems;
    }

//    to load data from file into the taskList
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList = null;
        try {
            ArrayList<String> dataItems = readFile();
            taskList = parseTaskFromString(dataItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public static ArrayList<Task> parseTaskFromString(ArrayList<String> taskFile) {
        ArrayList<Task> tasks = new ArrayList<>();

        for (String taskString : taskFile) {
            String[] parts = taskString.split("\\s*\\|\\s*");
            if (parts.length < 2) {
                continue;
            }

            String taskType = parts[0];
            Task task = null;

            switch (taskType) {
            case "T":
                if (parts.length >= 3) {
                    task = new Todo(parts[2]);
                }
                break;
            case "D":
                if (parts.length >= 4) {
                    task = new Deadline(parts[2], parts[3]);
                }
                break;
            case "E":
                if (parts.length >= 4) {
                    if (parts[3].contains("to")) {
                        String[] eventParts = parts[3].split("to");
                        task = new Event(parts[2], eventParts[0], eventParts[1]);
                    }
                }
                break;
            default:
                // Invalid task type, return null
                break;
            }

            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public static void saveTaskListToFile(ArrayList<Task> taskList) {
        try{
            File dataDirectory = new File("./data");
            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }

            File taskListFile = new File(dataDirectory, "tasks.txt");
            if (!taskListFile.exists()) {
                taskListFile.createNewFile();
            }
            FileWriter taskFile = new FileWriter(taskListFile);
            for (Task task : taskList) {
                String taskString = task.toFileString();
                taskFile.write(taskString);
                taskFile.write(System.lineSeparator());
            }
            taskFile.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


}
