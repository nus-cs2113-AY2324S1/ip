package rene.ui;

import rene.storage.Storage;
import rene.tasklist.TaskList;

import java.util.Scanner;
/**
 * Represents the user interface for CLI displays.
 * Supports CLI interactions with users.
 */
public class Ui {
    private final Scanner input = new Scanner(System.in);  // Create a Scanner object
    private Storage dataStorage;
    private TaskList tasks;
    /**
     * Print out separating line in CLI to mark
     * start and end of chatbot replies.
     */
    public void showLine(){
        System.out.println("    ____________________________________________________________");
    }
    /**
     * Displays opening message to welcome users
     * on the launch of chatbot.
     */
    public void displayOpeningMessage(){
            String logo = "     _____ \n"
                        + "    |  __  \\  ____  __   _   ____ \n"
                        + "    | |__  | /  _  \\|  \\| |/  _  \\\n"
                        + "    | |  \\ \\|   ___/| \\ | |   ___/\n"
                        + "    |_|   \\_\\\\____| |_|\\__|\\____|\n";
            System.out.println("    Hello from\n" + logo);
            showLine();
            System.out.println("    今日は! I am Rene Kokoro!");
            System.out.println("    Let me record your tasks!! *blushes*");
            System.out.println();
            dataStorage.loadData(tasks);
            showLine();
    }
    /**
     * Retrieves the CLI input from the user
     * and documents it as a String object.
     */
    public String readCommand() {
        return input.nextLine();
    }
    /**
     * Displays closing message on exiting the chatbot.
     */
    public void displayClosingMessage(){
        dataStorage.updateData(tasks);
        System.out.println("    Aww you are leaving? *sniffs*");
        System.out.println("    Well... hope to see you again soon!");
        showLine();
    }
    /**
     * Sets up the bridging between the UI and tasks data.
     *
     * @param tasks A record of all tasks documented that is built on program start and disposed on program exit.
     * @param dataStorage The hard disk record of all tasks documented that persists even on program exit.
     */
    public Ui(Storage dataStorage, TaskList tasks){
        this.dataStorage = dataStorage;
        this.tasks = tasks;
    }
}

