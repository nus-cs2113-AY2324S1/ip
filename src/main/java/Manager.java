import listWhisper.exceptions.DescriptionFormatException;
import storage.DataManager;
import listWhisper.task.TaskList;
import listWhisper.task.TaskClassifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Manager {
    TaskList taskList;

    public Manager() throws IOException, DescriptionFormatException {
        this.taskList = new TaskList();
        Manager.loadTasksToList(this.taskList);
    }

    public void readInput() throws DescriptionFormatException, IOException {
            Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String input = s.nextLine();
            this.execute(input);
        }
    }
    void execute(String input) throws DescriptionFormatException, IOException {
        String command = TaskClassifier.classifyTaskForExecution(this.listOfTasks, input);
        if (command.equals("bye")) {
            this.listOfTasks.saveList();
            System.exit(0);
        }
    }

    private static void loadTasksToList(ListOfTasks listOfTasks) throws IOException, DescriptionFormatException {
        ArrayList<String> data = DataManager.readFile();
        listOfTasks.load(data);
    }
}
