package linguobot;

import linguobot.task.Task;
import linguobot.command.CommandResponse;
import linguobot.file.TaskFile;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * The <code>LinguoBot</code> class represents a simple command-line task management application.
 * It allows users to interact with tasks, such as adding, listing, marking, unmarking,
 * and deleting tasks. Tasks are stored in a task list and can be loaded from and saved
 * to a file.
 */
public class LinguoBot {
    private TaskFile taskFile;
    private ArrayList<Task> taskList;
    private Scanner in;

    /**
     * Constructs a new <code>LinguoBot</code> instance. It initializes the task list based on tasks
     * stored in the task file and sets up the input scanner.
     */
    public LinguoBot() {
        taskFile = new TaskFile("./data/tasks.txt");
        taskList = taskFile.loadTasksFromFile();
        in = new Scanner(System.in);
    }

    public static void main(String[] args) {
        LinguoBot linguoBot = new LinguoBot();
        linguoBot.run();
    }

    /**
     * Runs the main loop of the <code>LinguoBot</code> application, allowing users to interact
     * with tasks through various commands.
     */
    public void run() {
        CommandResponse.displayWelcomeMessage();
        TaskFile.printFile();
        while (true) {
            String userInput = in.nextLine();
            if (userInput.equals("list")) {
                CommandResponse.printTaskList(taskList);
            }else if (userInput.startsWith("find")) {
                CommandResponse.findTask(taskList, userInput);
            } else if (userInput.startsWith("mark")) {
                CommandResponse.markTaskAsDone(taskList, userInput);
            } else if (userInput.startsWith("unmark")) {
                CommandResponse.markTaskAsUndone(taskList, userInput);
            } else if (userInput.contains("delete")) {
                CommandResponse.deleteTask(taskList, userInput);
            } else if (userInput.contains("bye")) {
                CommandResponse.displayGoodbyeMessage();
                break;
            } else {
                CommandResponse.addTask(taskList, userInput);
            }
        }
    }
}