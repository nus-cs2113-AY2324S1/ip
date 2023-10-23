package duke.ui;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * TextUi object is responsible for all messages displayed to the user.
 * All formatting is specified and followed in this class.
 */
public class TextUi {
    public static final String MESSAGE_WELCOME = "Hello! I'm KevBot";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_INIT_FAILED = "Failed to initialise address book application. Exiting...";

    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    private final Scanner in;
    private final PrintStream out;
    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcomeMessage() {
        showToUser(
                DIVIDER,
                DIVIDER,
                MESSAGE_WELCOME,
                DIVIDER);
    }

    public void showGoodbyeMessage() {
        showToUser(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }

    public void showResultToUser(String result) {
        showToUser(result, DIVIDER);
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    private void showToUser(String... message) {
        for (String m : message) {
            out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    public void showInitFailedMessage() {
        showToUser(MESSAGE_INIT_FAILED, DIVIDER, DIVIDER);
    }
}
