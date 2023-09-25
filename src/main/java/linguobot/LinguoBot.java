package linguobot;

import linguobot.task.Task;
import linguobot.command.CommandResponse;
import linguobot.file.TaskFile;

import java.util.Scanner;
import java.util.ArrayList;
public class LinguoBot {
    private TaskFile taskFile;
    private ArrayList<Task> taskList;
    private Scanner in;

    public LinguoBot() {
        taskFile = new TaskFile("./data/tasks.txt");
        taskList = taskFile.loadTasksFromFile();
        in = new Scanner(System.in);
    }

    public static void main(String[] args) {
        LinguoBot linguoBot = new LinguoBot();
        linguoBot.run();
    }

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
                CommandResponse.addTask(userInput, taskList);
            }
        }
    }
}