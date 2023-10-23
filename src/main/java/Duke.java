import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
//Refactoring methods is helpful for transforming

/**
 * Main class of program. Creates UI, Storage and Task objects and runs program.
 */
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
        //Add all previous run tasks
        Storage.loadFileObjects();
        ArrayList<Task> fileTasks = Storage.getFileTasksArray();
        TasksList.getTasks().addAll(fileTasks);

        ui.showWelcome();
        while (!TasksList.isExitProgram()){
            String input = ui.getUserInput();
            if (ui.checkUserInput(input)) {
                taskslist.chooseCommand(input);
            }

        }
        Storage.saveFile();

        //yo
        TasksList.tasksToBeSaved.clear();
        ui.showGoodbye();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();

    }
}

