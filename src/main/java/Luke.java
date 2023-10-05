
import luke.actions.*;
import luke.user.LukeTimeError;
import luke.user.Ui;
import luke.tasks.*;
import luke.files.*;

import java.util.ArrayList;

public class Luke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Luke(String filePath) {//i dont get how this filePath works
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LukeTimeError e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    //private static final String BYE_COMMAND = "bye";
    //private static ArrayList<Task> taskList = new ArrayList<>();

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage); //error because c is null
                isExit = c.isExit(); //for bye command
            } catch (LukeTimeError e) { //from Parser.parse i think
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Luke("./out/artifacts/ip_jar/memory.txt").run();
        //new Luke("data/tasks.txt").run();
        //that was the only line
    }
}

