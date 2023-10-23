package rene.ui;

import rene.storage.Storage;
import rene.task.Task;
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
     * Sets up the bridging between the UI and tasks data.
     *
     * @param tasks A record of all tasks documented that is built on program start and disposed on program exit.
     * @param dataStorage The hard disk record of all tasks documented that persists even on program exit.
     */
    public Ui(Storage dataStorage, TaskList tasks){
        this.dataStorage = dataStorage;
        this.tasks = tasks;
    }
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
     * Prints out a list of all accepted commands in the program.
     */
    public static void printValidCommands(){
        System.out.println("    Valid commands are: todo,\n" +
                "                        deadline /by [time],\n" +
                "                        event /from [start] /to [end],\n" +
                "                        list,\n" +
                "                        mark [task number],\n" +
                "                        unmark [task number],\n" +
                "                        delete [task number],\n" +
                "                        find /description [description]\n" +
                "                        find /time [time]\n" +
                "                        bye");
    }
    /**
     * Prints the symbol of a task in CLI.
     *
     * @param taskType The type of the task which details are to be printed.
     */
    public static String getTaskSymbol(Task.TaskType taskType){
        switch(taskType) {
        case TODO:
            return "[T]";
        case DEADLINE:
            return "[D]";
        case EVENT:
            return "[E]";
        default:
            return "";
        }
    }
    /**
     * Prints the done status symbol of a task in CLI.
     *
     * @param task The task which details are to be printed.
     */
    public static String getTaskDoneStatusSymbol(Task task){
        if(task.taskIsDone()){
            return "[X]";
        } else {
            return "[]";
        }
    }
    /**
     * Prints the done status text of a task in CLI.
     *
     * @param task The task which details are to be printed.
     */
    public static String getTaskDoneStatusText(Task task){
        if(task.taskIsDone()){
            return "done";
        } else {
            return "undone";
        }
    }   /**
     * Prints the timing of a task in CLI if it exists.
     *
     * @param task The task which details are to be printed.
     * @param useDefaultTiming Determines if timing is printed using default format
     */
    public static String getTaskTimingIfApplicable(Task task, boolean useDefaultTiming){
        Task.TaskType taskType = task.getTaskType();
        if(taskType != Task.TaskType.TODO){
            return  " | "  + task.getTaskTiming(useDefaultTiming);
        } else {
            return "";
        }
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
}

