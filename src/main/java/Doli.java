import java.util.Arrays;
import java.util.Scanner;
public class Doli {
    /** Prints a horizontal dashed line to separate fields */
    public static void printLine() {
        System.out.println("________________________________");
    }
    /** Prints the signature design of the chatbot */
    public static void printDesign() { //
        String design = " ____       _\n" +
                "|  _  \\    | | [_]\n" +
                "| | | |____| |  _\n" +
                "| |_| | [] | | | |\n" +
                "|____/|____|__||_|\n";
        System.out.printf("\nHello! My name is\n%s\nWhat can I do for you?\n", design);
    }
    /** If the input by the user is bye, the system will greet and finish the process */
    public static void greetBye() {
        System.out.println("\nIt was a pleasure, bye. See you later!");
    }

    public static void main(String[] args) {

        printLine();
        printDesign();
        printLine();

        int numberOfItems = 0;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String standardResponse = "Got it! I've added the following task to your agenda:\n   ";

        while (!line.trim().equalsIgnoreCase("bye")) {
            String command = line.split(" ")[0].toLowerCase();
            String summaryResponse = String.format("\n" +
                    "Now you have a total of %d tasks in your agenda.", numberOfItems + 1);

            /** Implemented commands so far: todo, event, deadline, list, mark, unmark */
            switch (command) {
            case "todo":
                tasks[numberOfItems] = new ToDo(line);
                String todoText = tasks[numberOfItems].toString();
                System.out.println(standardResponse + todoText + summaryResponse);
                numberOfItems++;
                break;
            case "event":
                String startTime = line.substring(line.indexOf("/from") + "/from ".length(), line.indexOf("/to"));
                String endTime = line.substring(line.indexOf("/to") + "/to ".length());
                tasks[numberOfItems] = new Event(line, startTime, endTime);
                String eventText = tasks[numberOfItems].toString();
                System.out.println(standardResponse + eventText + summaryResponse);
                numberOfItems++;
                break;
            case "deadline":
                String deadline = line.substring(line.indexOf("/by") + "/by ".length());
                tasks[numberOfItems] = new Deadline(line, deadline);
                String deadlineText = tasks[numberOfItems].toString();
                System.out.println(standardResponse + deadlineText + summaryResponse);
                numberOfItems++;
                break;
            case "list":
                Task[] array = Arrays.copyOf(tasks, numberOfItems);
                System.out.println("Here is an overview of your agenda:");
                for (int i = 0; i < array.length; i++) {
                    String overview = String.format("   %d. %s", i + 1, array[i].toString());
                    System.out.println(overview);
                }
                System.out.println("Would you like to mark/unmark something?");
                break;
            case "mark":
                int taskNumberToMark = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
                if (taskNumberToMark > numberOfItems) {
                    System.out.println("I could not find the task in your agenda, please try another one.");
                    break;
                }
                tasks[taskNumberToMark - 1].markTaskAsDone();
                String marked = String.format("I've successfully marked task %d as done.", taskNumberToMark);
                System.out.println(marked);
                break;
            case "unmark":
                int taskNumberToUnmark = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
                if (taskNumberToUnmark > numberOfItems) {
                    System.out.println("I could not find the task in your agenda, please try another one.");
                    break;
                }
                tasks[taskNumberToUnmark - 1].markTaskAsNotDone();
                String unmarked = String.format("I've successfully unmarked task %d.", taskNumberToUnmark);
                System.out.println(unmarked);
                break;
            default:
                String response = "I am so sorry, but I do not recognize this command. " +
                        "Please try typing something else.";
                System.out.println(response);
            }
            printLine();
            line = in.nextLine();
        }
        greetBye();
        printLine();
    }
}