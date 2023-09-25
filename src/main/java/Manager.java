import common.Messages;
import listWhisper.exceptions.DescriptionFormatException;
import listWhisper.exceptions.InvalidCommandException;
import listWhisper.task.DataManager;
import listWhisper.task.ListOfTasks;
import listWhisper.task.TaskClassifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Manager {
    ListOfTasks listOfTasks;

    public Manager() throws IOException, DescriptionFormatException {
        this.listOfTasks = new ListOfTasks();
        Manager.loadTasksToList(this.listOfTasks);
    }

    public void readInput() throws DescriptionFormatException {
            Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String input = s.nextLine();
            this.execute(input);
        }
    }
    void execute(String input) throws DescriptionFormatException {
        String command = TaskClassifier.classifyTaskForExecution(this.listOfTasks, input);
        if (command.equals("bye")) {
            System.exit(0);
        }
    }

    private static void loadTasksToList(ListOfTasks listOfTasks) throws IOException, DescriptionFormatException {
        ArrayList<String> data = DataManager.readFile();
        listOfTasks.load(data);
    }
}
