import java.util.Scanner;

public class Duke {

    private static final String NAME = "MudMud";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String BY_KEYWORD = " /by ";
    private static final String FROM_KEYWORD = " /from ";
    private static final String TO_KEYWORD = " /to ";
    private static final String DEADLINE_FORMAT = "deadline 'task' /by 'specified deadline'";
    private static final String EVENT_FORMAT = "event 'task' /from 'specified start date' /to 'specified end date'";

    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    public static String getInput(Scanner in) {
        return in.nextLine().trim();
    }

    public static void tellGreeting() {
        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\tOh hello! I'm " + NAME + ".");
        System.out.println("\tHow can I help you today?");
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    public static void tellGoodbye() {
        System.out.println("\tGoodbye! I am going to sleep now.");
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    public static void addTodo(String input) {
        tasks[tasksCount] = new Todo(input);
        tasksCount++;
    }

    public static boolean checkNumOfDeadlineKeyword(String[] parsedInput) {
        return parsedInput.length == 2;
    }

    public static void addDeadline(String input) throws DukeTaskException {
        String[] parsedInput = input.split(BY_KEYWORD);

        if (!checkNumOfDeadlineKeyword(parsedInput)) {
            throw new DukeTaskException();
        }

        tasks[tasksCount] = new Deadline(parsedInput[0].trim(), parsedInput[1].trim());
        tasksCount++;
    }

    public static boolean checkNumOfEventKeywords(String input) {
        int numOfFromKeyword = input.split(FROM_KEYWORD).length - 1;
        int numOfToKeyword = input.split(TO_KEYWORD).length - 1;

        return numOfFromKeyword == 1 && numOfToKeyword == 1;
    }

    public static boolean checkPosOfEventKeywords(String input) {
        return input.indexOf(FROM_KEYWORD) < input.indexOf(TO_KEYWORD);
    }

    public static void addEvent(String input) throws DukeTaskException {
        if (!checkNumOfEventKeywords(input) || !checkPosOfEventKeywords(input)) {
            throw new DukeTaskException();
        }

        String[] parsedInput = input.split(FROM_KEYWORD + "|" + TO_KEYWORD);

        tasks[tasksCount] = new Event(parsedInput[0].trim(), parsedInput[1].trim(), parsedInput[2].trim());
        tasksCount++;
    }

    public static void setMarkAsDone(String input) {
        int index = Integer.parseInt(input) - 1;
        tasks[index].markAsDone();

        System.out.println("\tYay! You have completed this task:");
        System.out.println("\t\t" + tasks[index]);
    }

    public static void setUnmarkAsDone(String input) {
        int index = Integer.parseInt(input) - 1;
        tasks[index].unmarkAsDone();

        System.out.println("\tOh no! It seems that you haven't finish this task:");
        System.out.println("\t\t" + tasks[index]);
    }

    public static void printNumOfTasks() {
        System.out.println("\tI took a peak at the list and you have " + tasksCount
                + (tasksCount == 1 ? " task" : " tasks") + " currently.");
    }

    public static void printRecentTask(Task task) {
        System.out.println("\tI have added the following task into the list:");
        System.out.println("\t\t" + task);
        printNumOfTasks();
    }

    public static void printTasks() {
        System.out.println("\tHere are your tasks you have inputted:");
        for (int i = 1; i <= tasksCount; i++) {
            System.out.println("\t" + i + "." + tasks[i - 1]);
        }
    }

    public static void handleNullPointerException() {
        System.out.println("\tUmm, you tried to access a task that does not exist.");
        System.out.println("\tPerhaps you should put a valid number based on the number of tasks " +
                "you have currently. ");
        printNumOfTasks();
    }

    public static void handleNumberFormatException(String input) {
        System.out.println("\tPlease use a valid number for marking or unmarking a task.");
        System.out.println("\tThe number you tried to input is: " + input);
    }

    public static void handleDukeCommandException(String command) {
        System.out.println("\tSorry I don't quite understand what your command is.");
        System.out.println("\tPlease a valid command.");
        System.out.println("\tThe command you tried to input is: " + command);
    }

    public static void handleDukeTaskException(String command, String input) {
        System.out.println("\tSorry your " + command + "'s format is invalid.");
        System.out.println("\tYour input should be in this format:");

        if (command.equals("deadline")) {
            System.out.println("\t\t" + DEADLINE_FORMAT);
        } else {
            System.out.println("\t\t" + EVENT_FORMAT);
        }

        System.out.println("\tYour input is: ");
        System.out.println("\t\t" + command + " " + input);
    }

    public static void executeCommand(String input) {
        String[] parsedInput = input.split(" ", 2);
        String command = parsedInput[0];
        input = parsedInput.length == 1 ? " " : parsedInput[1].trim();

        try {
            switch(command) {
            case "list":
                printTasks();
                break;
            case "mark":
                setMarkAsDone(input);
                break;
            case "unmark":
                setUnmarkAsDone(input);
                break;
            case "todo":
                addTodo(input);
                printRecentTask(tasks[tasksCount - 1]);
                break;
            case "deadline":
                addDeadline(input);
                printRecentTask(tasks[tasksCount - 1]);
                break;
            case "event":
                addEvent(input);
                printRecentTask(tasks[tasksCount - 1]);
                break;
            case "bye":
                tellGoodbye();
                System.exit(0);
            default:
                throw new DukeCommandException();
            }
        } catch (NullPointerException exception) {
            handleNullPointerException();
        } catch (NumberFormatException exception) {
            handleNumberFormatException(input);
        } catch (DukeCommandException exception) {
            handleDukeCommandException(command);
        } catch (DukeTaskException exception) {
            handleDukeTaskException(command, input);
        }
    }

    public static void main(String[] args) {
        String input = "";
        Scanner in = new Scanner(System.in);

        tellGreeting();
        while (true) {
            input = getInput(in);
            System.out.println("\t" + HORIZONTAL_LINE);
            executeCommand(input);
            System.out.println("\t" + HORIZONTAL_LINE);
        }
    }
}