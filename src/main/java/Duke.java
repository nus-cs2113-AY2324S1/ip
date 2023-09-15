import java.util.Arrays;
import java.util.Scanner;
import duke.Task;
import duke.Deadline;
import duke.Event;
import duke.ToDo;
import duke.DukeException;

public class Duke {

    private final Task[] taskList = new Task[100];

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

    public void listItems(int taskCount) {
        printLine();
        for (int i = 0; i < taskCount; i++) {
            int indexNo = i + 1;
            switch (this.taskList[i].taskType){
            case "todo":
                System.out.println("    " + indexNo + "." + this.taskList[i].getTaskTypeIcon() + this.taskList[i].getStatusIcon() + " " + this.taskList[i].description);
                break;
            case "deadline":
                System.out.println("    " + indexNo + "." + this.taskList[i].getTaskTypeIcon() + this.taskList[i].getStatusIcon() + " " + this.taskList[i].description
                        + "(by:"+this.taskList[i].deadline+")");
                break;
            case "event":
                System.out.println("    " + indexNo + "." + this.taskList[i].getTaskTypeIcon() + this.taskList[i].getStatusIcon() + " " + this.taskList[i].description
                        + " (from: " + this.taskList[i].from + " to: " + this.taskList[i].to+")");
                break;
            }
        }
        printLine();
    }

    public void markItem(int taskCount, boolean isMark) {
        printLine();
        if (isMark) {
            this.taskList[taskCount].isDone = true;
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("       " + this.taskList[taskCount].getStatusIcon() + " " + this.taskList[taskCount].description);
        } else {
            this.taskList[taskCount].isDone = false;
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("       " + this.taskList[taskCount].getStatusIcon() + " " + this.taskList[taskCount].description);
        }
        printLine();
    }

    public void addTaskCallback(int taskCount) {
        printLine();
        System.out.println("    Got it. I've added this task:");
        switch (this.taskList[taskCount].taskType) {
        case "todo":
            System.out.print("      " + this.taskList[taskCount].getTaskTypeIcon() + this.taskList[taskCount].getStatusIcon() + " " + this.taskList[taskCount].description);
            break;
        case "deadline":
            System.out.print("      " + this.taskList[taskCount].getTaskTypeIcon() + this.taskList[taskCount].getStatusIcon() + " " + this.taskList[taskCount].description
            + "(by:"+this.taskList[taskCount].deadline+")");
            break;
        case "event":
            System.out.print("      " + this.taskList[taskCount].getTaskTypeIcon() + this.taskList[taskCount].getStatusIcon() + " " + this.taskList[taskCount].description
            + " (from: " + this.taskList[taskCount].from + " to: " + this.taskList[taskCount].to+")");
            break;
        }

        System.out.println();
        System.out.println("    Now you have " + (taskCount + 1) + " tasks in the list.");
        printLine();
    }

    public void addTasks(String[] userInput, int taskCount){

        switch (userInput[0]) {
        case "todo":
            String todoDescription = String.join(" ", Arrays.copyOfRange(userInput, 1, userInput.length));
            Task todo = new ToDo(todoDescription);
            this.taskList[taskCount] = todo;
            addTaskCallback(taskCount);
            break;
        case "deadline":
            int byIndex = Arrays.asList(userInput).indexOf("/by");
            String deadlineDescription = String.join(" ", Arrays.copyOfRange(userInput, 1, byIndex)) + " ";
            String deadlineTime = String.join(" ", Arrays.copyOfRange(userInput, byIndex + 1, userInput.length));
            Deadline deadline = new Deadline(deadlineDescription, deadlineTime);
            this.taskList[taskCount] = deadline;
            addTaskCallback(taskCount);
            break;
        case "event":
            int fromIndex = Arrays.asList(userInput).indexOf("/from");
            int toIndex = Arrays.asList(userInput).indexOf("/to");
            String eventDescription = String.join(" ", Arrays.copyOfRange(userInput, 1, fromIndex)) + " ";
            String from = String.join(" ", Arrays.copyOfRange(userInput, fromIndex + 1, toIndex));
            String to = String.join(" ", Arrays.copyOfRange(userInput, toIndex + 1, userInput.length));
            Task event = new Event(eventDescription, from, to);
            this.taskList[taskCount] = event;
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