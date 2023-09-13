import java.util.Scanner;

public class Alan {
    static final int FROM_KEYWORD_END_INDEX = 5;
    static final int TO_KEYWORD_END_INDEX = 3;
    public static int currentTasksIndex = 1;
    public static final String INVALID_INPUT_COMMAND_MESSAGE = "Oof, I have no idea what are you saying duuude";
    public static final String EMPTY_DESCRIPTION_MESSAGE = "Oof Dude, you can't leave the description empty, man";
    public static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Oof the deadline command isn't quite right you gotta fix the format, bro, remember it's: /by <date>";
    public static final String INVALID_EVENT_FROM_FORMAT_MESSAGE = "Oof duude, your /from formatting is whack";
    public static final String INVALID_EVENT_TO_FORMAT_MESSAGE = "Oof my man, you need to work on that /to formatting";
    public static void printGreetingMessage() {
        printHorizontalLine();
        String manDrawing = " @/\n" +
                            "/| \n" +
                            "/ \\";

        System.out.println(manDrawing);
        System.out.println("Hello! I'm Alan");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printHorizontalLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void printTaskAddedMessage(Task[] tasks, int currentTasksIndex) {
        System.out.println("added: " + tasks[currentTasksIndex]);
        System.out.println("Now you have " + currentTasksIndex + " in the list.");
    }

    public static void processCommandHandler(String userInput, Task[] tasks) throws AlanExceptions {
        String[] userInputWords = userInput.split(" ");
        String command = userInputWords[0];

        if (command.equals("bye")) {
            printExitMessage();
        } else if (command.equals("list")) {
            //print the tasks in the lists
            listCommandHandler(tasks, currentTasksIndex);
        } else if (command.equals("mark")) {
            //mark tasks as done
            markingCommandHandler(userInput, tasks, true);
        } else if (command.equals("unmark")) {
            //unmark tasks as undone
            markingCommandHandler(userInput, tasks, false);
        } else if (command.equals("todo")) {
            //add to-do task to the list
            checkEmptyDescription(userInput);
            todoCommandHandler(userInput, tasks, currentTasksIndex);
            currentTasksIndex++;
        } else if (command.equals("deadline")) {
            //add deadline task to the list
            checkEmptyDescription(userInput);
            deadlineCommandHandler(userInput, tasks, currentTasksIndex);
            currentTasksIndex++;
        } else if (command.equals("event")) {
            //add event task to the list
            checkEmptyDescription(userInput);
            eventCommandHandler(userInput, tasks, currentTasksIndex);
            currentTasksIndex++;
        } else {
            throw new AlanExceptions(INVALID_INPUT_COMMAND_MESSAGE);
        }
    }

    public static void listCommandHandler(Task[] tasks, int currentTasksIndex) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 1; i < currentTasksIndex; i++) {
            System.out.print((i) + ". ");
            System.out.println(tasks[i]);
        }
    }

    public static void markingCommandHandler(String userInput, Task[] tasks, boolean isMark) {
        String[] words = userInput.split(" ");
        int selectedTaskIndex = Integer.parseInt(words[1]);

        if (isMark) {
            tasks[selectedTaskIndex].setDone(true);
            System.out.println("Alright! I've marked this task as done:");
        } else {
            tasks[selectedTaskIndex].setDone(false);
            System.out.println("Ok, I've marked this task as not done yet:");
        }

        System.out.println(tasks[selectedTaskIndex]);
    }

    public static void todoCommandHandler(String userInput, Task[] tasks, int currentTasksIndex) {
        String description = userInput.replace("todo ", "");
        tasks[currentTasksIndex] = new Todo(description);

        printTaskAddedMessage(tasks, currentTasksIndex);
    }

    public static void deadlineCommandHandler(String userInput, Task[] tasks, int currentTasksIndex) throws AlanExceptions {
        String filteredUserInput = userInput.replace("deadline ", "");
        String[] words = filteredUserInput.split(" /by ");

        if (words.length == 1) {
            throw new AlanExceptions(INVALID_DEADLINE_FORMAT_MESSAGE);
        }

        String description = words[0];
        String by = words[1];

        tasks[currentTasksIndex] = new Deadline(description, by);

        printTaskAddedMessage(tasks, currentTasksIndex);
    }

    public static void eventCommandHandler(String userInput, Task[] tasks, int currentTasksIndex) throws AlanExceptions {
        String filteredUserInput = userInput.replace("event ", "");
        String[] splitDescriptionAndDate = filteredUserInput.split(" /from ");

        if (splitDescriptionAndDate.length == 1) {
            throw new AlanExceptions(INVALID_EVENT_FROM_FORMAT_MESSAGE);
        }

        String[] splitFromAndTo = splitDescriptionAndDate[1].split(" /to ");

        if (splitFromAndTo.length == 1) {
            throw new AlanExceptions(INVALID_EVENT_TO_FORMAT_MESSAGE);
        }

        String description = splitDescriptionAndDate[0];
        String from = splitFromAndTo[0];
        String to = splitFromAndTo[1];

        tasks[currentTasksIndex] = new Event(description, from, to);

        printTaskAddedMessage(tasks, currentTasksIndex);
    }

    public static void checkEmptyDescription(String userInput) throws AlanExceptions {
        String[] userInputWords = userInput.split(" ");

        if (userInputWords.length == 1) {
            throw new AlanExceptions(EMPTY_DESCRIPTION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[101];

        printGreetingMessage();

        String userInput = null;
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                //Read user input
                System.out.print("Input: ");
                userInput = scanner.nextLine();

                printHorizontalLine();

                processCommandHandler(userInput, tasks);

            } catch (AlanExceptions e) {
                System.out.println(e);
            } finally {
                printHorizontalLine();
            }
        } while (!userInput.equals("bye"));

    }
}
