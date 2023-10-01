package alan;

import alan.data.TaskList;
import alan.data.exception.AlanException;
import alan.data.task.Deadline;
import alan.data.task.Event;
import alan.data.task.Task;
import alan.data.task.TaskType;
import alan.data.task.Todo;
import alan.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import static alan.data.exception.AlanException.checkDeadlineInputFormat;
import static alan.data.exception.AlanException.checkEmptyDescription;
import static alan.data.exception.AlanException.checkEventInputFromFormat;
import static alan.data.exception.AlanException.checkEventInputToFormat;
import static alan.data.exception.AlanException.checkOutOfTaskListIndex;
import static alan.data.exception.AlanException.invalidInputCommand;

public class Alan {
    public static void processCommandHandler(String userInput) throws AlanException {
        String[] userInputWords = userInput.split(" ");
        String command = userInputWords[0];

        switch (command) {
        case "bye":
            ui.showExitMessage();
            break;
        case "list":
            //print the tasks in the lists
            listCommandHandler();
            break;
        case "mark":
            //mark tasks as done
            markingCommandHandler(userInput, true);
            break;
        case "unmark":
            //unmark tasks as undone
            markingCommandHandler(userInput, false);
            break;
        case "todo":
            //add to-do task to the list
            checkEmptyDescription(userInput);
            todoCommandHandler(userInput);
            break;
        case "deadline":
            //add deadline task to the list
            checkEmptyDescription(userInput);
            deadlineCommandHandler(userInput);
            break;
        case "event":
            //add event task to the list
            checkEmptyDescription(userInput);
            eventCommandHandler(userInput);
            break;
        case "delete":
            //delete task from the list
            deleteCommandHandler(userInput);
            break;
        default:
            invalidInputCommand();
        }
    }

    public static void listCommandHandler() {
        ui.showListMessage(tasks.getTaskList());
    }

    public static void markingCommandHandler(String userInput, boolean isMark) throws AlanException {
        String[] words = userInput.split(" ");
        int selectedTaskIndex = Integer.parseInt(words[1]) - 1;

        checkOutOfTaskListIndex(selectedTaskIndex, tasks.getTaskList());
        Task targetTask = tasks.getSelectedTask(selectedTaskIndex);

        if (isMark) {
            tasks.markTask(selectedTaskIndex, true);
            ui.showMarkTaskMessage(targetTask);
        } else {
            tasks.markTask(selectedTaskIndex, false);
            ui.showUnmarkTaskMessage(targetTask);
        }
    }

    public static void todoCommandHandler(String userInput) {
        String description = userInput.replace("todo ", "");
        tasks.addToDo(description);

        ui.showTaskAddedMessage(tasks.getTaskList());
    }

    public static void deadlineCommandHandler(String userInput) throws AlanException {
        String filteredUserInput = userInput.replace("deadline ", "");
        String[] words = filteredUserInput.split(" /by ");

        checkDeadlineInputFormat(words);

        String description = words[0];
        String by = words[1];

        tasks.addDeadline(description, by);

        ui.showTaskAddedMessage(tasks.getTaskList());
    }

    public static void eventCommandHandler(String userInput) throws AlanException {
        String filteredUserInput = userInput.replace("event ", "");
        String[] splitDescriptionAndDate = filteredUserInput.split(" /from ");

        checkEventInputFromFormat(splitDescriptionAndDate);

        String[] splitFromAndTo = splitDescriptionAndDate[1].split(" /to ");

        checkEventInputToFormat(splitFromAndTo);

        String description = splitDescriptionAndDate[0];
        String from = splitFromAndTo[0];
        String to = splitFromAndTo[1];

        tasks.addEvent(description, from, to);

        ui.showTaskAddedMessage(tasks.getTaskList());
    }

    public static void deleteCommandHandler(String userInput) throws AlanException {
        String[] words = userInput.split(" ");
        int selectedTaskIndex = Integer.parseInt(words[1]) - 1;
        checkOutOfTaskListIndex(selectedTaskIndex, tasks.getTaskList());

        Task targetTask = tasks.getSelectedTask(selectedTaskIndex);
        ui.showDeleteTaskMessage(targetTask);
        tasks.removeTask(selectedTaskIndex);

        int numberOfTasks = tasks.getTaskListSize();
        ui.showNumberOfTasksMessage(numberOfTasks);
    }
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

    public static void runAlan() {
        ui = new Ui();
        tasks = new TaskList();

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
                processCommandHandler(userInput);
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
