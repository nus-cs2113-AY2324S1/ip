package doli.GUI;

import doli.exceptions.DoliExceptions;

import java.util.Scanner;

/**
 * <h3>Ui class</h3>
 * The Ui class aims at organising the user interface and interaction.
 * It primarily deals with welcoming the user, greeting at exit, displaying
 * various error messages and getting the user input.
 *
 * @author pappalardodaniel
 * @version 1.0
 * @since 2023-11-03
 */
public class Ui {
    private static final Scanner IN = new Scanner(System.in);
    private static final String LOGO = " ____       _\n"
            + "|  _  \\    | | [_]\n"
            + "| | | |____| |  _\n"
            + "| |_| | [] | | | |\n"
            + "|____/|____|__||_|\n";
    private static final String WELCOME_MESSAGE = String.format("Hello, my name is\n%s\n"
            + "I can help you create an agenda to manage your tasks.\nWhat is your name?", LOGO);
    private static final String BYE_MESSAGE = "Thank you for your patience, hope to see you soon! Bye!";

    /**
     * Prints out the given String (shorter than the standard java notation)
     * @param str of type String to be printed out
     */
    private static void print(String str) {
        System.out.println(str);
    }

    /**
     * Constructs an empty object of type Ui
     */
    public Ui() {
    }

    /**
     * Displays a message welcoming the user, the official
     * Doli logo/script and asking for the user's name.
     */
    public void welcomeUser() {
        print(WELCOME_MESSAGE);
    }

    /**
     * Records the user's name in order for Doli to be able to address him directly from now on
     * @return the input name of the user as type String
     * @throws DoliExceptions in case the user did not provide a name
     */
    public String getName() throws DoliExceptions {
        String name = IN.nextLine();
        if (!name.trim().isEmpty()) {
            return name;
        } else {
            throw new DoliExceptions("Please provide a valid name");
        }
    }

    /**
     * Greets the user at the end of the program
     */
    public void greetUser() {
        print(BYE_MESSAGE);
    }

    /**
     * Prints the username as input by the user
     * @param name of type String
     */
    public void printUser(String name) {
        print(name + ":");
    }

    /**
     * Invites the user to start asking questions and typing commands for Doli
     */
    public void askForInstruction() {
        print("How can I help you?");
        printHLine();
    }

    /**
     * Prints a horizontal divider to create structure in the program
     * and in between various commands and responses
     */
    public void printHLine() {
        print("==============================");
    }

    /**
     * Prints out a personalisable error message
     * @param error of type String specifying the personalised error message
     */
    public void showError(String error) {
        print(error);
    }

    /**
     * Prints out the loading error message
     */
    public void showLoadingError() {
        print("Loading Error");
    }

    /**
     * Obtains the input from the user by scanning the next line typed in
     * @return a String containing the user's input word for word
     */
    public String getInput() {
        String input = IN.nextLine();
        return input;
    }
}