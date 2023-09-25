package fredbot;

public class Ui {
    public static final String INDENT = "    ";
    public static final String DIVIDER = "    ____________________________________________________________\n";
    public static final String GREETING = "     Hello! I'm Fredbot\n" +
            "     What can I do for you?";

    public void printMessage(String message) {
        System.out.print(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }
    public void showWelcome() {
        printMessage(INDENT + GREETING);
    }

}
