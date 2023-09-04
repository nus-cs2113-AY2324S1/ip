import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    final private static String BOT_NAME = "Python";
    final private static int HORIZONTAL_LINE_LENGTH = 80;

    final private static String pythonASCIIArt =
            "\t ____        _   _                 \n" +
            "\t|  _ \\ _   _| |_| |__   ___  _ __  \n" +
            "\t| |_) | | | | __|  _ \\ / _ \\|  _ \\ \n" +
            "\t|  __/| |_| | |_| | | | (_) | | | |\n" +
            "\t|_|    \\__, |\\__|_| |_|\\___/|_| |_|\n" +
            "\t       |___/                        ";

    final private static String pythonEmoji = "\uD83D\uDC0D";

    final static private List<Task> taskList = new ArrayList<>();
    final private static Scanner in = new Scanner(System.in);

    private static void printHorizontalLine() {
        char[] horizontalLine = new char[HORIZONTAL_LINE_LENGTH];
        Arrays.fill(horizontalLine, '—');
        System.out.println("\t" + new String(horizontalLine));
    }
    
    public static void main(String[] args) {
        System.out.println(pythonASCIIArt);
        printHorizontalLine();
        System.out.printf("\t%s: Hello! I am a short Java Bot %s!\n", pythonEmoji, BOT_NAME);
        System.out.printf("\t%s: What can I do for you?\n", pythonEmoji);
        printHorizontalLine();

        String inputLine;
        do {
            inputLine = in.nextLine();
            String inputCommand = inputLine.split(" ")[0];
            printHorizontalLine();
            switch (inputCommand) {
            case "bye":
                System.out.printf("\t%s: Bye. See you again when you run the program again!\n", pythonEmoji);
                break;
            case "list":
                System.out.printf("\t%s: You have %d tasks to do!\n", pythonEmoji, taskList.size());
                for(int taskNo = 1; taskNo <= taskList.size(); taskNo++) {
                    System.out.printf("\t\t\t%d. [%s] %s\n",
                            taskNo, taskList.get(taskNo - 1).getStatusIcon(),
                            taskList.get(taskNo - 1).getDescription());
                }
                break;
            case "mark": {
                int taskNo = Integer.parseInt(inputLine.split(" ")[1]);
                if (taskNo > taskList.size()) {
                    System.out.printf("\t%s: Are you from the future?\n", pythonEmoji);
                    break;
                }
                if (taskList.get(taskNo - 1).isDone()) {
                    System.out.printf("\t%s: Are you from the past?\n", pythonEmoji);
                    System.out.printf("\t\tTask: \"%s\" is already done!!!\n",
                            taskList.get(taskNo - 1).getDescription());
                    break;
                }
                taskList.get(taskNo - 1).setDone(true);
                System.out.printf("\t%s: Good job completing the task!\n", pythonEmoji);
                System.out.printf("\t\t\t[%s] %s\n",
                        taskList.get(taskNo - 1).getStatusIcon(),
                        taskList.get(taskNo - 1).getDescription());
                break;
            }
            case "unmark": {
                int taskNo = Integer.parseInt(inputLine.split(" ")[1]);
                if (taskNo > taskList.size()) {
                    System.out.printf("\t%s: Are you from the future?\n", pythonEmoji);
                    break;
                }
                if (!taskList.get(taskNo - 1).isDone()) {
                    System.out.printf("\t%s: Alas! Only the completed tasks can be unmarked!\n", pythonEmoji);
                    System.out.printf("\t\tTask: \"%s\" is already sitting idle. Get started...!!!\n",
                            taskList.get(taskNo - 1).getDescription());
                    break;
                }
                taskList.get(taskNo - 1).setDone(false);
                System.out.printf("\t%s: Its okay! To err is human! Unmarked!\n", pythonEmoji);
                System.out.printf("\t\t\t[%s] %s\n",
                        taskList.get(taskNo - 1).getStatusIcon(),
                        taskList.get(taskNo - 1).getDescription());
                break;
            }
            default:
                System.out.printf("\t%s: %s\n", pythonEmoji, inputLine);
                Task task = new Task(inputLine);
                taskList.add(task);
            }
            printHorizontalLine();
        } while(!inputLine.equals("bye"));
    }
}
