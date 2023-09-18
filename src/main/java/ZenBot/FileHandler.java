package zenbot;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import java.io.IOException;
import exceptions.TaskEmptyDescriptionException;

import tasks.Tasklist;
import tasks.Task;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;

public class FileHandler {
    
    private static final String FILE_PATH = "data/zenbot.txt";

    public static void writeToFile(Tasklist tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (int i = 1; i <= tasks.getTaskListSize(); i++) {
            if ( tasks.getTask(i) instanceof Todo) {
                fileWriter.write("T | " + tasks.getTask(i).getIsDone() + " | "
                    + tasks.getTask(i).getDescription() + System.lineSeparator());
            } else if ( tasks.getTask(i) instanceof Deadline) {
                fileWriter.write("D | " + tasks.getTask(i).getIsDone() + " | " 
                    + tasks.getTask(i).getDescription() + " | " 
                    + ((Deadline) tasks.getTask(i)).getDeadline() + System.lineSeparator());
            } else if ( tasks.getTask(i) instanceof Event) {
                fileWriter.write("E | " + tasks.getTask(i).getIsDone() + " | " + tasks.getTask(i).getDescription() + " | "
                    + ((Event) tasks.getTask(i)).getStartTime() + " | "
                    + ((Event) tasks.getTask(i)).getEndTime() + System.lineSeparator());
            }
        }
        fileWriter.close();
    }

    public static void readFromFile(Tasklist tasks) throws TaskEmptyDescriptionException, IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] lineArray = line.split(" | ");

            Task task = null;
            switch (lineArray[0]) {
            case "T":
                task = new Todo (lineArray[4]);
                break;
            case "D":
                task = new Deadline (lineArray[4], lineArray[6]);
                break;
            case "E":
                task = new Event (lineArray[4], lineArray[6], lineArray[8]);
                break;
            }
            if (lineArray[2].equals("true")) {
                task.setIsDone(true);
            }
            tasks.addTask(task);
        }
    }

    public static String getFilePath() {
        return FILE_PATH;
    }
}
