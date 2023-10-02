package alan;

import alan.data.TaskList;
import alan.data.exception.AlanException;
import alan.data.task.Deadline;
import alan.data.task.Event;
import alan.data.task.Task;
import alan.data.task.TaskType;
import alan.parser.Parser;
import alan.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Alan {
    /* File storage */
    public static void saveFileHandler() throws Exception {
        String userWorkingDirectory = System.getProperty("user.dir");
        java.nio.file.Path tasksFilePath = java.nio.file.Paths.get(userWorkingDirectory, "data", "tasks.txt");
        java.nio.file.Path dataFolderPath = java.nio.file.Paths.get(userWorkingDirectory, "data");
        File textFile = new File(String.valueOf(tasksFilePath));
        File folder = new File(String.valueOf(dataFolderPath));

        //check if folder exists
        if (!Files.exists(dataFolderPath)) {
            folder.mkdir();
            ui.showFolderNotFoundMessage(userWorkingDirectory);
        }

        //check if file exists
        if (!Files.exists(tasksFilePath)) {
            textFile.createNewFile();
            ui.showFileNotFoundMessage(dataFolderPath);
        }

        //input arraylist data into text file
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            String taskDataRow = getStringOfTaskInformation(i);

            if (i == 0) {
                writeToFile(tasksFilePath.toString(), taskDataRow);
            } else {
                appendToFile(tasksFilePath.toString(), taskDataRow);
            }
        }
    }

    private static String getStringOfTaskInformation(int i) {
        Task task = tasks.getSelectedTask(i);
        String taskDataRow = task.getTaskType() + " | " + task.getStatusValue() + " | " + task.getDescription();

        if (task.getTaskType() == TaskType.D) {
            Deadline deadline = (Deadline) task;
            taskDataRow = taskDataRow + " | " + deadline.getBy();
        }

        if (task.getTaskType() == TaskType.E) {
            Event event = (Event) task;
            taskDataRow = taskDataRow + " | " + event.getFrom() + "-" +event.getTo();
        }

        taskDataRow = taskDataRow + "\n";
        return taskDataRow;
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    private static void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void readFileHandler() throws FileNotFoundException {
        String userWorkingDirectory = System.getProperty("user.dir");
        java.nio.file.Path tasksFilePath = java.nio.file.Paths.get(userWorkingDirectory, "data", "tasks.txt");
        File textFile = new File(String.valueOf(tasksFilePath));
        if (textFile.length() != 0) { //check if the file is empty
            //read the file and store into ArrayList
            Scanner s = new Scanner(textFile);
            ArrayList<String> list = new ArrayList<>();

            while (s.hasNext()){
                list.add(s.nextLine());
            }
            s.close();

            extractAndStoreDataInTaskList(list);

        }
    }

    private static void extractAndStoreDataInTaskList(ArrayList<String> list) {
        //todo: after this line should check if the data is in correct format
        for (String task : list) {
            String[] splitTaskString = task.split(" \\| ");
            String taskType = splitTaskString[0];
            String isDoneString = splitTaskString[1];
            String description = splitTaskString[2];
            boolean isDone = isDoneStringToBoolean(isDoneString);

            tasks.addTaskToTaskList(taskType, description, splitTaskString);

            int lastTaskIndex = tasks.getLastTaskIndex();
            tasks.getSelectedTask(lastTaskIndex).setDone(isDone);
        }
    }

    private static boolean isDoneStringToBoolean (String string) {
        // todo check if isDone is correct value, error handling
        return string.equals("1");
    }

    /** MORE OOP Increment **/
    private static Ui ui;
    private static TaskList tasks;
    private static Parser parser;

    public static void runAlan() {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser(tasks, ui);

        try {
            readFileHandler();
        } catch (Exception exception) {
            ui.showToUser(exception.getMessage());
        }

        ui.showWelcomeMessage();

        String userInput = null;

        do {
            try {
                userInput = ui.getUserCommand();
                parser.processCommandHandler(userInput);
            } catch (AlanException e) {
                ui.showToUser(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        } while (!userInput.equals("bye"));

        //Store TaskList in Text file tasks.txt
        try {
            saveFileHandler();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        Alan.runAlan();
    }
}
