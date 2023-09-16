package main;
import magpie.files.fileHandler;
import magpie.task.taskHandler;
import magpie.input.inputHandler;
import magpie.exceptions.MagpieException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Magpie {

    private static final String LINE = "\n____________________________________________________________";

    public static void processUserInput(taskHandler taskManager) {
        Scanner in = new Scanner(System.in);
        String input = "";
        input = in.nextLine();

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
    }

    public static void main(String[] args) throws FileNotFoundException {

        taskHandler taskManager = new taskHandler();
        fileHandler taskFileHandler = new fileHandler();
        taskFileHandler.readFile(taskManager);

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

        processUserInput(taskManager);

        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);

        try{
            taskFileHandler.saveToFile();
        }
        catch (IOException e){
            System.out.println("Opps. File writing error!");
        }
    }

}