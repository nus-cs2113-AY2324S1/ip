import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    final private static String BOT_NAME = "Python";
    final private static int HORIZONTAL_LINE_LENGTH = 80;

    final private static String PYTHON_ASCII_ART =
                    "\t ____        _   _\n" +
                    "\t|  _ \\ _   _| |_| |__   ___  _ __\n" +
                    "\t| |_) | | | | __|  _ \\ / _ \\|  _ \\\n" +
                    "\t|  __/| |_| | |_| | | | (_) | | | |\n" +
                    "\t|_|    \\__, |\\__|_| |_|\\___/|_| |_|\n" +
                    "\t       |___/";
    final private static String PYTHON_EMOJI = "\uD83D\uDC0D";

    final static private List<Task> tasks = new ArrayList<>();
    final private static Scanner in = new Scanner(System.in);

    private static void printHorizontalLine() {
        char[] horizontalLine = new char[HORIZONTAL_LINE_LENGTH];
        Arrays.fill(horizontalLine, '—');
        System.out.println("\t" + new String(horizontalLine));
    }

    public static void main(String[] args) {
        System.out.println(PYTHON_ASCII_ART);
        printHorizontalLine();
        System.out.printf("\t%s: Hello! I am a short Java Bot %s!\n", PYTHON_EMOJI, BOT_NAME);
        System.out.printf("\t%s: What can I do for you?\n", PYTHON_EMOJI);
        printHorizontalLine();
        String inputLine;
        do {
            inputLine = in.nextLine();
            String inputCommand = inputLine.split("\\s+")[0];
            printHorizontalLine();
            switch (inputCommand) {
            case "bye":
                System.out.printf("\t%s: Bye. See you again when you run the program again!\n", PYTHON_EMOJI);
                break;
            case "list":
                System.out.printf("\t%s: You have %d tasks to do!\n", PYTHON_EMOJI, tasks.size());
                for (int taskNo = 1; taskNo <= tasks.size(); taskNo++) {
                    System.out.printf("\t\t\t%d. %s\n", taskNo, tasks.get(taskNo - 1));
                }
                break;
            case "mark": {
                int taskNo = Integer.parseInt(inputLine.split(" ")[1]);
                if (taskNo > tasks.size()) {
                    System.out.printf("\t%s: Are you from the future?\n", PYTHON_EMOJI);
                    break;
                }
                if (tasks.get(taskNo - 1).isDone()) {
                    System.out.printf("\t%s: Are you from the past?\n", PYTHON_EMOJI);
                    System.out.printf("\t\tTask: %s\n \t\t is already done!!!\n",
                            tasks.get(taskNo - 1));
                    break;
                }
                tasks.get(taskNo - 1).setDone(true);
                System.out.printf("\t%s: Good job completing the task!\n", PYTHON_EMOJI);
                System.out.printf("\t\t\t %s\n", tasks.get(taskNo - 1));
                break;
            }
            case "unmark": {
                int taskNo = Integer.parseInt(inputLine.split(" ")[1]);
                if (taskNo > tasks.size()) {
                    System.out.printf("\t%s: Are you from the future?\n", PYTHON_EMOJI);
                    break;
                }
                if (!tasks.get(taskNo - 1).isDone()) {
                    System.out.printf("\t%s: Alas! Only the completed tasks can be unmarked!\n", PYTHON_EMOJI);
                    System.out.printf("\t\tTask: %s\n \t\tis already sitting idle. Get started...!!!\n",
                            tasks.get(taskNo - 1));
                    break;
                }
                tasks.get(taskNo - 1).setDone(false);
                System.out.printf("\t%s: Its okay! To err is human! Unmarked!\n", PYTHON_EMOJI);
                System.out.printf("\t\t\t %s\n", tasks.get(taskNo - 1));
                break;
            }
            case "todo": {
                System.out.printf("\t%s: %s\n", PYTHON_EMOJI, "New Todo! You have added this todo:");
                final String todoDescription = inputLine.split(" ", 2)[1];
                Todo todo = new Todo(todoDescription);
                tasks.add(todo);
                System.out.printf("\t\t\t %s\n", todo);
                System.out.printf("\t\tYou have %d tasks in total!\n", tasks.size());
                break;
            }
            case "deadline": {
                System.out.printf("\t%s: %s\n", PYTHON_EMOJI, "New Deadline! You have added this deadline:");
                final String deadlineDetails = inputLine.split(" ", 2)[1];
                final String deadlineDescription = deadlineDetails.split(" /by ")[0];
                final String deadlineBy = deadlineDetails.split(" /by ")[1];
                Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
                tasks.add(deadline);
                System.out.printf("\t\t\t %s\n", deadline);
                System.out.printf("\t\tYou have %d tasks in total!\n", tasks.size());
                break;
            }
            case "event": {
                System.out.printf("\t%s: %s\n", PYTHON_EMOJI, "New Event! You have added this event:");
                final String eventDetails = inputLine.split(" ", 2)[1];
                final String eventDescription = eventDetails.split(" /from | /to ", 3)[0];
                final String eventFrom = eventDetails.split(" /from | /to ", 3)[1];
                final String eventTo = eventDetails.split(" /from | /to ", 3)[2];
                Event event = new Event(eventDescription, eventFrom, eventTo);
                tasks.add(event);
                System.out.printf("\t\t\t %s\n", event);
                System.out.printf("\t\tYou have %d tasks in total!\n", tasks.size());
                break;
            }
            default:
                System.out.printf("\t%s: %s\n", PYTHON_EMOJI, "I cannot understand the command!");
                break;
            }
            printHorizontalLine();
        } while (!inputLine.equals("bye"));
    }
}
