package alan;

import alan.data.exception.AlanException;
import alan.data.task.Deadline;
import alan.data.task.Event;
import alan.data.task.Task;
import alan.data.task.TaskType;
import alan.data.task.Todo;
import alan.storage.Storage;
import alan.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import static alan.common.Messages.*;
import static alan.data.exception.AlanException.checkDeadlineInputFormat;
import static alan.data.exception.AlanException.checkEmptyDescription;
import static alan.data.exception.AlanException.checkEventInputFromFormat;
import static alan.data.exception.AlanException.checkEventInputToFormat;
import static alan.data.exception.AlanException.checkOutOfTaskListIndex;
import static alan.data.exception.AlanException.invalidInputCommand;

public class Alan {
    public static void processCommandHandler(String userInput, ArrayList<Task> taskList) throws AlanException {
        String[] userInputWords = userInput.split(" ");
        String command = userInputWords[0];

        switch (command) {
        case "bye":
            ui.showExitMessage();
            break;
        case "list":
            //print the tasks in the lists
            listCommandHandler(taskList);
            break;
        case "mark":
            //mark tasks as done
            markingCommandHandler(userInput, taskList, true);
            break;
        case "unmark":
            //unmark tasks as undone
            markingCommandHandler(userInput, taskList, false);
            break;
        case "todo":
            //add to-do task to the list
            checkEmptyDescription(userInput);
            todoCommandHandler(userInput, taskList);
            break;
        case "deadline":
            //add deadline task to the list
            checkEmptyDescription(userInput);
            deadlineCommandHandler(userInput, taskList);
            break;
        case "event":
            //add event task to the list
            checkEmptyDescription(userInput);
            eventCommandHandler(userInput, taskList);
            break;
        case "delete":
            //delete task from the list
            deleteCommandHandler(userInput, taskList);
            break;
        default:
            invalidInputCommand();
        }
    }

    public static void listCommandHandler(ArrayList<Task> taskList) {
        ui.showListMessage(taskList);
    }

    public static void markingCommandHandler(String userInput, ArrayList<Task> taskList, boolean isMark) throws AlanException {
        String[] words = userInput.split(" ");
        int selectedTaskIndex = Integer.parseInt(words[1]) - 1;

        checkOutOfTaskListIndex(selectedTaskIndex, taskList);
        Task targetTask = taskList.get(selectedTaskIndex);

        if (isMark) {
            taskList.get(selectedTaskIndex).setDone(true);
            ui.showMarkTaskMessage(targetTask);
        } else {
            taskList.get(selectedTaskIndex).setDone(false);
            ui.showUnmarkTaskMessage(targetTask);
        }
    }

    public static void todoCommandHandler(String userInput, ArrayList<Task> taskList) {
        String description = userInput.replace("todo ", "");
        taskList.add(new Todo(description));

        ui.showTaskAddedMessage(taskList);
    }

    public static void deadlineCommandHandler(String userInput, ArrayList<Task> taskList) throws AlanException {
        String filteredUserInput = userInput.replace("deadline ", "");
        String[] words = filteredUserInput.split(" /by ");

        checkDeadlineInputFormat(words);

        String description = words[0];
        String by = words[1];

        taskList.add(new Deadline(description, by));

        ui.showTaskAddedMessage(taskList);
    }

    public static void eventCommandHandler(String userInput, ArrayList<Task> taskList) throws AlanException {
        String filteredUserInput = userInput.replace("event ", "");
        String[] splitDescriptionAndDate = filteredUserInput.split(" /from ");

        checkEventInputFromFormat(splitDescriptionAndDate);

        String[] splitFromAndTo = splitDescriptionAndDate[1].split(" /to ");

        checkEventInputToFormat(splitFromAndTo);

        String description = splitDescriptionAndDate[0];
        String from = splitFromAndTo[0];
        String to = splitFromAndTo[1];

        taskList.add(new Event(description, from, to));

        ui.showTaskAddedMessage(taskList);
    }

    public static void deleteCommandHandler(String userInput, ArrayList<Task> taskList) throws AlanException {
        String[] words = userInput.split(" ");
        int selectedTaskIndex = Integer.parseInt(words[1]) - 1;
        checkOutOfTaskListIndex(selectedTaskIndex, taskList);

        Task targetTask = taskList.get(selectedTaskIndex);
        ui.showDeleteTaskMessage(targetTask);
        taskList.remove(selectedTaskIndex);

        int numberOfTasks = taskList.size();
        ui.showNumberOfTasksMessage(numberOfTasks);
    }
    public static void saveFileHandler(ArrayList<Task> taskList) throws Exception {
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
        for (int i = 0; i < taskList.size(); i++) {
            String taskDataRow = getStringOfTaskInformation(taskList, i);

            if (i == 0) {
                writeToFile(tasksFilePath.toString(), taskDataRow);
            } else {
                appendToFile(tasksFilePath.toString(), taskDataRow);
            }
        }
    }

    private static String getStringOfTaskInformation(ArrayList<Task> taskList, int i) {
        Task task = taskList.get(i);
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

    private static void readFileHandler(ArrayList<Task> taskList) throws FileNotFoundException {
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

            extractAndStoreDataInTaskList(taskList, list);

        }
    }

    private static void extractAndStoreDataInTaskList(ArrayList<Task> taskList, ArrayList<String> list) {
        //todo: after this line should check if the data is in correct format
        for (String task : list) {
            String[] splitTaskString = task.split(" \\| ");
            String taskType = splitTaskString[0];
            String isDoneString = splitTaskString[1];
            String description = splitTaskString[2];
            boolean isDone = isDoneStringToBoolean(isDoneString);

            addTaskToTaskList(taskList, taskType, description, splitTaskString);

            int lastTaskIndex = taskList.size() - 1;
            taskList.get(lastTaskIndex).setDone(isDone);
        }
    }

    private static void addTaskToTaskList(ArrayList<Task> taskList, String taskType, String description, String[] splitTaskString) {
        switch (taskType) {
        case "T":
            taskList.add(new Todo(description));
            break;
        case "D":
            String by = splitTaskString[3];
            taskList.add(new Deadline(description, by));
            break;
        case "E":
            String date = splitTaskString[3];
            String[] splitDate = date.split("-");
            String from = splitDate[0];
            String to = splitDate[1];

            taskList.add(new Event(description, from, to));
            break;
        default:
            //todo handle invalid task type
            break;
        }
    }

    private static boolean isDoneStringToBoolean (String string) {
        boolean isDone = false;

        if (string.equals("1")) {
            isDone = true;
        }

        if (string.equals("0")) {
            isDone = false;
        }

        //todo check if isDone is correct value, error handling

        return isDone;
    }

    /** MORE OOP Increment **/
    private static Ui ui;

    public static void runAlan() {
        ui = new Ui();
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            readFileHandler(taskList);
        } catch (Exception exception) {
            ui.showToUser(exception.getMessage());
        }

        ui.showWelcomeMessage();

        String userInput = null;

        do {
            try {
                userInput = ui.getUserCommand();
                processCommandHandler(userInput, taskList);
            } catch (AlanException e) {
                ui.showToUser(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        } while (!userInput.equals("bye"));

        //Store TaskList in Text file tasks.txt
        try {
            saveFileHandler(taskList);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        Alan.runAlan();
    }
}
