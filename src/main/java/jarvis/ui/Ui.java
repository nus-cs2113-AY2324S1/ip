package jarvis.ui;

import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner input = new Scanner(System.in);
//    private Storage dataStorage;
    private TaskList tasks;

    private static final String LOGO = "\n" +
            "     ____.  _____ ______________   ____.___  _________\n" +
            "    |    | /  _  \\\\______   \\   \\ /   /|   |/   _____/\n" +
            "    |    |/  /_\\  \\|       _/\\   Y   / |   |\\_____  \\ \n" +
            "/\\__|    /    |    \\    |   \\ \\     /  |   |/        \\\n" +
            "\\________\\____|__  /____|_  /  \\___/   |___/_______  /\n" +
            "                 \\/       \\/                       \\/ \n";
    private static final String LINE_BREAK = "____________________________________________________________";

    public Ui(TaskList tasks){
//        this.dataStorage = dataStorage;
        this.tasks = tasks;
    }

    /**
     * Read the user's CLI input
     * @return return it as a string --> to be used by task
     */
    public String readCommand(){
        return input.nextLine();
    }

    public void displayLine(){
        System.out.println(LINE_BREAK + "\n");
    }

    public void displayWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE_BREAK + "\n Hi Sir! I'm JARVIS \n" + " What can I do for you today?\n" + LINE_BREAK);
//        dataStorage.loadData(tasks);
    }

    public void displayGoodbyeMessage() {
//        dataStorage.updateData(tasks);
        System.out.println("Good bye sir! Have a good day" + "\n" + LINE_BREAK);
    }
}
