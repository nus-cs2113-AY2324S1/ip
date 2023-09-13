package commands;

import zenbot.ZenBot;
import zenbot.FileHandler;
import tasks.Tasklist;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Farewell extends Command {
    
    private Tasklist tasks;

    public Farewell(Tasklist tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute() throws FileNotFoundException, IOException{
        FileHandler.writeToFile(tasks);
        ZenBot.printSeperatorLine();
        System.out.println("\tFarewell, my friend! Until our laughter intertwines again");
        ZenBot.printSeperatorLine();
    }
}
