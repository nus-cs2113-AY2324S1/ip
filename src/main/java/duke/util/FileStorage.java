package main.java.duke.util;

import main.java.duke.util.task.Deadline;
import main.java.duke.util.task.Event;
import main.java.duke.util.task.Task;
import main.java.duke.util.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileStorage {
    public static void fillFileFromList(TaskList taskList) {
        // writing to file
        try {
            File dataFile = new File("./duke.txt");
            // attempt file creation
            dataFile.createNewFile();

            // add all tasks to file
            FileWriter fileWriter = new FileWriter(dataFile);
            for(Task task : taskList.getTasks()) {
                fileWriter.write(task.toStringFile() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fillListFromFile(TaskList taskList) {
        // IO file handling
        try {
            // file object with expected file path
            File dataFile = new File("./duke.txt");
            // attempt file creation, if file already exist, read from file
            if (!dataFile.createNewFile()) {
                Scanner fileScanner = new Scanner(dataFile);
                while (fileScanner.hasNext()) {
                    String line = fileScanner.nextLine();
                    String[] taskData = line.split("\\|");
                    switch (taskData[0]) {
                    case "T":
                        taskList.addTodo(taskData[2]);
                        break;
                    case "E":
                        taskList.addEvent(taskData[2], taskData[3], taskData[4]);
                        break;
                    case "D":
                        taskList.addDeadline(taskData[2], taskData[3]);
                        break;
                    }
                    if (Integer.parseInt(taskData[1]) == 1) {
                        taskList.markTask(taskList.size() - 1);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
