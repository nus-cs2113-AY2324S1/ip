package alan;

import alan.task.Deadline;
import alan.task.Event;
import alan.task.Task;
import alan.task.Todo;

import java.util.Scanner;

import static alan.AlanExceptions.checkDeadlineInputFormat;
import static alan.AlanExceptions.checkEmptyDescription;
import static alan.AlanExceptions.checkEventInputFromFormat;
import static alan.AlanExceptions.checkEventInputToFormat;
import static alan.AlanExceptions.checkOutOfTasksIndex;
import static alan.AlanExceptions.invalidInputCommand;

public class Alan {
    public static int currentTasksIndex = 1;

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

    public static void printTaskAddedMessage(Task[] tasks, int currentTasksIndex) {
        System.out.println("added: " + tasks[currentTasksIndex]);
        if (currentTasksIndex == 1) {
            System.out.println("Dude! You've got a solid " + currentTasksIndex + " task lined up on your list now!");
        } else {
            System.out.println("Dude! You've got a solid " + currentTasksIndex + " tasks lined up on your list now!");
        }
    }

    public static void processCommandHandler(String userInput, Task[] tasks) throws AlanExceptions {
        String[] userInputWords = userInput.split(" ");
        String command = userInputWords[0];

        switch (command) {
        case "bye":
            printExitMessage();
            break;
        case "list":
            //print the tasks in the lists
            listCommandHandler(tasks, currentTasksIndex);
            break;
        case "mark":
            //mark tasks as done
            markingCommandHandler(userInput, tasks, true);
            break;
        case "unmark":
            //unmark tasks as undone
            markingCommandHandler(userInput, tasks, false);
            break;
        case "todo":
            //add to-do task to the list
            checkEmptyDescription(userInput);
            todoCommandHandler(userInput, tasks, currentTasksIndex);
            currentTasksIndex++;
            break;
        case "deadline":
            //add deadline task to the list
            checkEmptyDescription(userInput);
            deadlineCommandHandler(userInput, tasks, currentTasksIndex);
            currentTasksIndex++;
            break;
        case "event":
            //add event task to the list
            checkEmptyDescription(userInput);
            eventCommandHandler(userInput, tasks, currentTasksIndex);
            currentTasksIndex++;
            break;
        default:
            invalidInputCommand();
        }
    }

    public static void listCommandHandler(Task[] tasks, int currentTasksIndex) {
        System.out.println("Dude, check out these tasks on your list:");

        for (int i = 1; i < currentTasksIndex; i++) {
            System.out.print((i) + ". ");
            System.out.println(tasks[i]);
        }
    }

    public static void markingCommandHandler(String userInput, Task[] tasks, boolean isMark) throws AlanExceptions {
        String[] words = userInput.split(" ");
        int selectedTaskIndex = Integer.parseInt(words[1]);

        checkOutOfTasksIndex(selectedTaskIndex, currentTasksIndex);

        if (isMark) {
            tasks[selectedTaskIndex].setDone(true);
            System.out.println("Alright bro! This task is officially checked off:");
        } else {
            tasks[selectedTaskIndex].setDone(false);
            System.out.println("Ok dude, I've marked this task as ain't done yet amigo:");
        }

        System.out.println(tasks[selectedTaskIndex]);
    }

    public static void todoCommandHandler(String userInput, Task[] tasks, int currentTasksIndex) {
        String description = userInput.replace("todo ", "");
        tasks[currentTasksIndex] = new Todo(description);

        printTaskAddedMessage(tasks, currentTasksIndex);
    }

    public static void deadlineCommandHandler(String userInput, Task[] tasks, int currentTasksIndex) throws AlanExceptions {
        String filteredUserInput = userInput.replace("deadline ", "");
        String[] words = filteredUserInput.split(" /by ");

        checkDeadlineInputFormat(words);

        String description = words[0];
        String by = words[1];

        tasks[currentTasksIndex] = new Deadline(description, by);

        printTaskAddedMessage(tasks, currentTasksIndex);
    }

    public static void eventCommandHandler(String userInput, Task[] tasks, int currentTasksIndex) throws AlanExceptions {
        String filteredUserInput = userInput.replace("event ", "");
        String[] splitDescriptionAndDate = filteredUserInput.split(" /from ");

        checkEventInputFromFormat(splitDescriptionAndDate);

        String[] splitFromAndTo = splitDescriptionAndDate[1].split(" /to ");

        checkEventInputToFormat(splitFromAndTo);

        String description = splitDescriptionAndDate[0];
        String from = splitFromAndTo[0];
        String to = splitFromAndTo[1];

        tasks[currentTasksIndex] = new Event(description, from, to);

        printTaskAddedMessage(tasks, currentTasksIndex);
    }


    public static void main(String[] args) {
        Task[] tasks = new Task[101];

        printGreetingMessage();

        String userInput = null;
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                //Read user input
                System.out.print("Input: ");
                userInput = scanner.nextLine();

                printHorizontalLine();

                processCommandHandler(userInput, tasks);

            } catch (AlanExceptions e) {
                System.out.println(e);
            } finally {
                printHorizontalLine();
            }
        } while (!userInput.equals("bye"));

    }
}
