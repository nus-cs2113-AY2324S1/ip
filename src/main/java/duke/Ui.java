package duke;

/**
 * Class that consists handles all of the UI and printing operations of this chatbot.
 */
public class Ui {

    /**
     * Method to print a divider line
     */
    public void printLine() {
        System.out.println("    ____________________________________________________________");
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
        System.out.println("    Hello from\n" + logo);
        printLine();
        System.out.println("    Hello! I'm Bobby");
        System.out.println("    What can I do for you?");
        printLine();
    }

    /**
     * Method to print greeting when exiting the chatbot.
     */
    public void printBye(){
        printLine();
        System.out.println("    Bye. Hope to see you again soon!");
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
        System.out.println("    Nice! I've marked this task as done:");
    }

    /**
     * Callback method to inform the user that an item has been unmarked.
     */
    public void printUnmarked(){
        System.out.println("    OK, I've marked this task as not done yet:");
    }

    /**
     * @param text Userinput text that will be echo'd back into the output.
     */
    public void echo(String text){
        System.out.print(text);
    }

}
