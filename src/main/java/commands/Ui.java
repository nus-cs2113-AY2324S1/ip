package commands;

public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        String logoRyan = " ____  _          \n"
                + "|  _ \\| | ___   __\n"
                + "| |_) | |/ _ \\ / _|\n"
                + "|  __/| | (_) | (__\n"
                + "|_|   |_|\\___/ \\___|\n";

        String line = "Hello! I'm Ryan Loh \nWhat can I do for you?\nType anything to be added to your magic list :D\n";
        System.out.println(line + logoRyan);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        String lineBreak = "------------------------------ \n";

        System.out.println(lineBreak + "Bye. Hope to see you again soon!\n");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }


}
