package rene.ui;

import rene.storage.Storage;
import rene.task.Task;
import rene.tasklist.TaskList;
import rene.exception.ReneExceptions;

import java.util.Scanner;
public class Ui {
    private final Scanner input = new Scanner(System.in);  // Create a Scanner object
    private Storage dataStorage;
    private TaskList tasks;

    public void showLine(){
        System.out.println("    ____________________________________________________________");
    }
    public void displayOpeningMessage(){
            String logo = "     ____        _        \n"
                    + "    |  _ \\ _   _| | _____ \n"
                    + "    | | | | | | | |/ / _ \\\n"
                    + "    | |_| | |_| |   <  __/\n"
                    + "    |____/ \\__,_|_|\\_\\___|\n";
            System.out.println("    Hello from\n" + logo);
            showLine();
            System.out.println("    今日は! I am Rene Kokoro!");
            System.out.println("    Let me record your tasks!! *blushes*");
            System.out.println();
            dataStorage.loadData(tasks);
            showLine();
    }
    public String readCommand() {
        return input.nextLine();
    }

    public void displayCLosingMessage(){
        dataStorage.updateData(tasks);
        System.out.println("    Aww you are leaving? *sniffs*");
        System.out.println("    Well... hope to see you again soon!");
        showLine();
    }

    public Ui(Storage dataStorage, TaskList tasks){
        this.dataStorage = dataStorage;
        this.tasks = tasks;
    }
}

