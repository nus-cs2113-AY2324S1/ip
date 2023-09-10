import java.util.Scanner;

public class Duke {

    private static final String NAME = "MudMud";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String BY_KEYWORD = " /by ";
    private static final String FROM_KEYWORD = " /from ";
    private static final String TO_KEYWORD = " /to ";

    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    public static void addTodo(String input) {
        tasks[tasksCount] = new Todo(input);
        tasksCount++;
    }

    public static void addDeadline(String input) throws DukeTaskException {
        String[] parsedInput = input.split(BY_KEYWORD);

        if (parsedInput.length != 2) {
            throw new DukeTaskException();
        }

        tasks[tasksCount] = new Deadline(parsedInput[0].trim(), parsedInput[1].trim());
        tasksCount++;
    }

    public static void addEvent(String input) throws DukeTaskException {
        String[] parsedInput = input.split(FROM_KEYWORD);

        if (parsedInput.length != 2) {
            throw new DukeTaskException();
        }

        parsedInput = input.split(TO_KEYWORD);

        if (parsedInput.length != 2) {
            throw new DukeTaskException();
        }

        if (input.indexOf(FROM_KEYWORD) >= input.indexOf(TO_KEYWORD)) {
            throw new DukeTaskException();
        }

        parsedInput = input.split(FROM_KEYWORD + "|" + TO_KEYWORD);

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

    public static void printRecentTask(Task task) {
        System.out.println("\tI have added the following task into the list:");
        System.out.println("\t\t" + task);
        System.out.println("\tI took a peak at the list and you have " + tasksCount
                + (tasksCount == 1 ? " task" : " tasks") + " currently.");
    }

    public static void printTasks() {
        System.out.println("\tHere are your tasks you have inputted:");
        for (int i = 1; i <= tasksCount; i++) {
            System.out.println("\t" + i + "." + tasks[i - 1]);
        }
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
                System.out.println("\tGoodbye! I am going to sleep now.");
                System.out.println("\t" + HORIZONTAL_LINE);
                System.exit(0);
                break;
            default:
                throw new DukeCommandException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\tUmm, you tried to access a task that does not exist");
            System.out.println("\tPerhaps you should put a valid number based on the number of tasks " +
                    "you have currently. ");
        } catch (NumberFormatException e) {
            System.out.println("\tPlease use a valid for marking or unmarking a task.");
        } catch (DukeCommandException e) {
            System.out.println("\tSorry I don't quite understand what your command is.");
        } catch (DukeTaskException e) {
            System.out.println("\tSorry your format is invalid.");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = "";

        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\tOh hello! I'm " + NAME + ".");
        System.out.println("\tHow can I help you today?");
        System.out.println("\t" + HORIZONTAL_LINE);

        while (true) {
            input = in.nextLine().trim();
            System.out.println("\t" + HORIZONTAL_LINE);
            executeCommand(input);
            System.out.println("\t" + HORIZONTAL_LINE);
        }
    }
}