package magpie.input;

import magpie.exceptions.MagpieException;
import magpie.task.TaskList;

import java.util.Scanner;

public class Ui {

    private static final String LINE = "\n____________________________________________________________";

    private static Scanner scanInput;

    public Ui() {
        scanInput = new Scanner(System.in);
    }
    public static void processUserInput(TaskList taskManager) {

        String input = "";
        input = scanInput.nextLine();

        while (!input.equalsIgnoreCase("bye")) {

            Parser userInput = new Parser(input);
            try{
                userInput.processCommand(taskManager);
            }
            catch (MagpieException e){
                System.out.println(e.getErrorMessage());
            }
            input = scanInput.nextLine();


        }
    }

    public static void printLogo() {
        System.out.println(LINE);
        System.out.println(" __  __          _____ _____ _____ ______ ");
        System.out.println("|  \\/  |   /\\   / ____|  __ \\_   _|  ____|");
        System.out.println("| \\  / |  /  \\ | |  __| |__) || | | |__   ");
        System.out.println("| |\\/| | / /\\ \\| | |_ |  ___/ | | |  __|  ");
        System.out.println("| |  | |/ ____ \\ |__| | |    _| |_| |____ ");
        System.out.println("|_|  |_/_/    \\_\\_____|_|   |_____|______|");
        System.out.println("\nHello! I'm Magpie :)");
        System.out.println("What can I do for you?\n");
        System.out.println(LINE);
    }

    public static void printByeMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
