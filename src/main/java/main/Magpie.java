package main;
import magpie.task.taskHandler;
import magpie.input.inputHandler;
import magpie.exceptions.MagpieException;
import java.util.Scanner;


public class Magpie {

    private static final String LINE = "\n____________________________________________________________";


    public static void main(String[] args) {


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


        Scanner in = new Scanner(System.in);
        String input = "";
        input = in.nextLine();

        taskHandler taskManager = new taskHandler();

        while (!input.equalsIgnoreCase("bye")) {

            inputHandler userInput = new inputHandler(input);
            try{
                userInput.processCommand(taskManager);
            }
            catch (MagpieException e){
                System.out.println(e.getErrorMessage());
            }
            input = in.nextLine();


        }
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);

    }

}