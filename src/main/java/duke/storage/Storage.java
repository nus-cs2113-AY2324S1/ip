package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String DATA_PATH = "data/tasks.txt";
    private final String DATA_DIRECTORY = "data";

    private TextUi ui;
    private ArrayList<Task> tasksListPlaceholder;

    public Storage() {
        tasksListPlaceholder = new ArrayList<>();
        ui = new TextUi();
    }
    public void addNewData(String dataString, int tasksCount) throws IOException {
        FileWriter writer = new FileWriter(DATA_PATH, true);

        if (tasksCount != 0) {
            writer.write(System.lineSeparator());
        }

        writer.write(dataString);
        writer.close();
    }

    private void modifyTaskData(int index, int currentIndex, Scanner scanner, String change,
                                       StringBuilder buffer) {
        if (currentIndex == index) {
            if (buffer.length() > 0) {
                buffer.append(System.lineSeparator());
            }

            String[] parsedTask = scanner.nextLine().split(" \\| ");
            parsedTask[1] = change;

            for (int i = 0; i < parsedTask.length; i++) {
                buffer.append(parsedTask[i]);

                if (i < parsedTask.length - 1) {
                    buffer.append(" | ");
                }
            }

        } else {
            if (buffer.length() > 0) {
                buffer.append(System.lineSeparator());
            }

            buffer.append(scanner.nextLine());
        }
    }

    public void updateTaskDatabase(int index, boolean taskStatus) throws IOException {
        Scanner scanner = new Scanner(new File(DATA_PATH));

        StringBuilder buffer = new StringBuilder();
        String change = taskStatus ? "true" : "false";
        int currentIndex = 0;

        while (scanner.hasNext()) {
            modifyTaskData(index, currentIndex, scanner, change, buffer);
            currentIndex++;
        }

        FileWriter writer = new FileWriter(DATA_PATH);
        writer.append(buffer.toString());
        writer.close();
    }

    private void createNewData(Scanner scan) {
        String[] parsedTask = scan.nextLine().split(" \\| ");

        String taskType = parsedTask[0];

        switch (taskType) {
        case "T":
            tasksListPlaceholder.add(new Todo(parsedTask[2] , parsedTask[1]));
            break;
        case "D":
            tasksListPlaceholder.add(new Deadline(parsedTask[2], parsedTask[1], parsedTask[3]));
            break;
        case "E":
            tasksListPlaceholder.add(new Event(parsedTask[2], parsedTask[1], parsedTask[3], parsedTask[4]));
            break;
        }
    }

    public ArrayList<Task> restoreSavedData() {
        try {
            File file = new File(DATA_PATH);
            Scanner scan = new Scanner(file);

            while (scan.hasNext()) {
                createNewData(scan);
            }
        } catch (FileNotFoundException exception) {
            handleFileNotFoundException();
        }

        return tasksListPlaceholder;
    }

    private void createDukeDirectory(File newDirectory) {
        if (!newDirectory.isDirectory() && newDirectory.mkdir()) {
            System.out.println("\tDirectory successfully created!");
        } else {
            System.out.println("\tDirectory found!");
        }
    }

    private void createDukeFile(File newDatabase) throws IOException {
        if (!newDatabase.isFile() && newDatabase.createNewFile()) {
            System.out.println("\tData text file successfully created!");
        } else {
            System.out.println("\tText file found!");
        }
    }

    private void excludeDeletedData(int index, int currentIndex, Scanner scanner, StringBuilder buffer) {
        if (currentIndex == index) {
            scanner.nextLine();
        } else {
            if (buffer.length() > 0) {
                buffer.append(System.lineSeparator());
            }

            buffer.append(scanner.nextLine());
        }
    }

    //Solution below adapted by https://www.tutorialspoint.com/how-to-overwrite-a-line-in-a-txt-file-using-java
    public void deleteTaskData(int index) throws IOException {
        Scanner scanner = new Scanner(new File(DATA_PATH));

        StringBuilder buffer = new StringBuilder();
        int currentIndex = 0;

        while (scanner.hasNext()) {
            excludeDeletedData(index, currentIndex, scanner, buffer);
            currentIndex++;
        }

        scanner.close();

        FileWriter writer = new FileWriter(DATA_PATH);
        writer.append(buffer.toString());
        writer.close();
    }

    public void handleFileNotFoundException() {
        ui.showLine();
        System.out.println("\tLooks like I can't find the file. Let me make it for you.");

        File newDirectory = new File(DATA_DIRECTORY);
        File newDatabase = new File(DATA_PATH);

        try {
            createDukeDirectory(newDirectory);
            createDukeFile(newDatabase);
        } catch (IOException exception){
            ui.handleIOException(exception);
        }

        System.out.println("\tPlease try to run the app again.");
        ui.showLine();
    }
}
