import java.util.Arrays;
import java.util.Scanner;
import duke.Task;
import duke.Deadline;
import duke.Event;
import duke.ToDo;
import duke.DukeException;
import java.util.ArrayList;

public class Duke {

    private static final ArrayList<duke.Task> taskList = new ArrayList<>();

    public void printGreeting() {
        String logo = "______       _     _\n"
                + "| ___ \\     | |   | |\n"
                + "| |_/ / ___ | |__ | |__  _   _\n"
                + "| ___ \\/ _ \\| '_ \\| '_ \\| | | |\n"
                + "| |_/ | (_) | |_) | |_) | |_| |\n"
                + "\\____/ \\___/|_.__/|_.__/ \\__, |\n"
                + "                          __/ |\n"
                + "                         |___/";
        System.out.println("    Hello from\n" + logo);
        printLine();
        System.out.println("    Hello! I'm Bobby");
        System.out.println("    What can I do for you?");
        printLine();
    }

    public void exitChatbot() {
        printLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printLine();
    }

    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void printIndentTask(){
        System.out.print("      ");
    }

    public void listItems() {
        printLine();
        if (!taskList.isEmpty()) {
            for (Task task : taskList) {
                System.out.print("    " + (taskList.indexOf(task) + 1) + ".");
                task.printTask();
            }
        } else {
            System.out.println("    List is empty!");
        }
        printLine();
    }

    public void markItem(int taskCount, boolean isMark) {
        printLine();
        if (isMark) {
            taskList.get(taskCount).isDone = true;
            System.out.println("    Nice! I've marked this task as done:");
        } else {
            taskList.get(taskCount).isDone = false;
            System.out.println("    OK, I've marked this task as not done yet:");
        }
        System.out.print("       ");
        taskList.get(taskCount).printTask();
        printLine();
    }

    public void addTaskCallback(int taskCount) {
        printLine();
        System.out.println("    Got it. I've added this task:");
        printIndentTask();
        taskList.get(taskCount).printTask();
        System.out.println("    Now you have " + (taskCount + 1) + " tasks in the list.");
        printLine();
    }

    public void addTasks(String[] userInput, int taskCount){

        switch (userInput[0]) {
        case "todo":
            String todoDescription = String.join(" ", Arrays.copyOfRange(userInput, 1, userInput.length));
            Task todo = new ToDo(todoDescription);
            taskList.add(todo);
            addTaskCallback(taskCount);
            break;
        case "deadline":
            int byIndex = Arrays.asList(userInput).indexOf("/by");
            String deadlineDescription = String.join(" ", Arrays.copyOfRange(userInput, 1, byIndex)) + " ";
            String deadlineTime = String.join(" ", Arrays.copyOfRange(userInput, byIndex + 1, userInput.length));
            Deadline deadline = new Deadline(deadlineDescription, deadlineTime);
            taskList.add(deadline);
            addTaskCallback(taskCount);
            break;
        case "event":
            int fromIndex = Arrays.asList(userInput).indexOf("/from");
            int toIndex = Arrays.asList(userInput).indexOf("/to");
            String eventDescription = String.join(" ", Arrays.copyOfRange(userInput, 1, fromIndex)) + " ";
            String from = String.join(" ", Arrays.copyOfRange(userInput, fromIndex + 1, toIndex));
            String to = String.join(" ", Arrays.copyOfRange(userInput, toIndex + 1, userInput.length));
            Task event = new Event(eventDescription, from, to);
            taskList.add(event);
            addTaskCallback(taskCount);
            break;
        default:
        }

    }

    public void getInput(){
        Scanner scanner = new Scanner(System.in);
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();
            String[] userInput = input.trim().split("\\s+");

            DukeException exceptionHandler = new DukeException(userInput);
            exceptionHandler.checkInput();

            if (!exceptionHandler.exception) {
                switch (userInput[0]) {
                case "mark":
                    markItem(Integer.parseInt(userInput[1]) - 1, true);
                    break;
                case "unmark":
                    markItem(Integer.parseInt(userInput[1]) - 1, false);
                    break;
                case "bye":
                    exitChatbot();
                    System.exit(0);
                    break;
                case "list":
                    listItems(taskCount);
                    break;
                default:
                    addTasks(userInput, taskCount);
                    taskCount++;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.printGreeting();
        duke.getInput();
    }
}