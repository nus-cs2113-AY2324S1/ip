package commands;

import zenbot.UI;
import zenbot.FileHandler;
import tasks.Tasklist;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents a command to quit the app
 */
public class Farewell extends Command {
    
    private Tasklist tasks;

    public Farewell(Tasklist tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints farewell message and saves tasks to file
     * @throws FileNotFoundException if given file path is invalid
     * @throws IOException if there is an error writing to file
     */
    @Override
    public void execute() throws FileNotFoundException, IOException{
        FileHandler.writeToFile(tasks);
        UI.printSeperatorLine();
        System.out.println("\tFarewell, my friend! Until our laughter intertwines again");
        UI.printSeperatorLine();
    }
}
