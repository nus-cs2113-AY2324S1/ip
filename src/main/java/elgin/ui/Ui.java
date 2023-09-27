package elgin.ui;

public class Ui {

    final String BOT_NAME;
    final String SEPARATOR = "____________________________________________________________";

    public Ui() {
        BOT_NAME = "Elgin";
    }

    public void sayGreeting() {
        String[] toPrint = new String[]{
                "Hello! I'm " + BOT_NAME,
                "What can I do for you?"
        };
        formatPrint(toPrint);
    }

    public void sayBye() {
        formatPrint("Bye. Hope to see you again soon!");
    }

    public void formatPrint(String[] lines) {
        System.out.println("\t" + SEPARATOR);
        for (String line : lines) {
            System.out.println("\t" + line);
        }
        System.out.println("\t" + SEPARATOR);
    }

    public void formatPrint(String line) {
        System.out.println("\t" + SEPARATOR);
        System.out.println("\t" + line);
        System.out.println("\t" + SEPARATOR);
    }

    public void showUnknownCmdErrorMsg() {
        formatPrint("Sorry I do not understand your command");
    }

}
