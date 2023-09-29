package duke.ui;

import static duke.ui.MessageConstants.LINE;
import static duke.ui.MessageConstants.LOGO;
import static duke.ui.MessageConstants.MESSAGE_WELCOME;
import static duke.ui.MessageConstants.MESSAGE_GOODBYE;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    private final PrintStream printer;

    public Ui() {
        this.sc = new Scanner(System.in);
        this.printer = System.out;
    }

    /**
     * Prints the welcome message.
     */

    public void showWelcome() {
        printer.println(LINE);
        printer.println(MESSAGE_WELCOME);
        printer.println(LOGO);
        printer.println(LINE);
    }

    /**
     * Prints the goodbye message.
     */

    public void showGoodbye() {
        printer.println(LINE);
        printer.println(MESSAGE_GOODBYE);
        printer.println(LINE);
    }

    /**
     * Prints the given message.
     */
    public void showMessage(String message) {
        printer.println(LINE);
        printer.println(message);
        printer.println(LINE);
    }
 
    /**
     * Reads the next line of input.
     */
    public String readCommand() {
        printer.println(LINE);
        printer.println();
        printer.print("Enter command: ");
        return sc.nextLine();
    }
    
}
