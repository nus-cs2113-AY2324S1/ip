import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
//Refactoring methods is helpful for transforming
public class Duke {

    private Ui ui;
    private Storage storage;
    private TasksList taskslist;
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskslist = new TasksList();
    }

    public void run() throws IOException {
        storage.loadFileObjects();
        ArrayList<Task> fileTasks = Storage.getFileTasksArray();

        TasksList.getTasks().addAll(fileTasks);
        while (!TasksList.isExitProgram()){
            taskslist.chooseCommand(ui.getUserInput());
        }
        Storage.saveFile();
        TasksList.tasksToBeSaved.clear();
        ui.showGoodbye();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();




    }
}

