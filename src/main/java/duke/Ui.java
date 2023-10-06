package duke;

/**
 * Class that consists handles all the UI and printing operations of this chatbot.
 */
public class Ui {

    /**
     * Method to print a divider line
     */
    public void printLine() {
        echo("____________________________________________________________\n");
    }

    /**
     * Method to print a greeting to the user upon launching the chatbot.
     */
    public void printGreeting() {
        String logo = "______       _     _\n"
                + "| ___ \\     | |   | |\n"
                + "| |_/ / ___ | |__ | |__  _   _\n"
                + "| ___ \\/ _ \\| '_ \\| '_ \\| | | |\n"
                + "| |_/ | (_) | |_) | |_) | |_| |\n"
                + "\\____/ \\___/|_.__/|_.__/ \\__, |\n"
                + "                          __/ |\n"
                + "                         |___/";
        echo("Hello from\n" + logo+'\n');
        printLine();
        echo("Hello! I'm Bobby\n");
        echo("What can I do for you?\n");
        printLine();
    }

    /**
     * Method to print greeting when exiting the chatbot.
     */
    public void printBye(){
        printLine();
        echo("Bye. Hope to see you again soon!\n");
        printLine();
    }

    /**
     * Method to print intentation space.
     */
    public void printIndentTask(){
        System.out.print("      ");
    }

    /**
     * Callback method to inform the user that an item has been marked.
     */
    public void printMarked(){
        echo("Nice! I've marked this task as done:\n");
    }

    /**
     * Callback method to inform the user that an item has been unmarked.
     */
    public void printUnmarked(){
        echo("OK, I've marked this task as not done yet:\n");
    }

    /**
     * Method to output the given text.
     * @param text User input text that will be echoed back into the output.
     */
    public void echo(String text){
        System.out.print("    " + text);
    }

}
