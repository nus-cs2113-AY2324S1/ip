package alan;

import alan.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import static alan.AlanExceptions.checkDeadlineInputFormat;
import static alan.AlanExceptions.checkEmptyDescription;
import static alan.AlanExceptions.checkEventInputFromFormat;
import static alan.AlanExceptions.checkEventInputToFormat;
import static alan.AlanExceptions.checkOutOfTasksIndex;
import static alan.AlanExceptions.invalidInputCommand;

public class Alan {
    public static void printGreetingMessage() {
        String manDrawing = " @/\n" +
                "/| \n" +
                "/ \\";
        String alanText = " ______     __         ______     __   __    \n" +
                "/\\  __ \\   /\\ \\       /\\  __ \\   /\\ \"-.\\ \\   \n" +
                "\\ \\  __ \\  \\ \\ \\____  \\ \\  __ \\  \\ \\ \\-.  \\  \n" +
                " \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_\\\\\"\\_\\ \n" +
                "  \\/_/\\/_/   \\/_____/   \\/_/\\/_/   \\/_/ \\/_/ ";

        String greetMessage = "Sup dude! I'm \n" + alanText + "\n" + manDrawing + "\n" + "What can I do for you, my man?";

        printHorizontalLine();
        System.out.println(greetMessage);
        printHorizontalLine();
    }

    public static void printExitMessage() {
        System.out.println("Later, dude! Can't wait to catch up again real soon!");
    }

    public static void printHorizontalLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void printTaskAddedMessage(ArrayList<Task> taskList) {
        int numberOfTasks = taskList.size();
        int lastTaskIndex = taskList.size() - 1;

        System.out.println("added: " + taskList.get(lastTaskIndex));

        if (numberOfTasks == 1) {
            System.out.println("Dude! You've got a solid " + numberOfTasks + " task lined up on your list now!");
        } else {
            System.out.println("Dude! You've got a solid " + numberOfTasks + " tasks lined up on your list now!");
        }
    }

    public static void processCommandHandler(String userInput, ArrayList<Task> taskList) throws AlanExceptions {
        String[] userInputWords = userInput.split(" ");
        String command = userInputWords[0];

        switch (command) {
        case "bye":
            printExitMessage();
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
        default:
            invalidInputCommand();
        }
    }

    public static void listCommandHandler(ArrayList<Task> taskList) {
        System.out.println("Dude, check out these tasks on your list:");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(taskList.get(i));
        }
    }

    public static void markingCommandHandler(String userInput, ArrayList<Task> taskList, boolean isMark) throws AlanExceptions {
        String[] words = userInput.split(" ");
        int selectedTaskIndex = Integer.parseInt(words[1]) - 1;

        checkOutOfTasksIndex(selectedTaskIndex, taskList);

        if (isMark) {
            taskList.get(selectedTaskIndex).setDone(true);
            System.out.println("Alright bro! This task is officially checked off:");
        } else {
            taskList.get(selectedTaskIndex).setDone(false);
            System.out.println("Ok dude, I've marked this task as ain't done yet amigo:");
        }

        System.out.println(taskList.get(selectedTaskIndex));
    }

    public static void todoCommandHandler(String userInput, ArrayList<Task> taskList) {
        String description = userInput.replace("todo ", "");
        taskList.add(new Todo(description));

        printTaskAddedMessage(taskList);
    }

    public static void deadlineCommandHandler(String userInput, ArrayList<Task> taskList) throws AlanExceptions {
        String filteredUserInput = userInput.replace("deadline ", "");
        String[] words = filteredUserInput.split(" /by ");

        checkDeadlineInputFormat(words);

        String description = words[0];
        String by = words[1];

        taskList.add(new Deadline(description, by));

        printTaskAddedMessage(taskList);
    }

    public static void eventCommandHandler(String userInput, ArrayList<Task> taskList) throws AlanExceptions {
        String filteredUserInput = userInput.replace("event ", "");
        String[] splitDescriptionAndDate = filteredUserInput.split(" /from ");

        checkEventInputFromFormat(splitDescriptionAndDate);

        String[] splitFromAndTo = splitDescriptionAndDate[1].split(" /to ");

        checkEventInputToFormat(splitFromAndTo);

        String description = splitDescriptionAndDate[0];
        String from = splitFromAndTo[0];
        String to = splitFromAndTo[1];

        taskList.add(new Event(description, from, to));

        printTaskAddedMessage(taskList);
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
            System.out.println("Data Folder was not found!\nIt's ok...new data folder has been created in" + userWorkingDirectory);
        }

        //check if file exists
        if (!Files.exists(tasksFilePath)) {
            textFile.createNewFile();
            System.out.println("tasks.txt was not found!\nIt's ok...new tasks.txt has been created in " + dataFolderPath);
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
            ArrayList<String> list = new ArrayList<String>();

            while (s.hasNext()){
                list.add(s.nextLine());
            }
            s.close();

            //extract data and store into taskList
            //todo: after this line should check if the data is in correct format
            for (String task : list) {
                String[] splitTaskString = task.split(" \\| ");
                String taskType = splitTaskString[0];
                String isDoneString = splitTaskString[1];
                String description = splitTaskString[2];

                boolean isDone = isDoneStringToBoolean(isDoneString);

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

                int lastTaskIndex = taskList.size() - 1;
                taskList.get(lastTaskIndex).setDone(isDone);
            }

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

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            readFileHandler(taskList);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        printGreetingMessage();

        String userInput = null;
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                //Read user input
                System.out.print("Input: ");
                userInput = scanner.nextLine();

                printHorizontalLine();

                processCommandHandler(userInput, taskList);

            } catch (AlanExceptions alanExceptions) {
                System.out.println(alanExceptions.getMessage());
            } finally {
                printHorizontalLine();
            }
        } while (!userInput.equals("bye"));

        //Store TaskList in Text file tasks.txt
        try {
            saveFileHandler(taskList);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }
}
