import exception.*;
import task.*;
import static UI.Printer.*;
import static commands.TaskCommands.*;
import java.util.Scanner;
import java.util.ArrayList;
import static data.DataMethods.*;
import static data.Parser.readToList;
import static data.SimonFilePath.simontxtFilePath;

public class Simon {
    public static void main(String[] args) {

        String userInput = "";

        String userDirectory = System.getProperty("user.dir");
        System.out.println("User's directory: " + userDirectory);

        if(!isFileExist(simontxtFilePath)) {
            System.out.println("\t" + line);
            System.out.println("\tsimon.txt does not exist, creating simon.txt in 'data' folder");
            createSimonTxt(userDirectory);
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

        case "list":
            printList(tasks);
            break;

        case "mark":
            markTask(splitInputs[1], tasks);
            break;

        case "unmark":
            unmarkTask(splitInputs[1], tasks);
            break;

        case "delete":
            deleteTask(splitInputs[1], tasks);
            break;

        case "todo":
            try {
                addTodo(splitInputs[1], tasks);
            } catch (IndexOutOfBoundsException | SimonException e) { //Empty description
                System.out.println("\t" + line);
                System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.println("\t" + line);
            }
            break;

        case "event":
            try {
                addEvent(splitInputs[1], tasks);
            } catch (IndexOutOfBoundsException | SimonException e) { //Empty description
                System.out.println("\t" + line);
                System.out.println("\t☹ OOPS!!! The description of an event cannot be empty.");
                System.out.println("\t" + line);
            }
            break;

        case "deadline":
            try {
                addDeadline(splitInputs[1], tasks);
            } catch (IndexOutOfBoundsException | SimonException e) { //Empty description
                System.out.println("\t" + line);
                System.out.println("\t☹ OOPS!!! The description of a deadline cannot be empty.");
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