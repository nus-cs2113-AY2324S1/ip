/**
 * Duke is a command-line task management robot that allows users to
 * add, delete, mark, unmark, and search for tasks. It also supports
 * different types of tasks such as Todo, Deadline, and Event.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-09-30
 */

package duke;

import java.util.Scanner;
import java.io.IOException;

import duke.commands.*;
import duke.inputProcess.Parser;
import duke.inputProcess.TaskList;
import java.io.FileNotFoundException;
import duke.tasksStorage.SaveToFile;
import duke.tasksStorage.GetFromFile;

public class Duke {

    private GetFromFile getTasks;
    private SaveToFile saveTasks;
    private TaskList tasks;
    private Ui ui;
    Scanner in;

    private Duke(String filePath) {
        ui = new Ui();
        getTasks = new GetFromFile(filePath);
        saveTasks = new SaveToFile(filePath);
        tasks = new TaskList();
        in = new Scanner(System.in);
        ui.greeting();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("Duke.txt");
        try{
            duke.getTasks.getFromTextFile(duke.tasks);
        } catch(FileNotFoundException e){
            System.out.println("\tOOPS!!! File not found.");
        }
        String userInput = duke.in.nextLine();
        Parser parser = new Parser(userInput);
        while (!userInput.equals("bye")) {
            String command = userInput;
            if (!userInput.equals("list") && !userInput.equals("help")){
                try{
                    command = parser.getCommand();
                    userInput = parser.getRemainingPart();
                    parser.newUserInput(userInput);
                } catch(IndexOutOfBoundsException e){
                    System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                    userInput = duke.in.nextLine();
                    parser.newUserInput(userInput);
                    continue;
                }
            }
            new Execute(command, userInput, parser, duke.tasks).execute();
            try {
                duke.saveTasks.saveToTextFile(duke.tasks);
            } catch(IOException e){
                System.out.println("Something wrong to save the file");
            }
            userInput = duke.in.nextLine();
            parser.newUserInput(userInput);
        }
        System.out.println("Bye. Hope to see you again soon! ");
    }
}