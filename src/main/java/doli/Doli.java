package doli;

import doli.exceptions.DoliExceptions;
import doli.files.FileHandler;
import doli.tasks.Deadline;
import doli.tasks.Event;
import doli.tasks.Task;
import doli.tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/** Very simple chatbot that helps setting up an agenda with various assignments and tasks
 *  Able to add, delete, view, clear, mark, unmark etc.
 *  Agenda is stored in working directory as Agenda.txt and retrieved when the bot is initialised
 */
public class Doli {
    private static final String LOGO = " ____       _\n" +
            "|  _  \\    | | [_]\n" +
            "| | | |____| |  _\n" +
            "| |_| | [] | | | |\n" +
            "|____/|____|__||_|\n";
    private static final String BULLET_POINT = "\n\t\u2022 ";
    private static final String INTRO = "I can help you create an agenda to manage your tasks.\n" +
            "Simply use one of the below listed commands to proceed:" +
            BULLET_POINT + "todo: adds a task with a DESCRIPTION" +
            BULLET_POINT + "deadline /: adds a task with DESCRIPTION and /DEADLINE" +
            BULLET_POINT + "event / /: adds a task with DESCRIPTION, /STARTDATE and /ENDDATE" +
            BULLET_POINT + "mark: marks the task with the provided INDEX as done" +
            BULLET_POINT + "unmark: resets the task with the provided INDEX as not done" +
            BULLET_POINT + "count: returns the total number of tasks in the agenda" +
            BULLET_POINT + "delete: deletes the task with the provided INDEX" +
            BULLET_POINT + "clear: deletes all tasks within the agenda" +
            BULLET_POINT + "list: lists all current entries in the agenda" +
            BULLET_POINT + "bye: exits the program\n";
    private static final String USERNAME = "User:";
    private static final String CHATBOT = "Doli:";
    private static final String WELCOME_MESSAGE = "Hello, my name is\n%s\n" + INTRO + "How can I help you?";
    private static final String EXIT = "It was a pleasure, bye. See you later!";
    private static final String HORIZONTAL_LINE = "________________________________";
    private static final String ADDED_TASK_SUCCESSFULLY = "Got it! I've added the following task to your agenda:";
    private static final String SUMMARIZING_CURRENT_AGENDA_ENTRIES = "Now you have a total of %d tasks in your agenda.";
    private static final String ENCOURAGE_TO_MARK = "Would you like to mark/unmark something else?";
    private static final String AGENDA_OVERVIEW = "Here is an overview of your agenda:";
    private static final String FAILED_TO_FIND_TASK = "I couldn't find the task in your agenda, try another one.";
    private static final String MARKED_SUCCESSFULLY = "I've successfully marked task %d as done.";
    private static final String UNMARKED_SUCCESSFULLY = "I've unmarked task %d. You better get it done soon!";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String COUNT_COMMAND = "count";
    private static final String DELETE_COMMAND = "delete";
    private static final String CLEAR_COMMAND = "clear";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String EXIT_COMMAND = "bye";
    private static final int NR_EVENT_ARGS = 3;
    private static final int NR_DEADLINE_ARGS = 2;
    private static final String UNRECOGNIZED_COMMAND = "I am so sorry, but I do not recognize this command. " +
            "Please try typing something else.";
    private static final Scanner IN = new Scanner(System.in);
    public static ArrayList<Task> tasks;

    public static void main(String[] args) throws DoliExceptions, IOException {

        initializeAgenda();
        welcomeUser();

        while (true) {
            String input = getInput();
            handleCommand(input);
            FileHandler.modifyFile(tasks);
        }
    }
    public static void initializeAgenda() throws IOException, DoliExceptions {
        tasks = new ArrayList<>();
        /** Check if a file named Agenda.txt already exists in the wd
         *  if yes, read its content and add it to the ArrayList tasks
         *  if not, create a new empty text file
         */
        FileHandler.initializeFile(tasks);
    }
    public static void welcomeUser() {
        printOutput(buildWelcomeMessage());
    }
    public static String buildWelcomeMessage() {
        return String.format(WELCOME_MESSAGE, LOGO);
    }
    private static String buildCurrentSummary() {
        return String.format(SUMMARIZING_CURRENT_AGENDA_ENTRIES, tasks.size());
    }
    public static String getInput() {
        System.out.println(USERNAME);
        String input = IN.nextLine();
        return input;
    }
    public static void printOutput(String... arguments) {
        System.out.println(CHATBOT);
        for (String arg: arguments) {
            System.out.println(arg);
        }
        System.out.println(HORIZONTAL_LINE);
    }
    public static void greetBye() {
        printOutput(EXIT);
        System.exit(0);
    }

    /** Extract both the command and if it exists also its input arguments
     *  for example "list" is a single word, whereas a "todo" also expects a description
     */
    private static String[] extractCommandInfo(String input) {
        String[] commandAndArgs = input.toLowerCase().split(" ", 2);
        if (commandAndArgs.length == 2) {
            return commandAndArgs;
        } else {
            return commandAndArgs = new String[]{commandAndArgs[0], ""};
        }
    }
    private static boolean invalidEvent(String[] args) {
        return args.length < NR_EVENT_ARGS;
    }
    private static boolean invalidDeadline(String[] args) {
        return args.length < NR_DEADLINE_ARGS;
    }
    private static ToDo initializeNewTodo(String args) throws DoliExceptions {
        if (args.trim().isEmpty()) {
            throw new DoliExceptions("The description of a todo cannot be blank!");
        }
        ToDo newTodo = new ToDo(args);
        return newTodo;
    }
    private static Deadline initializeNewDeadline(String args) throws DoliExceptions {
        final String[] descriptionAndTiming = args.trim().split("/");
        if (invalidDeadline(descriptionAndTiming)) {
            throw new DoliExceptions("A deadline needs both a description and a time reference!");
        }
        final String description = descriptionAndTiming[0];
        final String timing = descriptionAndTiming[1];
        Deadline newDeadline = new Deadline(description, timing);
        return newDeadline;
    }
    private static Event initializeNewEvent(String args) throws DoliExceptions {
        final String[] descriptionAndTiming = args.trim().split("/");
        if (invalidEvent(descriptionAndTiming)) {
            throw new DoliExceptions("An event needs to contain a description, a start and an end date");
        }
        final String description = descriptionAndTiming[0];
        final String startTime = descriptionAndTiming[1];
        final String endTime = descriptionAndTiming[2];
        Event newEvent = new Event(description, startTime, endTime);
        return newEvent;
    }
    private static String addTodo(String arguments) throws DoliExceptions {
        ToDo newTodo = initializeNewTodo(arguments);
        tasks.add(newTodo);
        final String TODO_DESCRIPTION = newTodo.toString();
        return indentText(TODO_DESCRIPTION);
    }
    private static String addDeadline(String arguments) throws DoliExceptions {
        Deadline newDeadline = initializeNewDeadline(arguments);
        tasks.add(newDeadline);
        final String DEADLINE_DESCRIPTION = newDeadline.toString();
        return indentText(DEADLINE_DESCRIPTION);
    }
    private static String addEvent(String arguments) throws DoliExceptions {
        Event newEvent = initializeNewEvent(arguments);
        tasks.add(newEvent);
        final String EVENT_DESCRIPTION = newEvent.toString();
        return indentText(EVENT_DESCRIPTION);
    }
    private static boolean verifyValidTaskNumber(String number) {
        int taskNumber = Integer.parseInt(number);
        return taskNumber <= tasks.size();
    }
    private static String markedTaskSuccessfully(int taskNumber) {
        return String.format(MARKED_SUCCESSFULLY, taskNumber);
    }
    private static String unmarkedTaskSuccessfully(int taskNumber) {
        return String.format(UNMARKED_SUCCESSFULLY, taskNumber);
    }
    private static String markTask(String arg) {
        int taskNumber = Integer.parseInt(arg);
        tasks.get(taskNumber - 1).markTaskAsDone();
        return markedTaskSuccessfully(taskNumber);
    }
    private static String unmarkTask(String arg) {
        int taskNumber = Integer.parseInt(arg);
        tasks.get(taskNumber - 1).markTaskAsNotDone();
        return unmarkedTaskSuccessfully(taskNumber);
    }
    private static String indentText(String text) {
        return "\t" + text;
    }
    private static String listTasks() {
        StringBuilder agenda = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            agenda.append(String.format("   %d. %s\n", i + 1, tasks.get(i).toString()));
        }
        return agenda.toString();
    }
    private static void deleteTask(String numberOfTaskToDelete) {
        try {
            removedTaskSuccessfully(Integer.parseInt(numberOfTaskToDelete) - 1);
            tasks.remove(Integer.parseInt(numberOfTaskToDelete) - 1);
        } catch(IndexOutOfBoundsException e) {
            System.out.println(String.format("Watch out, there is no task number %s in your agenda.\n" +
                    "Try a different task or add one", numberOfTaskToDelete));
        }
    }
    private static void removedTaskSuccessfully(int numberOfTask) {
        String outputMessage = "Sure, I've removed this task:\n\t" +
                tasks.get(numberOfTask).toString() +
                String.format("Now you have a total of %d tasks in your agenda", tasks.size() - 1);
        System.out.println(outputMessage);
    }
    private static String addMark(String arguments) {
        if (verifyValidTaskNumber(arguments)) {
            return markTask(arguments);
        } else {
            return FAILED_TO_FIND_TASK;
        }
    }
    private static String undoMark(String arguments) {
        if (verifyValidTaskNumber(arguments)) {
            return unmarkTask(arguments);
        } else {
            return FAILED_TO_FIND_TASK;
        }
    }
    private static void executeTodo(String task) {
        try {
            printOutput(ADDED_TASK_SUCCESSFULLY, addTodo(task), buildCurrentSummary());
        } catch(DoliExceptions e) {
            System.out.println("Could not add the todo task.");
        }
    }
    private static void executeDeadline(String task) {
        try {
            printOutput(ADDED_TASK_SUCCESSFULLY, addDeadline(task), buildCurrentSummary());
        } catch(DoliExceptions e) {
            System.out.println("Could not add the deadline.");
        }
    }
    private static void executeEvent(String task) {
        try {
            printOutput(ADDED_TASK_SUCCESSFULLY, addEvent(task), buildCurrentSummary());
        } catch(DoliExceptions e) {
            System.out.println("Could not add the event.");
        }
    }
    private static void deleteAll() {
        tasks = new ArrayList<>();
    }
    private static void printList(String list) throws IOException {
        printOutput(AGENDA_OVERVIEW, list);
    }
    private static void executeMark(String arguments) {
        printOutput(addMark(arguments), ENCOURAGE_TO_MARK);
    }
    private static void executeUnmark(String arguments) {
        printOutput(undoMark(arguments), ENCOURAGE_TO_MARK);
    }

    /** Most important method of the both, responsible for identifying the specific command
     * and carrying out the respective action. As default, the method does not know the command and
     * encourages the user to type a new one.
     * @param input
     * @throws DoliExceptions
     * @throws IOException
     */
    public static void handleCommand(String input) throws DoliExceptions, IOException {
        final String command = extractCommandInfo(input)[0];
        final String args = extractCommandInfo(input)[1];
        switch (command) {
        case TODO_COMMAND:
            executeTodo(args);
            break;
        case DEADLINE_COMMAND:
            executeDeadline(args);
            break;
        case EVENT_COMMAND:
            executeEvent(args);
            break;
        case LIST_COMMAND:
            printList(listTasks());
            break;
        case COUNT_COMMAND:
            printOutput(String.valueOf(tasks.size()));
            break;
        case DELETE_COMMAND:
            deleteTask(args);
            break;
        case CLEAR_COMMAND:
            deleteAll();
            break;
        case MARK_COMMAND:
            executeMark(args);
            break;
        case UNMARK_COMMAND:
            executeUnmark(args);
            break;
        case EXIT_COMMAND:
            greetBye();
            break;
        default:
            printOutput(UNRECOGNIZED_COMMAND);
            break;
        }
    }
}
