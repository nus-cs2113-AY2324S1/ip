package Duke;

import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.ToDo;

import Duke.Exception.NoDateTimeSpecifiedException;
import Duke.Exception.NoTaskSpecifiedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private final static ArrayList<Task> records = new ArrayList<>();


    public static void generateResponse(String input) {

        String[] commandDetails = input.split(" ", 2);
        String instructionString = commandDetails[0].trim();


        switch (instructionString) {
        case ("list"):
            executeListCommand();
            break;
        case ("unmark"):
            executeUnmarkCommand(commandDetails[1]);
            break;
        case ("mark"):
            executeMarkCommand(commandDetails[1]);
            break;
        case ("todo"):
            createNewTask("todo", commandDetails[1]);
            break;
        case ("deadline"):
            createNewTask("deadline", commandDetails[1]);
            break;
        case ("event"):
            createNewTask("event", commandDetails[1]);
            break;
        case ("delete"):
            deleteTask(commandDetails[1]);
            break;
        default:
            System.out.println("Invalid Command");
            break;
        }
    }

    private static void executeMarkCommand(String removedInstructionString) {
        int taskNum;
        try {
            taskNum = Integer.parseInt(removedInstructionString);
            records.get(taskNum - 1).setDone();
            executeListCommand();
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer");

        } catch (NullPointerException | IndexOutOfBoundsException e) {
            if (records.isEmpty()) {
                System.out.println("Please create a task to continue");
            } else if (records.size() == 1) {
                System.out.println("There are " + records.size() + " task.");
                System.out.println("Please enter 'mark 1' to check the first task as completed");
            } else {
                System.out.println("There are " + records.size() + " tasks.");
                System.out.println("Please enter a valid number from 1 to " + records.size() + "(inclusive).");
            }
        }
    }

    private static void executeUnmarkCommand(String removedInstructionString) {
        int taskNum;
        try {
            taskNum = Integer.parseInt(removedInstructionString);
            records.get(taskNum - 1).setUndone();
            executeListCommand();
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            if (records.isEmpty()) {
                System.out.println("Please create a task to continue");
            } else if (records.size() == 1) {
                System.out.println("There are " + records.size() + " task.");
                System.out.println("Please enter 'mark 1' to check the first task as completed");
            } else {
                System.out.println("There are " + records.size() + " tasks.");
                System.out.println("Please enter a valid number from 1 to " + records.size() + "(inclusive).");
            }
        }
    }

    private static void createNewTask(String instructionString, String removedInstructionString) {
        Task task = null;
        try {
            switch (instructionString) {
            case "todo":
                try {
                    task = createToDo(removedInstructionString);
                } catch (NoTaskSpecifiedException e) {
                    System.out.println("Please indicate the task for todo.");
                }
                break;
            case "deadline":
                try {
                    task = createDeadline(removedInstructionString);
                } catch (NoTaskSpecifiedException e) {
                    System.out.println("Please indicate the task for deadline.");
                } catch (NoDateTimeSpecifiedException e) {
                    System.out.println("Please indicate the end date for this deadline");
                }
                break;
            case "event":
                try {
                    task = createEvent(removedInstructionString);
                } catch (NoTaskSpecifiedException e) {
                    System.out.println("Please indicate the task for event.");
                } catch (NoDateTimeSpecifiedException e) {
                    System.out.println("Please indicate the start date and end date for this event.");
                }
                break;
            default:
                System.out.println("Command is invalid!");
                break;
            }
            if (task != null) {
                addTaskToList(task);
                printTaskAdded(task);
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please ensure that " + instructionString + " has the correct number of parameters.");
        }
    }

    private static void printTaskAdded(Task task) {
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("\tNow you have " + records.size() + " tasks in the list.");
        printLine();
    }

    private static void addTaskToList(Task task) {
        records.add(task);
    }

    private static Task createEvent(String removedInstructionString) throws NoTaskSpecifiedException, NoDateTimeSpecifiedException {
        String taskDescription;
        String fromDate;
        String toDate;
        String startDateIndicator = "/from";
        String endDateIndicator = "/to";
        String eventSplitNotation = startDateIndicator + "|" + endDateIndicator;
        String[] eventContents = removedInstructionString.split(eventSplitNotation);
        try {
            taskDescription = eventContents[0].trim();
            fromDate = eventContents[1].trim();
            toDate = eventContents[2].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoDateTimeSpecifiedException();
        }
        if (taskDescription.isEmpty()) {
            throw new NoTaskSpecifiedException();
        }
        if (fromDate.isEmpty() | toDate.isEmpty()) {
            throw new NoDateTimeSpecifiedException();
        }
        return new Event(taskDescription, fromDate, toDate);
    }

    private static Task createDeadline(String removedInstructionString) throws NoTaskSpecifiedException, NoDateTimeSpecifiedException {
        //TODO need to catch lack of by here.
        String taskDescription;
        String byDate;
        try {
            String dateIndicator = "/by";
            String[] deadlineContents = removedInstructionString.split(dateIndicator, 2);
            taskDescription = deadlineContents[0].trim();
            byDate = deadlineContents[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoDateTimeSpecifiedException();
        }
        if (taskDescription.isEmpty()) {
            throw new NoTaskSpecifiedException();
        }
        if (byDate.isEmpty()) {
            throw new NoDateTimeSpecifiedException();
        }

        return new Deadline(taskDescription, byDate);
    }

    private static Task createToDo(String taskDescription) throws NoTaskSpecifiedException {
        taskDescription = taskDescription.trim();
        if (taskDescription.isEmpty()) {
            throw new NoTaskSpecifiedException();
        }
        return new ToDo(taskDescription);
    }

    private static void executeListCommand() {
        printLine();
        for (Task task: records) {
            System.out.println(task);
        }
        printLine();
    }

    public static void printMessage(String message) {
        printLine();
        System.out.println("\t" + message);
        printLine();
    }

    private static void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    private static void deleteTask(String indexString) {
        try {
            int index = Integer.parseInt(indexString);
            Task task = records.get(index - 1);
            records.remove(index - 1);
            printLine();
            System.out.println("\tNoted. I've removed this task:");
            System.out.println(task);
            System.out.println("\tNow you have " + records.size() + " tasks in the list.");
            printLine();
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There are only " + records.size() + " tasks.");
        }
    }

    private static void interactWithUser() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.trim().equals("bye")) {
            try {
                generateResponse(line);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please ensure correct number of parameters are given.");
            }
            line = in.nextLine();
        }
    }

    private static void loadTaskList() {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        Scanner s;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNext()) {
            String storedMessage = s.nextLine();
            String[] messageFragments = storedMessage.split("\\|");
            Task task = null;
            switch (messageFragments[0].trim()) {
            case ("T"):
                try {
                    task = createToDo(messageFragments[2]);
                } catch (NoTaskSpecifiedException e) {
                    System.out.println("Failed loading Todo. Todo will be deleted.");
                }
                break;
            case ("D"):
                try {
                    task = createDeadline(messageFragments[2]);
                } catch (NoTaskSpecifiedException | NoDateTimeSpecifiedException e) {
                    System.out.println("Failed loading Deadline. Deadline will be deleted.");
                }
                break;
            case ("E"):
                try {
                    task = createEvent(messageFragments[2]);
                } catch (NoTaskSpecifiedException | NoDateTimeSpecifiedException e) {
                    System.out.println("Failed loading Event. Event will be deleted.");
                }
                break;
            default:
                continue;
            }
            if(task != null){
                addTaskToList(task);
            }
            if (messageFragments[1].trim().equals("1")) {
                //mark the previous task as done.
                records[recordsNum - 1].setDone();
            }
        }

    }

    private static void saveTaskList() throws IOException {
        String taskSaveFormat;
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < recordsNum; i++) {
            Task task = records[i];
            taskSaveFormat = task.convertToSaveFormat();
            fw.write(taskSaveFormat + "\n");
        }
        fw.close();
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String startMessage = "____________________________________________________________\n"
                + "Hello! I'm Chatty\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";


        System.out.println("Hello from\n" + logo);
        System.out.println(startMessage);
    }

    private static void printByeMessage() {
        String END_MESSAGE = "Bye. Hope to see you again soon!";
        printMessage(END_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            printWelcomeMessage();
            loadTaskList();
            interactWithUser();
            saveTaskList();
            printByeMessage();
        } catch (IOException e) {
            System.out.println("Please ensure that " + filePath + " exists.");
        }
    }
}

