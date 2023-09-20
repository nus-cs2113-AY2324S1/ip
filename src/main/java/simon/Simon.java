package simon;

import simon.exception.*;
import simon.task.*;
import static simon.constant.TaskConstants.*;
import static simon.UI.Printer.*;
import static simon.command.Commands.*;
import java.util.Scanner;


public class Simon {
    public static void main(String[] args) {
        //Initialise variables
        String userInput = "";
        Task[] tasks = new Task[MAX_TASKS];

        //Print out greeting when user starts the program.
        printGreeting();

        //Take in user input
        Scanner in = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = in.nextLine();
            processUserInput(userInput, tasks);
        }
    }

    public static void processUserInput(String userInput, Task[] tasks) {
        String[] splitInput = userInput.split(" ", 2);
        switch (splitInput[0]) {

        //If user types "list ..."
        case "list":
            printList(tasks);
            break;

        //If user types "mark 'n'", where n is a number referring to the task number
        case "mark":
            markTask(splitInput[1], tasks);
            break;

        //If user types "unmark 'n'", where n is a number referring to the task number
        case "unmark":
            unmarkTask(splitInput[1], tasks);
            break;

        //If user types "todo ..."
        case "todo":
            try {
                addTodo(splitInput[1], tasks);
            } catch (IndexOutOfBoundsException e) { //Empty description
                System.out.println("\t" + line);
                System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.println("\t" + line);
            } catch (SimonException e) {
                System.out.println("\t" + line);
                System.out.println("\t☹ OOPS!!! You have reached the maximum number of tasks.");
                System.out.println("\t" + line);
            }
            break;

        //If user types "event ... /from ... /to ..."
        case "event":
            try {
                addEvent(splitInput[1], tasks);
            } catch (IndexOutOfBoundsException e) { //Empty description
                System.out.println("\t" + line);
                System.out.println("\t☹ OOPS!!! The description of an event cannot be empty.");
                System.out.println("\t" + line);
            } catch (SimonException e) {
                System.out.println("\t" + line);
                System.out.println("\t☹ OOPS!!! You have reached the maximum number of tasks.");
                System.out.println("\t" + line);
            }
            break;

        //If user types "deadline ... /by ..."
        case "deadline":
            try {
                addDeadline(splitInput[1], tasks);
            } catch (IndexOutOfBoundsException e) { //Empty description
                System.out.println("\t" + line);
                System.out.println("\t☹ OOPS!!! The description of a deadline cannot be empty.");
                System.out.println("\t" + line);
            } catch (SimonException e) {
                System.out.println("\t" + line);
                System.out.println("\t☹ OOPS!!! You have reached the maximum number of tasks.");
                System.out.println("\t" + line);
            }
            break;

        case "bye":
            printFarewell();
            break;

        default:
            //If unable to understand user input
            printUnknownInputMessage();
        }
    }
}