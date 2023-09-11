import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.Scanner;
public class Doli {
    private static final String LOGO = " ____       _\n" +
            "|  _  \\    | | [_]\n" +
            "| | | |____| |  _\n" +
            "| |_| | [] | | | |\n" +
            "|____/|____|__||_|\n";
    /**
     * If the input by the user is bye, the system will greet and finish the process
     */
    private static final String USERNAME = "User:";
    private static final String CHATBOT = "Doli:";
    private static final String WELCOME_MESSAGE = "Hello, my name is\n%s\nHow can I help you?";
    private static final String EXIT = "It was a pleasure, bye. See you later!";
    private static final String HORIZONTAL_LINE = "________________________________";
    private static final String ADDED_TASK_SUCCESSFULLY = "Got it! I've added the following task to your agenda:";
    private static final String SUMMARIZING_CURRENT_AGENDA_ENTRIES = "Now you have a total of %d tasks in your agenda.";
    private static final String ENCOURAGE_TO_MARK = "Would you like to mark/unmark something else?";
    private static final String AGENDA_OVERVIEW = "Here is an overview of your agenda:";
    private static final String FAILED_TO_FIND_TASK = "I couldn't find the task in your agenda, try another one.";
    private static final String MARKED_SUCCESSFULLY = "I've successfully marked task %d as done.";
    private static final String UNMARKED_SUCCESSFULLY = "I've unmarked task %d. You better get it done soon!";
    private static final String FORGOT_TIMING = "Please indicated the deadline or event timings";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String EXIT_COMMAND = "bye";
    private static final String UNRECOGNIZED_COMMAND = "I am so sorry, but I do not recognize this command. " +
            "Please try typing something else.";
    private static final Scanner IN = new Scanner(System.in);
    private static int numberOfItems;
    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static Task[] tasks;

    public static void initializeAgenda() {
        tasks = new Task[MAX_NUMBER_OF_TASKS];
        numberOfItems = 0;
    }

    public static void welcomeUser() {
        printOutput(buildWelcomeMessage());
    }

    public static String buildWelcomeMessage() {
        return String.format(WELCOME_MESSAGE, LOGO);
    }

    public static String getInput() {
        System.out.println(USERNAME);
        String input = IN.nextLine();
        return input;
    }

    private static String[] extractCommandInfo(String input) {
        String[] commandAndArgs = input.toLowerCase().split(" ", 2);
        if (commandAndArgs.length == 2) {
            return commandAndArgs;
        } else {
            return commandAndArgs = new String[]{commandAndArgs[0], ""};
        }
    }
    private static boolean verifyValidEvent(String[] args) {
        return args.length == 3;
    }
    private static boolean verifyValidDeadline(String[] args) {
        return args.length == 2;
    }
    private static ToDo initializeNewTodo(String args) {
        ToDo newTodo = new ToDo(args);
        return newTodo;
    }

    private static Deadline initializeNewDeadline(String args) {
        final String[] descriptionAndTiming = args.trim().split("/");
        final String description = descriptionAndTiming[0];
        final String timing = descriptionAndTiming[1];
        Deadline newDeadline = new Deadline(description, timing);
        return newDeadline;
    }

    private static Event initializeNewEvent(String args) {
        final String[] descriptionAndTiming = args.trim().split("/");
        final String description = descriptionAndTiming[0];
        final String startTime = descriptionAndTiming[1];
        final String endTime = descriptionAndTiming[2];
        Event newEvent = new Event(description, startTime, endTime);
        return newEvent;
    }

    private static String initializeNewList(Task[] currentAgenda) {
        String agenda = "";
        for (int i = 0; i < currentAgenda.length; i++) {
            agenda += String.format("   %d. %s\n", i + 1, currentAgenda[i].toString());
        }
        return agenda;
    }

    private static boolean verifyValidTaskNumber(String number) {
        int taskNumber = Integer.parseInt(number);
        return taskNumber <= numberOfItems;
    }

    private static String markedTaskSuccessfully(int taskNumber) {
        return String.format(MARKED_SUCCESSFULLY, taskNumber);
    }

    private static String unmarkedTaskSuccessfully(int taskNumber) {
        return String.format(UNMARKED_SUCCESSFULLY, taskNumber);
    }

    private static String markTask(String arg) {
        int taskNumber = Integer.parseInt(arg);
        tasks[taskNumber - 1].markTaskAsDone();
        return markedTaskSuccessfully(taskNumber);
    }

    private static String unmarkTask(String arg) {
        int taskNumber = Integer.parseInt(arg);
        tasks[taskNumber - 1].markTaskAsNotDone();
        return unmarkedTaskSuccessfully(taskNumber);
    }
    private static String indentText(String text) {
        return "\t" + text;
    }
    private static String addTodoTask(String arguments) {
        ToDo newTodo = initializeNewTodo(arguments);
        tasks[numberOfItems] = newTodo;
        final String TODO_DESCRIPTION = tasks[numberOfItems].toString();
        numberOfItems++;
        return indentText(TODO_DESCRIPTION);
    }

    private static String addDeadlineTask(String arguments) {
        Deadline newDeadline = initializeNewDeadline(arguments);
        tasks[numberOfItems] = newDeadline;
        final String DEADLINE_DESCRIPTION = tasks[numberOfItems].toString();
        numberOfItems++;
        return indentText(DEADLINE_DESCRIPTION);
    }

    private static String addEventTask(String arguments) {
        Event newEvent = initializeNewEvent(arguments);
        tasks[numberOfItems] = newEvent;
        final String EVENT_DESCRIPTION = tasks[numberOfItems].toString();
        numberOfItems++;
        return indentText(EVENT_DESCRIPTION);
    }

    private static String executeTaskListing() {
        Task[] currentAgenda = Arrays.copyOf(tasks, numberOfItems);
        String newList = initializeNewList(currentAgenda);
        return newList;
    }
    private static String buildCurrentSummary() {
        return String.format(SUMMARIZING_CURRENT_AGENDA_ENTRIES, numberOfItems);
    }

    private static String executeTaskMarking(String arguments) {
        if (verifyValidTaskNumber(arguments)) {
            return markTask(arguments);
        }
        return FAILED_TO_FIND_TASK;
    }

    private static String executeTaskUnmarking(String arguments) {
        if (verifyValidTaskNumber(arguments)) {
            return unmarkTask(arguments);
        }
        return FAILED_TO_FIND_TASK;
    }

    private static void successfullyAddedTask(String task) {
        printOutput(ADDED_TASK_SUCCESSFULLY, task, buildCurrentSummary());
    }

    private static void successfullyList(String list) {
        printOutput(AGENDA_OVERVIEW, list);
    }

    private static void successfullyMark(String marked) {
        printOutput(marked, ENCOURAGE_TO_MARK);
    }

    private static void successfullyUnmark(String unmarked) {
        printOutput(unmarked, ENCOURAGE_TO_MARK);
    }

    public static void handleCommand(String input) {
        final String command = extractCommandInfo(input)[0];
        final String args = extractCommandInfo(input)[1];
        switch (command) {
        case TODO_COMMAND:
            successfullyAddedTask(addTodoTask(args));
            break;
        case DEADLINE_COMMAND:
            successfullyAddedTask(addDeadlineTask(args));
            break;
        case EVENT_COMMAND:
            successfullyAddedTask(addEventTask(args));
            break;
        case LIST_COMMAND:
            successfullyList(executeTaskListing());
            break;
        case MARK_COMMAND:
            successfullyMark(executeTaskMarking(args));
            break;
        case UNMARK_COMMAND:
            successfullyUnmark(executeTaskUnmarking(args));
            break;
        case EXIT_COMMAND:
            greetBye();
            break;
        default:
            printOutput(UNRECOGNIZED_COMMAND);
            break;
        }
    }

    public static void printOutput(String... arguments) {
        System.out.println(CHATBOT);
        for (String input : arguments) {
            System.out.println(input);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void greetBye() {
        printOutput(EXIT);
        System.exit(0);
    }

    public static void main(String[] args) {

        initializeAgenda();
        welcomeUser();

        while (true) {
            String input = getInput();
            handleCommand(input);
        }
    }
}
