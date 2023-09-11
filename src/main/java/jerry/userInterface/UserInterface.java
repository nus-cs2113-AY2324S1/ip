package jerry.userInterface;

import java.util.Scanner;

import jerry.task.Task;
import jerry.task.TaskList;

public class UserInterface {
    private static final String HORIZONTAL_LINE = "--------------------------------------------";
    private static final String LOGO = "                                                  \n" + "                                                     \n" + "         $$\\  $$$$$$\\   $$$$$$\\   $$$$$$\\  $$\\   $$\\ \n" + "         \\__|$$  __$$\\ $$  __$$\\ $$  __$$\\ $$ |  $$ |\n" + "         $$\\ $$$$$$$$ |$$ |  \\__|$$ |  \\__|$$ |  $$ |\n" + "         $$ |$$   ____|$$ |      $$ |      $$ |  $$ |\n" + "         $$ |\\$$$$$$$\\ $$ |      $$ |      \\$$$$$$$ |\n" + "         $$ | \\_______|\\__|      \\__|       \\____$$ |\n" + "   $$\\   $$ |                              $$\\   $$ |\n" + "   \\$$$$$$  |                              \\$$$$$$  |\n" + "    \\______/                                \\______/ \n" + "   \n";

    private static final String WELCOME_MESSAGE = "Hi I'm Jerry!\nWhat can I do for you ?";
    private static final String GOODBYE_MESSAGE = "Bye! Hope to see you again.";
	private static final String LIST_OF_TASKS_MESSAGE = "Here are the tasks in your list:";
    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command.";

    private static final Scanner in = new Scanner(System.in);

    public static String getUserInput() {
        String userInput = in.nextLine();
        return userInput;
    }

    private static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printLogo() {
        System.out.println(LOGO);
    }

    private static void printMessage(String message) {
        String[] messagesLines = message.split("\n", 0);
        for (String messageLine : messagesLines) {
            System.out.printf("\t%s\n", messageLine);
        }
    }

    public static void showWelcomeMessage() {
        printLogo();
        printHorizontalLine();
        printMessage(WELCOME_MESSAGE);
        printHorizontalLine();
    }

    public static void showGoodbyeMessage() {
        printMessage(GOODBYE_MESSAGE);
        printHorizontalLine();
    }

    public static void showListOfTasks(TaskList taskList) {
        printMessage(LIST_OF_TASKS_MESSAGE);
        printMessage(taskList.toString());
    }

    public static void showUnknownCommandMessage() {
        printMessage(UNKNOWN_COMMAND_MESSAGE);
    }

    public static void showTaskAddedConfirmation(Task task, TaskList taskList) {
        System.out.printf("\tGot it. I've added this task:\n\t\t %s\n\tNow you have %d tasks in the list.\n", task, taskList.size());
    }

}
