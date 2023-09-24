import exception.*;
import task.*;
import static UI.Printer.*;
import static commands.TaskCommands.*;
import java.util.Scanner;
import java.util.ArrayList;
import static commands.DataCommands.*;
import static data.Parser.*;
import static data.SimonFilePath.simontxtFilePath;

public class Simon {
    public static void main(String[] args) {
        //Initialise variables
        String userInput = "";

        String userDirectory = System.getProperty("user.dir");
        System.out.println("User's home directory: " + userDirectory);

        if(!isFileExist(simontxtFilePath)) {
            System.out.println("\t" + line);
            System.out.println("\tsimon.txt does not exist, creating simon.txt in 'data' folder");
            String dataFolderDirectory = userDirectory + "/" + "data";
            createDirectory(dataFolderDirectory);
            createFileInDirectory(dataFolderDirectory, "simon.txt");
            System.out.println("\t" + line);
        }
        ArrayList<Task> tasks = readToList(simontxtFilePath);

        //Print out greeting when user starts the program.
        printGreeting();

        //Take in user input
        Scanner in = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = in.nextLine();
            processUserInput(userInput, tasks);
        }
    }

    public static void processUserInput(String userInput, ArrayList<Task> tasks) {
        String[] splitInputs = userInput.split(" ", 2);
        switch (splitInputs[0]) {

        //If user types "list ..."
        case "list":
            printList(tasks);
            break;

        //If user types "mark 'n'", where n is a number referring to the task number
        case "mark":
            markTask(splitInputs[1], tasks);
            break;

        //If user types "unmark 'n'", where n is a number referring to the task number
        case "unmark":
            unmarkTask(splitInputs[1], tasks);
            break;

        //If user types "delete 'n'", where n is an integer referring to the task number
        case "delete":
            deleteTask(splitInputs[1], tasks);
            break;

        //If user types "todo ..."
        case "todo":
            try {
                addTodo(splitInputs[1], tasks);
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
                addEvent(splitInputs[1], tasks);
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
                addDeadline(splitInputs[1], tasks);
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