package duke;

import java.util.Scanner;


/**
 * The {@code Ui} class is responsible for interactions with the user.
 * It handles user inputs and application outputs, including displaying welcome, exit messages,
 * errors, and other information that the user should see.
 * <p>
 * It utilizes a {@code Scanner} object to read commands entered by the user.
 * Various methods in this class print information, errors, and decorative lines to the console,
 * following the format and conventions of the application's user interface.
 * </p>
 *
 * Key Methods Include:
 * <ul>
 *     <li>{@link #readCommand()} - Reads the user input and returns it as a string.</li>
 *     <li>{@link #showError(Exception)} - Prints the error message of a given exception to the console.</li>
 *     <li>{@link #showLoadingError(String)} - Informs the user of a file loading error at a given file path.</li>
 *     <li>{@link #showWelcomeMessage()} - Displays a welcome message to the user.</li>
 *     <li>{@link #showExitMessage()} - Displays an exit message to the user.</li>
 *     <li>{@link #showLine()} - Prints a line divider to the console, used to separate different sections of output.</li>
 * </ul>
 *
 * @see Scanner
 *
 * @author  Ashok Balaji
 * @version 1.0
 * @since   2023-09-25
 */
public class Ui {
    protected static String CHATBOTNAME = "Andrew Tate";
    protected static String LINE_DIVIDER = "____________________________________________________________";
    protected Scanner myScanner;

    public Ui(){
        this.myScanner = new Scanner(System.in);
    }

    public void showWelcomeMessage(){
        showLine();
        System.out.println("Hello! I'm the top G " + CHATBOTNAME);
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showLoadingError(String filePath){
        System.out.println("Unable to create file @ " + filePath);
    }

    public void showExitMessage(){
        System.out.println("Bye, hope to see you again soon!");
        showLine();
    }

    public void showError(Exception e){
        System.out.println(e);
    }

    public String readCommand(){
        System.out.print("> ");
        return myScanner.nextLine();
    }

    public void showLine(){
        System.out.println(LINE_DIVIDER);
    }
}
