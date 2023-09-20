import Command.Command;
import Soccat.SoccatException;
import Parser.Parser;
import Storage.Storage;
import Storage.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Soccat {

    static TaskList tasks;
    static final String FILENAME = "data/Soccat.txt";
    static Storage taskFile;

    public static void processCommand(String line) {
        Scanner textIn = new Scanner(System.in);
        Command command;
        while (true) {
            String commandInput = textIn.nextLine();
            command = new Parser().parseCommand(commandInput);
            command.execute(tasks);
            System.out.println(line);
            storeData();
        }
    }

    public static void storeData() {
        try {
            taskFile.setTaskData(tasks.getTasks());
        } catch (IOException e) {
            System.out.println("IO Exception occurred during file storage!");
        }
    }

    public static void main(String[] args) {
        try {
            taskFile = new Storage(FILENAME);
        } catch (SoccatException e) {
            System.out.println("Failed to create data file " + FILENAME);
        } catch (IOException e) {
            System.out.println("IO Exception during file creation of " + FILENAME);
        }
        tasks = new TaskList(taskFile.getTaskData());
        String line = "____________________________________________________________";
        System.out.println(line + "\nHello! I'm soccat!\nWhat can I do for you?\n" + line);
        processCommand(line);
    }
}
