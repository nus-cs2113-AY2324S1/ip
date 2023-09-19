package alan;

import alan.task.Deadline;
import alan.task.Event;
import alan.task.Task;
import alan.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import static alan.AlanExceptions.checkDeadlineInputFormat;
import static alan.AlanExceptions.checkEmptyDescription;
import static alan.AlanExceptions.checkEventInputFromFormat;
import static alan.AlanExceptions.checkEventInputToFormat;
import static alan.AlanExceptions.checkOutOfTaskListIndex;
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

    public static void printNumberOfTasksMessage(int numberOfTasks) {
        if (numberOfTasks == 1) {
            System.out.println("Dude! You've got a solid " + numberOfTasks + " task lined up on your list now!");
        } else {
            System.out.println("Dude! You've got a solid " + numberOfTasks + " tasks lined up on your list now!");
        }
    }

    public static void printTaskAddedMessage(ArrayList<Task> taskList) {
        int numberOfTasks = taskList.size();
        int lastTaskIndex = taskList.size() - 1;

        System.out.println("added: " + taskList.get(lastTaskIndex));
        printNumberOfTasksMessage(numberOfTasks);
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
        case "delete":
            //delete task from the list
            deleteCommandHandler(userInput, taskList);
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

        checkOutOfTaskListIndex(selectedTaskIndex, taskList);

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

    public static void deleteCommandHandler(String userInput, ArrayList<Task> taskList) throws AlanExceptions {
        String[] words = userInput.split(" ");
        int selectedTaskIndex = Integer.parseInt(words[1]) - 1;

        checkOutOfTaskListIndex(selectedTaskIndex, taskList);

        System.out.println("Got it, dude. This task is outta here:");
        System.out.println(taskList.get(selectedTaskIndex));

        taskList.remove(selectedTaskIndex);

        int numberOfTasks = taskList.size();
        printNumberOfTasksMessage(numberOfTasks);
    }


    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();

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

            } catch (AlanExceptions e) {
                System.out.println(e);
            } finally {
                printHorizontalLine();
            }
        } while (!userInput.equals("bye"));

    }
}
